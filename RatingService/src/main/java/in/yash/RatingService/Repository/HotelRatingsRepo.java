package in.yash.RatingService.Repository;

import in.yash.RatingService.Entities.HotelRatings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRatingsRepo extends JpaRepository<HotelRatings,String> {
}
