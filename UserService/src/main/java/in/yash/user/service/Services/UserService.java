package in.yash.user.service.Services;

import in.yash.user.service.Entities.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User savedUser(User user);
    List<User> getAllUser();
    User getUser(String userId);
    Boolean checkUser(String userId);
    List<User>getUsersByIds(Set<String> userIds);
}
