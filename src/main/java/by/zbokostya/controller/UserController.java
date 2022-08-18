package by.zbokostya.controller;


import by.zbokostya.entity.User;
import by.zbokostya.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/all")
public class UserController {


    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/admin/info")
    public ResponseEntity<User> getUserInfo() {
        return ResponseEntity.ok(getUser());
    }

    private User getUser() {
        return userService.getUserWithAuthorities();
    }

    @GetMapping("/generateapi")
    public ResponseEntity<String> generateApi() {
        return ResponseEntity.ok(userService.generateApiUser());
    }


}
