package com.Web.User.service;

import com.Web.User.Repository.UserRepository;
import com.Web.User.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id){
       return userRepository.findById(id);

    }

    public Optional<List<User>> getUserByEmail(String email){
        return userRepository.findByEmail(email);

    }

    public User UpdateUser(Long id,User user){
        return userRepository.findById(id).map(u->{
            u.setEmail(user.getEmail());
            u.setName(user.getName());
            return userRepository.save(u);
        }).orElseThrow(()->new RuntimeException("User Not found"));
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
