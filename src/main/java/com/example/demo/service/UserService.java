package com.example.demo.service;

import com.example.demo.controller.MainController;
import com.example.demo.dao.UserRepository;
import com.example.demo.model.MyTestUsers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LogManager.getLogger(MainController.class);

    private UserRepository userRepository;

    public void saveUser(String name, String sername) {
       userRepository.save(new MyTestUsers(name, sername));
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(cacheNames = {"userCache"})
    public List<MyTestUsers> getAllUsers() {
        logger.info("Knocking to real DataBase from getAllUsers");
        return userRepository.findAll();
    }

    public List<MyTestUsers> getAllUsersWithoutCache() {
        logger.info("Knocking to real DataBase from getAllUsers");
        return userRepository.findAll();
    }

    @Cacheable(value = "userCache", key = "#id", unless = "#result == null")
    public Optional<MyTestUsers> getOneUser(int id) {

        logger.info("Knocking to real DataBase from getOneUser");

        Optional<MyTestUsers> result = userRepository.findById(id);
        if(result.isPresent()) {
            return result;
        } else {
            return Optional.of(new MyTestUsers(null, null));
        }
    }

    @CacheEvict(value = "userCache")
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
