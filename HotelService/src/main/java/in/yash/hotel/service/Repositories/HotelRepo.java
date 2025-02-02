package in.yash.hotel.service.Repositories;

import in.yash.hotel.service.Entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepo extends JpaRepository<Hotel,String> {
}
