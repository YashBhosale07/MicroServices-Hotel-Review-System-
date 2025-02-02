package in.yash.RatingService.Repository;

import in.yash.RatingService.Entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepo extends JpaRepository<Rating,String> {

    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}
