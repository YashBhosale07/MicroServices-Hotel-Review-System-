package in.yash.hotel.service.External;

import in.yash.hotel.service.Entities.HotelRatings;
import in.yash.hotel.service.Entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RatingService")
public interface RatingService {

    @GetMapping("/rating/getRatingByHotelId/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId);

    @GetMapping("/rating/getHotelTotalRating/{hotelId}")
    public ResponseEntity<HotelRatings>getHotelRatings(@PathVariable String hotelId);

}
