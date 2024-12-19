package com.email.self.webcamcapture;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

public class WebcamCapture {

    static {
        // Load OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static String captureImage(String savePath) {
        VideoCapture camera = new VideoCapture(0); // 0 is the default camera
        if (!camera.isOpened()) {
            throw new RuntimeException("Camera not found or not accessible");
        }

        Mat frame = new Mat();
        camera.read(frame);
        String filePath = savePath + "/captured_image.jpg";
        Imgcodecs.imwrite(filePath, frame);
        camera.release();
        return filePath;
    }
}
