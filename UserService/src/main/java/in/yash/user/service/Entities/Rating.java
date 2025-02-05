package in.yash.user.service.Entities;

import lombok.Data;

@Data
public class Rating {
    private String id;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;
    private Hotel hotel;
}
