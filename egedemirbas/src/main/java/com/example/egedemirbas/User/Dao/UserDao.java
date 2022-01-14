package com.example.egedemirbas.User.Dao;

import com.example.egedemirbas.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findUserById(Long id);

    User save(User user);
}
