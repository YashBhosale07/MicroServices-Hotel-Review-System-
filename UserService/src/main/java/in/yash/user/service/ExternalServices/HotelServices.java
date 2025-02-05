package in.yash.user.service.ExternalServices;

import in.yash.user.service.Entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HotelService")

public interface HotelServices {

    @GetMapping("/hotel/getHotel/{id}")
     ResponseEntity<Hotel> getHotel(@PathVariable String id);

    @GetMapping("/hotel/test")
    String Test();

}
