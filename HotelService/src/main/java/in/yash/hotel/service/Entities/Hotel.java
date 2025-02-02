package in.yash.hotel.service.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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

}
