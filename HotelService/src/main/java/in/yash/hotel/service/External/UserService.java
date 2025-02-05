package in.yash.hotel.service.External;

import in.yash.hotel.service.Entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

@FeignClient(name = "UserService")
public interface UserService {
    @PostMapping("/users/bulk")
    List<User> getUsersByIds(@RequestBody Set<String> userIds);
}
