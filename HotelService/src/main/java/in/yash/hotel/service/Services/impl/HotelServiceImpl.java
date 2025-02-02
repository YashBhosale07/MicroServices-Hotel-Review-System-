package in.yash.hotel.service.Services.impl;

import in.yash.hotel.service.Entities.Hotel;
import in.yash.hotel.service.ExceptionHandler.ResourceNotFoundException;
import in.yash.hotel.service.Repositories.HotelRepo;
import in.yash.hotel.service.Services.HotelServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelServices {

    private final HotelRepo hotelRepo;

    @Override
    public Hotel create(Hotel hotel) {
        UUID uuid=UUID.randomUUID();
        hotel.setId(uuid.toString());
        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        List<Hotel>hotels=hotelRepo.findAll();
        return hotels;
    }

    @Override
    public Hotel get(String id) {
        return hotelRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Hotel is not present with id "+id));
    }
}
