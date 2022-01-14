package com.example.egedemirbas.User.Service;

import com.example.egedemirbas.User.Dao.UserDao;
import com.example.egedemirbas.User.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityService {

    @Autowired
    private UserDao userDao;

    public List<User> findAll(){
        return userDao.findAll();
    }

    public User findUserById(Long id){
        return userDao.findUserById(id);
    }

    public User save(User user){
        return userDao.save(user);
    }
}
