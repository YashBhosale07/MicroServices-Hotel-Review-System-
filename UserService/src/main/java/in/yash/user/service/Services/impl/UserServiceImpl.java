package in.yash.user.service.Services.impl;

import in.yash.user.service.Entities.User;
import in.yash.user.service.Exception.ResourceNotFoundException;
import in.yash.user.service.Repositories.UserRepository;
import in.yash.user.service.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User savedUser(User user) {
        String randomUserId= UUID.randomUUID().toString();
        user.setUser_Id(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found with id "+userId));
    }
}
