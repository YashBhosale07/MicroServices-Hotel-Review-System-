package in.yash.RatingService.Services.impl;

import in.yash.RatingService.Entities.Rating;
import in.yash.RatingService.External.HotelService;
import in.yash.RatingService.External.UserService;
import in.yash.RatingService.Repository.RatingRepo;
import in.yash.RatingService.Services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepo ratingRepo;
    private final HotelService hotelService;
    private final UserService userService;

    @Override
    public Rating create(Rating rating) {
        UUID uuid=UUID.randomUUID();
        rating.setId(uuid.toString());
        Boolean isPresentHotel=hotelService.checkHotel(rating.getHotelId()).getBody();
        Boolean isPresentUser=userService.checkUser(rating.getUserId()).getBody();
        if(! isPresentHotel || !isPresentUser){
            throw new RuntimeException("you cannot rate");
        }
        return ratingRepo.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepo.findAll();
    }

    @Override
    public List<Rating> getAllRatingByUserId(String userId) {
        return ratingRepo.findByUserId(userId);
    }

    @Override
    public List<Rating> getAllRatingByHotelId(String hotelId) {
        return ratingRepo.findByHotelId(hotelId);
    }
}
