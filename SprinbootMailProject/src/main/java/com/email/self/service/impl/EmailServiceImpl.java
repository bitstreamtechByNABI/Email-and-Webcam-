package com.email.self.service.impl;
import java.io.File;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.email.self.service.EmailMethod;
import com.email.self.service.WebcamCaptureService;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailMethod {

    @Autowired 
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String fromEmailSender;

    private final WebcamCaptureService webcamCaptureService;

    @Autowired
    public EmailServiceImpl(WebcamCaptureService webcamCaptureService) {
        this.webcamCaptureService = webcamCaptureService;
    }

    @Override
    public String shopping(String[] items, Double[] prices, String[] toEmailIds) throws Exception {
        double totalAmount = 0;
        for (double price : prices) {
            totalAmount += price;
        }

        String message = Arrays.toString(items) + " are the items you purchased with prices " +
                         Arrays.toString(prices) + ". Total amount: " + totalAmount;
        
        // Capture webcam image
        File webcamImage = webcamCaptureService.captureImage();

        String sendMailStatus = sendMailWithAttachment(message, toEmailIds, fromEmailSender, webcamImage);
        return sendMailStatus + " sent successfully. Message: " + message;
    }

    private String sendMailWithAttachment(String msg, String[] toEmailIds, String fromEmailSender, File webcamImage) throws Exception {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSentDate(new Date());
            helper.setFrom(fromEmailSender);
            helper.setTo(toEmailIds);
            helper.setSubject("Open this mail...");
            helper.setText(msg);
            
            // Attach the webcam image
            FileSystemResource file = new FileSystemResource(webcamImage);
            helper.addAttachment("webcam-image.jpg", file);
            
            sender.send(message);
            return "Email sent successfully";
        } catch (Exception e) {
            throw new Exception("Error while sending email: " + e.getMessage(), e);
        }
    }

	

	
}
