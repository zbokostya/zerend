package by.zbokostya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class ZerendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZerendApplication.class, args);
    }

}
