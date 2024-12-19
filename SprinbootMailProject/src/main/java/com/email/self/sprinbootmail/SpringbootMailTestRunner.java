package com.email.self.sprinbootmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.email.self.service.impl.EmailServiceImpl;
import com.email.self.webcamcapture.WebcamCapture;

@Component
public class SpringbootMailTestRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(SpringbootMailTestRunner.class);

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @Override
    public void run(String... args) {
        try {
            // Step 1: Send shopping summary email
            logger.info("Starting email process for shopping summary...");
            String shoppingResult = emailServiceImpl.shopping(
                new String[] {"book", "pen", "bike"},
                new Double[] {4000.0, 52.0, 150000.0}, 
                new String[] {"aaman804403@gmail.com", "alammaruti@gmail.com", "nabi.alam@finagg.in"}
            );
            logger.info("Shopping result: {}", shoppingResult);

            // Step 2: Capture an image using the webcam
            logger.info("Capturing image from webcam...");
            String imagePath = WebcamCapture.captureImage("C:\\Users\\nabia\\OneDrive\\Pictures\\Feedback"); // Specify your path
            logger.info("Image captured successfully at path: {}", imagePath);

            // Step 3: Send email with the captured image
            logger.info("Sending email with webcam image...");
         
            logger.info("Email sent successfully with the webcam image.");
        } catch (Exception e) {
            logger.error("An error occurred: {}", e.getMessage(), e);
        }
    }
}
