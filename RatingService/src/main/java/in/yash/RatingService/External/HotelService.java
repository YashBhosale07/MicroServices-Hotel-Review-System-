package in.yash.RatingService.External;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HotelService")
public interface HotelService {

    @GetMapping("/hotel/checkHotel/{hotelId}")
    public ResponseEntity<Boolean> checkHotel(@PathVariable String hotelId);


}
