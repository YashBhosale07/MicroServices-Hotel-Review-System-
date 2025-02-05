package in.yash.RatingService.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Ratings")
@Getter
@Setter
public class Rating {
    @Id
    private String id;
    private String userId;
    private String hotelId;
    private double rating;
    private String feedback;
}
