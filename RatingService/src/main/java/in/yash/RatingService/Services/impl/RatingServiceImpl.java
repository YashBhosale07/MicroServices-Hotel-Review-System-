package in.yash.RatingService.Services.impl;

import in.yash.RatingService.Entities.HotelRatings;
import in.yash.RatingService.Entities.Rating;
import in.yash.RatingService.External.HotelService;
import in.yash.RatingService.External.UserService;
import in.yash.RatingService.Repository.HotelRatingsRepo;
import in.yash.RatingService.Repository.RatingRepo;
import in.yash.RatingService.Services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepo ratingRepo;
    private final HotelService hotelService;
    private final UserService userService;
    private final HotelRatingsRepo hotelRatingsRepo;

    @Override
    public Rating create(Rating rating) {
        UUID uuid=UUID.randomUUID();
        rating.setId(uuid.toString());
        Boolean isPresentHotel=hotelService.checkHotel(rating.getHotelId()).getBody();
        Boolean isPresentUser=userService.checkUser(rating.getUserId()).getBody();
        if(! isPresentHotel || !isPresentUser){
            throw new RuntimeException("you cannot rate");
        }
        Optional<HotelRatings> hotelRatings = hotelRatingsRepo.findById(rating.getHotelId());
        if(hotelRatings.isPresent()){
            int noOfRating=hotelRatings.get().getNoOfRating()+1;
            double ratingOfHotel=hotelRatings.get().getRating()+rating.getRating();
            double rate=ratingOfHotel/noOfRating;
            hotelRatings.get().setRating(rate);
            hotelRatings.get().setNoOfRating(noOfRating);
            hotelRatingsRepo.save(hotelRatings.get());
        }else{
            HotelRatings newRating=new HotelRatings();
            newRating.setNoOfRating(1);
            newRating.setHotelId(rating.getHotelId());
            newRating.setRating(rating.getRating());
            hotelRatingsRepo.save(newRating);
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

    @Override
    public HotelRatings getHotelRatings(String hotelId) {
        return hotelRatingsRepo.findById(hotelId).orElseThrow(()->new RuntimeException("Cannot be Rated"));
    }
}
