package in.yash.user.service.Controller;

import in.yash.user.service.Entities.User;
import in.yash.user.service.Services.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/saveUser")
    public ResponseEntity<User>createUSer(@RequestBody User user){
        User savedUser=userService.savedUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>>getAllUser(){
        List<User>savedUser=userService.getAllUser();
        return new ResponseEntity<>(savedUser,HttpStatus.OK);
    }
    @GetMapping("/getUser/{user_id}")
    public ResponseEntity<User>getUserById(@PathVariable String user_id){
        User savedUser=userService.getUser(user_id);
        return new ResponseEntity<>(savedUser,HttpStatus.OK);
    }

    @GetMapping("/checkUser/{userId}")
    public ResponseEntity<Boolean>checkUser(@PathVariable String userId){
        return new ResponseEntity<>(userService.checkUser(userId),HttpStatus.OK);
    }

    @PostMapping("/bulk")
    public List<User> getUsersByIds(@RequestBody Set<String> userIds) {
        return userService.getUsersByIds(userIds);
    }

}
