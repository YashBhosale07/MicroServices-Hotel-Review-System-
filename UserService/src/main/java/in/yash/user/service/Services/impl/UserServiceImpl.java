package in.yash.user.service.Services.impl;

import feign.FeignException;
import in.yash.user.service.Entities.Hotel;
import in.yash.user.service.Entities.Rating;
import in.yash.user.service.Entities.User;
import in.yash.user.service.Exception.ResourceNotFoundException;
import in.yash.user.service.ExternalServices.HotelServices;
import in.yash.user.service.ExternalServices.RatingServices;
import in.yash.user.service.Repositories.UserRepository;
import in.yash.user.service.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final HotelServices hotelServices;
    private final RatingServices ratingServices;

    @Override
    public User savedUser(User user) {
        String randomUserId= UUID.randomUUID().toString();
        user.setId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
       User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found with id "+userId));
        if (user.getRatings() == null) {
            user.setRatings(new ArrayList<>());
        }
       try{
           List< Rating> ratings =ratingServices.getRatingByUserId(userId).getBody();
           for (int i=0;i<ratings.size();i++){
               String hotelId=ratings.get(i).getHotelId();
               Hotel hotel = hotelServices.getHotel(hotelId).getBody();
               ratings.get(i).setHotel(hotel);
               user.getRatings().add(ratings.get(i));
           }

       }catch (FeignException e){
           System.out.println("Error"); e.printStackTrace();
       }

        return user;
    }

    @Override
    public Boolean checkUser(String userId) {
        User user=userRepository.findById(userId).get();
        if(user.getId().isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public List<User> getUsersByIds(Set<String> userIds) {
        return userRepository.findByIdIn(userIds);
    }


}
