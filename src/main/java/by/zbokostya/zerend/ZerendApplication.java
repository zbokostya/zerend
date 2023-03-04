package by.zbokostya.zerend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableAsync
public class ZerendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZerendApplication.class, args);
    }

}
