package in.yash.RatingService.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class HotelRatings {
    @Id
    private String hotelId;
    private double rating;
    private int noOfRating;
}
