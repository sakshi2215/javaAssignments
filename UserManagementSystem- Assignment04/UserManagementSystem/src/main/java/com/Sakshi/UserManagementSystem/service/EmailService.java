package com.Sakshi.UserManagementSystem.service;
import com.Sakshi.UserManagementSystem.model.Users;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${techcorp.hr.emails}")
    private List<String> hrEmails;

    public EmailService(JavaMailSender mailSender,
                        SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendEmail(
            List<String> to,
            String subject,
            String templateName,
            Context context
    ) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            message,
                            MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                            "UTF-8"
                    );

            String html = templateEngine.process(templateName, context);

            helper.setFrom(fromEmail);
            helper.setTo(to.toArray(new String[0]));
            helper.setSubject(subject);
            helper.setText(html, true);

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Email sending failed", e);
        }
    }

    public List<String> getHrEmails() {
        return hrEmails;
    }
    public List<String> getCreateRecipients(Users user) {
        List<String> recipients = new ArrayList<>(hrEmails);
        recipients.add(user.getEmail());
        return recipients;
    }

    public List<String> getUpdateRecipients(Users user) {
        List<String> recipients = new ArrayList<>(hrEmails);
        recipients.add(user.getEmail());
        return recipients;
    }

    public List<String> getDeleteRecipients(Users user) {
        List<String> recipients = new ArrayList<>(hrEmails);
        recipients.add(user.getEmail());
        return recipients;
    }

}
