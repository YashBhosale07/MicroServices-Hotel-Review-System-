package in.yash.user.service.Repositories;

import in.yash.user.service.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

        List<User> findByIdIn(Set<String> userIds);


}
