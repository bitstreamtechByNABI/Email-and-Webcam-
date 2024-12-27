package nabi.qrcode.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import nabi.qrcode.model.QRCodeRequest;
import nabi.qrcode.service.QRCodeService;

@Service
public class QRCodeServiceImpl implements QRCodeService {
	
	@Autowired
	LocationService locationService;

    @Override
    public byte[] generateQRCode(QRCodeRequest request) throws Exception {String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    // Fetch current location coordinates
    String coordinates = locationService.getCurrentLocation(); // Format: "latitude,longitude"
    String googleMapsUrl = "https://www.google.com/maps?q=" + coordinates;

    // Prepare QR code content
    String text = "ID: " + request.getId() + "\n" +
                  "Name: " + request.getName() + "\n" +
                  "Location: " + coordinates + "\n" +
                  "Google Maps: " + googleMapsUrl + "\n" +
                  "Time: " + currentTime;

    // Set encoding options
    Hashtable<EncodeHintType, Object> hintMap = new Hashtable<>();
    hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
    hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

    // Generate the QR code matrix
    BitMatrix matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, 200, 200, hintMap);

    // Create an image from the matrix
    BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < 200; x++) {
        for (int y = 0; y < 200; y++) {
            // Set pixel color: black for true, white for false
            image.setRGB(x, y, matrix.get(x, y) ? 0x000000 : 0xFFFFFF);
        }
    }

    // Write the image to a byte array
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write(image, "PNG", baos);
    return baos.toByteArray();}

	@Override
	public String getCurrentCoordinates() {
		 double latitude = 40.7128;  // Example: New York City latitude
	        double longitude = -74.0060; // Example: New York City longitude
	        return latitude + "," + longitude;
	}
}