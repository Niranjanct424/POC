package com.java.app.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {

    @GetMapping("/email")
    public String getEmailTemplate(Model model) {
        // Inject data to populate email template placeholders
        model.addAttribute("recipientName", "John Doe");
        model.addAttribute("emailSubject", "Spring Boot and Thymeleaf Email Template");
        model.addAttribute("emailBody", "This is a sample email body");

        // Return the name of the email template file
        return "email-template";
    }
    
    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestParam String referenceNumber,
                                             @RequestParam String emailAddress,
                                             @RequestParam String subject,
                                             @RequestParam String jobStatus,
                                             @RequestParam String mailContent,Model model) {
        // Logic to send email using referenceNumber, emailAddress, subject, jobStatus, and mailContent
        // ...

        // Return success response with a message
    	
    	model.addAttribute("recipientName", "John Doe");
        model.addAttribute("emailSubject", "Spring Boot and Thymeleaf Email Template");
        model.addAttribute("emailBody", "This is a sample email body");
        return ResponseEntity.ok("Email sent successfully");
    }
    
    // Example API endpoint for sending email
//    @GetMapping("/sendEmail")
//    public String sendEmail() {
//        try {
//            MimeMessage message = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message);
//
//            helper.setTo("recipient@example.com");
//            helper.setText("This is a test email from Spring Boot");
//            helper.setSubject("Test Email");
//
//            javaMailSender.send(message);
//            return "Email sent successfully";
//        } catch (Exception e) {
//            return "Error sending email: " + e.getMessage();
//        }
//    }

}
