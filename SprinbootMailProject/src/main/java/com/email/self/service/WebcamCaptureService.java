package com.email.self.service;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamException;

@Component
public class WebcamCaptureService {

    public File captureImage() throws IOException {
        Webcam webcam = Webcam.getDefault();
        if (webcam == null) {
            throw new WebcamException("No webcam detected.");
        }
        webcam.open();
        
        // Capture image
        BufferedImage image = webcam.getImage();
        
        // Save image to temporary file
        File tempFile = new File(System.getProperty("java.io.tmpdir"), "webcam-image.jpg");
        ImageIO.write(image, "JPG", tempFile);

        webcam.close();
        
        return tempFile;
    }
}
