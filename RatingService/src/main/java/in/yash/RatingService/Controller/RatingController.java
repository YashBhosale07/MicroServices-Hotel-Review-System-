package in.yash.RatingService.Controller;
import in.yash.RatingService.Entities.Rating;
import in.yash.RatingService.Services.RatingService;
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
    public ResponseEntity<Rating>create(@RequestBody Rating rating){
        Rating savedRating=ratingService.create(rating);
        return new ResponseEntity<>(savedRating, HttpStatus.CREATED);
    }

    @GetMapping("/getRatings")
    public ResponseEntity<List<Rating>>getAllRatings(){
        List<Rating>getAllRating=ratingService.getAllRatings();
        return new ResponseEntity<>(getAllRating,HttpStatus.FOUND);
    }

    @GetMapping("/getRatingByUserId/{userId}")
    public ResponseEntity<List<Rating>>getRatingByUserId(@PathVariable String userId){
        List<Rating>getRatingByUserId=ratingService.getAllRatingByUserId(userId);
        return new ResponseEntity<>(getRatingByUserId,HttpStatus.OK);
    }

    @GetMapping("/getRatingByHotelId/{hotelId}")
    public ResponseEntity<List<Rating>>getRatingByHotelId(@PathVariable String hotelId){
        List<Rating>getRatingByHotelId=ratingService.getAllRatingByHotelId(hotelId);
        return new ResponseEntity<>(getRatingByHotelId,HttpStatus.OK);
    }
}
