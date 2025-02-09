package in.yash.hotel.service.Controller;

import in.yash.hotel.service.Entities.Hotel;
import in.yash.hotel.service.Services.HotelServices;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<Hotel>getHotel(@PathVariable String id, HttpServletRequest httpServletRequest){
        Hotel hotel=hotelServices.get(id);
        return new ResponseEntity<>(hotel,HttpStatus.OK);
    }

    @GetMapping("/getAllHotel")
    public ResponseEntity<List<Hotel>>getAllHotel(){
        List<Hotel> hotels=hotelServices.getAll();
        return new ResponseEntity<>(hotels,HttpStatus.FOUND);
    }


    @GetMapping("/checkHotel/{hotelId}")
    public ResponseEntity<Boolean>checkHotel(@PathVariable String hotelId){
        System.out.println("checkHotel");
        return new ResponseEntity<>(hotelServices.checkHotel(hotelId),HttpStatus.OK);
    }
    public ResponseEntity<Boolean>checkHotelFallBackMethod(String hotelId){
        System.out.println("Called FallBack Method");
        return new ResponseEntity<>(hotelServices.checkHotel(null),HttpStatus.OK);
    }

}
