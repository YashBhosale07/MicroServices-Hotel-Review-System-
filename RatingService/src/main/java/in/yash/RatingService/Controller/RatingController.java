package in.yash.RatingService.Controller;
import in.yash.RatingService.Entities.HotelRatings;
import in.yash.RatingService.Entities.Rating;
import in.yash.RatingService.External.HotelService;
import in.yash.RatingService.Services.RatingService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping("/create")
    @Retry(name = "CreateRating", fallbackMethod = "createRatingFallBackMethod")
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        try {
            Rating savedRating = ratingService.create(rating);
            return new ResponseEntity<>(savedRating, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
            throw e;
        }
    }

    public ResponseEntity<Rating>createRatingFallBackMethod(Rating rating,Throwable throwable){
        System.out.println("FallBackMethod");
        return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getRatings")
    public ResponseEntity<List<Rating>>getAllRatings(){
        List<Rating>getAllRating=ratingService.getAllRatings();
        return new ResponseEntity<>(getAllRating,HttpStatus.FOUND);
    }

    @GetMapping("/getRatingByUserId/{userId}")
    @CircuitBreaker(name = "getRatingByUserIdCricuitBreaker",fallbackMethod = "getRatingByUserIdCricuitBreaker")
    public ResponseEntity<List<Rating>>getRatingByUserId(@PathVariable String userId){
        List<Rating>getRatingByUserId=ratingService.getAllRatingByUserId(userId);
        return new ResponseEntity<>(getRatingByUserId,HttpStatus.OK);
    }
    public ResponseEntity<List<Rating>>getRatingByUserIdCricuitBreaker(@PathVariable String userId){
        return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getRatingByHotelId/{hotelId}")
    public ResponseEntity<List<Rating>>getRatingByHotelId(@PathVariable String hotelId){
        List<Rating>getRatingByHotelId=ratingService.getAllRatingByHotelId(hotelId);
        return new ResponseEntity<>(getRatingByHotelId,HttpStatus.OK);
    }

    @GetMapping("/getHotelTotalRating/{hotelId}")
    public ResponseEntity<HotelRatings>getHotelRatings(@PathVariable String hotelId){
        HotelRatings ratings=ratingService.getHotelRatings(hotelId);
        return new ResponseEntity<>(ratings,HttpStatus.OK);
    }
}
