package by.zbokostya.zerend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


@Configuration
public class MailConfig {

    @Value("${spring.mail.host}")
    private String smptHost;
    @Value("${spring.mail.port}")
    private Integer smptPort;
    @Value("${spring.mail.username}")
    private String smptUsername;
    @Value("${spring.mail.password}")
    private String smptPassword;


    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(smptHost);
        mailSender.setPort(smptPort);

        mailSender.setUsername(smptUsername);
        mailSender.setPassword(smptPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
