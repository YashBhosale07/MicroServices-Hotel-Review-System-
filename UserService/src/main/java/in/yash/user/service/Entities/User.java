package in.yash.user.service.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "micro_Users")
@Getter
@Setter
public class User {
    @Id
    @Column(name = "ID")
    private String user_Id;
    @Column(name = "NAME")
    private String name;
    @Transient
    private String email;
    @Column(name = "ABOUT")
    private String about;
    @Column(name = "RATINGS")
    private List<Rating>ratings;
}
