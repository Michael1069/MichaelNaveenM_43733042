package com.hostelsphere.app.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hostelsphere.app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByRole_Name(String name);
}
