package in.yash.RatingService.External;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "UserService")
public interface UserService {

    @GetMapping("/users/checkUser/{userId}")
    public ResponseEntity<Boolean> checkUser(@PathVariable String userId);

}
