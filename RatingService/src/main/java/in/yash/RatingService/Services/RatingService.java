package in.yash.RatingService.Services;

import in.yash.RatingService.Entities.HotelRatings;
import in.yash.RatingService.Entities.Rating;

import java.util.List;

public interface RatingService {
    //TODO create rating
    Rating create(Rating rating);

    //TODO get all rating
    List<Rating> getAllRatings();

    //TODO get all by userId
    List<Rating>getAllRatingByUserId(String userId);

    //TODO get all by hotelId
    List<Rating>getAllRatingByHotelId(String hotelId);
    HotelRatings getHotelRatings(String hotelId);
}
