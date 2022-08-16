package by.zbokostya.controller;


import by.zbokostya.entity.LoginVM;
import by.zbokostya.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthController(UserService userService, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userService = userService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }


//    @PostMapping("/authenticate")
//    private void register() {}

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UUID> register(@RequestBody LoginVM loginVM) {
        return ResponseEntity.ok(userService.createUser(loginVM));

    }

    @PostMapping("/authenticate")
    public ResponseEntity<UsernamePasswordAuthenticationToken> authorize(@RequestBody LoginVM loginVM) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginVM.getLogin(),
                loginVM.getPassword()
        );
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>(new UsernamePasswordAuthenticationToken(loginVM.getLogin(), loginVM.getPassword()), HttpStatus.OK);
    }

}
