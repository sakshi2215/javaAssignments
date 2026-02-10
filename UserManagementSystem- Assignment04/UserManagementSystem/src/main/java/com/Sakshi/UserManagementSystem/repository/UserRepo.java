package com.Sakshi.UserManagementSystem.repository;
import com.Sakshi.UserManagementSystem.model.Users;   //only small case
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<Users,Long> {
    List<Users> findByActiveTrue();

}
