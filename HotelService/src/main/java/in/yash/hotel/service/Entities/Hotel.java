package in.yash.hotel.service.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Hotels")
@Getter
@Setter
public class Hotel {

    @Id
    private String id;
    private String name;
    private String location;
    private String about;
    private Double overAllRatings=0.0;
    @Transient
    private List<Rating> ratings;

}
