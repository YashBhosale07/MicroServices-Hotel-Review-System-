package in.yash.hotel.service.Entities;

import lombok.Data;

@Data
public class Rating {

    private double rating;
    private String feedback;
    private String userId;
    private User user;


}
