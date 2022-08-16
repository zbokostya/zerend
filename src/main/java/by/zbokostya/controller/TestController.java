package by.zbokostya.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<String> getUserInfo() {
        return ResponseEntity.ok("123");
    }

//    public ResponseEntity<Void> connectApi() {
//
//    }
}
