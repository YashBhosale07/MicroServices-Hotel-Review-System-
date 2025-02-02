package in.yash.hotel.service.Controller;

import in.yash.hotel.service.Entities.Hotel;
import in.yash.hotel.service.Services.HotelServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {
    private final HotelServices hotelServices;

    @PostMapping("/create")
    public ResponseEntity<Hotel>createHotel(@RequestBody Hotel hotel){
        Hotel savedHotel=hotelServices.create(hotel);
        return new ResponseEntity<>(savedHotel, HttpStatus.CREATED);
    }

    @GetMapping("/getHotel/{id}")
    public ResponseEntity<Hotel>getHotel(@PathVariable String id){
        Hotel hotel=hotelServices.get(id);
        return new ResponseEntity<>(hotel,HttpStatus.FOUND);
    }

    @GetMapping("getAllHotel")
    public ResponseEntity<List<Hotel>>getAllHotel(){
        List<Hotel> hotels=hotelServices.getAll();
        return new ResponseEntity<>(hotels,HttpStatus.FOUND);
    }


}
