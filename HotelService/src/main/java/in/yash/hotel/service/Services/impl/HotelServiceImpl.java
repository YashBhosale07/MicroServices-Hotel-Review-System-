package in.yash.hotel.service.Services.impl;

import in.yash.hotel.service.Entities.Hotel;
import in.yash.hotel.service.Entities.HotelRatings;
import in.yash.hotel.service.Entities.Rating;
import in.yash.hotel.service.Entities.User;
import in.yash.hotel.service.ExceptionHandler.ResourceNotFoundException;
import in.yash.hotel.service.External.RatingService;
import in.yash.hotel.service.External.UserService;
import in.yash.hotel.service.Repositories.HotelRepo;
import in.yash.hotel.service.Services.HotelServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelServices {

    private final HotelRepo hotelRepo;
    private final UserService userService;
    private final RatingService ratingService;

    @Override
    public Hotel create(Hotel hotel) {
        UUID uuid=UUID.randomUUID();
        hotel.setId(uuid.toString());
        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        List<Hotel>hotels=hotelRepo.findAll();
        return hotels;
    }

    @Override
    public Hotel get(String id) {
        Hotel savedHotel = hotelRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel is not present with id " + id));
        savedHotel.setRatings(new ArrayList<>());
        List<Rating> ratings = ratingService.getRatingByHotelId(id).getBody();
        HotelRatings hotelRatings=ratingService.getHotelRatings(id).getBody();
        savedHotel.setOverAllRatings(hotelRatings.getRating());
        savedHotel.setPeopleRated(hotelRatings.getNoOfRating());
        Set<String> userIds = ratings.stream()
                .map(Rating::getUserId)
                .collect(Collectors.toSet());
        List<User> users = userService.getUsersByIds(userIds);
        for (int i = 0; i < ratings.size(); i++) {
            String userId = ratings.get(i).getUserId();
            User user = null;
            if (userId != null) {
                for (User u : users) {
                    if (u.getId() != null && u.getId().equals(userId)) {
                        user = u;
                        System.out.println(user.toString());
                        break;
                    }
                }
            }
            ratings.get(i).setUser(user);
        }
        savedHotel.setRatings(ratings);
        return savedHotel;
    }


    @Override
    public Boolean checkHotel(String hotelId) {
        Hotel hotel=hotelRepo.findById(hotelId).get();
        if(hotel.getId().isEmpty()){
            return false;
        }
        return true;
    }
}
