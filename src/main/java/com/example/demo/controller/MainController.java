package com.example.demo.controller;

import com.example.demo.businessExceptions.DeleteIdException;
import com.example.demo.model.MyTestUsers;
import com.example.demo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Optional;


@RestController
public class MainController {

    private static final Logger logger = LogManager.getLogger(MainController.class);

    @PostConstruct
    private void init() {
        logger.debug("This will be printed on debug");
        logger.info("This will be printed on info");
        logger.warn("This will be printed on warn");
        logger.error("This will be printed on error");
        logger.fatal("This will be printed on fatal");
    }

    final private UserService userService;

    private MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/save")
    private String saveUsers() {
        for (int i = 1; i < 901; i++) {
            userService.saveUser("AnyName" + i, "AnySername" + i);
        }
        return "Was add new users";
    }

    @RequestMapping(value = "/delete/{id}")
    private String deleteUsers(@PathVariable int id) {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            try {
                throw new DeleteIdException();
            } catch (DeleteIdException ex) {
                logger.error(ex);
            }
        }
        return "Was delete 1 user. Left: " + userService.getAllUsers().size();
    }

    @RequestMapping(value = "/get_one/{id}")
    private Optional<MyTestUsers> getOne(@PathVariable int id) {
        return userService.getOneUser(id);
    }

    @RequestMapping(value = "/get_users")
    private String getUsers() {
        return "Users Total: " + userService.getAllUsers().size();
    }

    @RequestMapping(value = "/get_users_without_cache")
    private String getAllUsersWithoutCache() {
        return "Users Total: " + userService.getAllUsersWithoutCache().size();
    }
}
