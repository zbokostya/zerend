package by.zbokostya.zerend.service.impl;

import by.zbokostya.zerend.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    private final static Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender emailSender;

    @Value("${spring.mail.from}")
    private String fromEmail;

    @Value("${zerend.host.default}")
    private String address;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(
            String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Async
    public void sendConfirmationEmail(User user, String token) {
        try {


            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(fromEmail);
            helper.setTo(user.getEmail());
            helper.setSubject("Confirm registration");
            String messageText =
                    "Dear [[name]],<br>"
                            + "Please click the link below to verify your registration:<br>"
                            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                            + "Thank you,<br>"
                            + "Your company name.";
            messageText = messageText.replace("[[name]]", user.getLogin());
            messageText = messageText.replace("[[URL]]", address + "/verify?code=" + token);
            helper.setText(messageText, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            logger.error("Failed to send email for: " + user.getEmail() + "\n" + e);
            throw new IllegalArgumentException("Failed to send email for: " + user.getEmail());
        }
    }
}
