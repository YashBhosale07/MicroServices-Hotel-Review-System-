package in.yash.user.service.ExternalServices;

import in.yash.user.service.Entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RatingService")

public interface RatingServices {
    @GetMapping("/rating/getRatingByUserId/{userId}")
    ResponseEntity<List<Rating>>getRatingByUserId(@PathVariable String userId);
}
