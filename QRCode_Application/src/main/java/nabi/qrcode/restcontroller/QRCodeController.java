package nabi.qrcode.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nabi.qrcode.model.QRCodeRequest;
import nabi.qrcode.service.QRCodeService;

@RestController
@RequestMapping("/api/qrcode")
public class QRCodeController {
	
	 @Autowired
	 private QRCodeService qrCodeService;
	 
	 @PostMapping("/generate")
	    public ResponseEntity<byte[]> generateQRCode(@RequestBody QRCodeRequest request) {
	        try {
	           
	            byte[] qrCodeImage = qrCodeService.generateQRCode(request);

	          
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(org.springframework.http.MediaType.IMAGE_PNG);

	           
	            return ResponseEntity.ok().headers(headers).body(qrCodeImage);
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Error generating QR code".getBytes());
	        }
	    }

}
