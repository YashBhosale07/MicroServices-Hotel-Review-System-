package in.yash.hotel.service.Services;

import in.yash.hotel.service.Entities.Hotel;

import java.util.List;

public interface HotelServices {
    Hotel create(Hotel hotel);
    List<Hotel>getAll();
    Hotel get(String id);
}
