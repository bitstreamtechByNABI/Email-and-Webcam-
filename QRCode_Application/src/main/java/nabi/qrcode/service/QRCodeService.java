package nabi.qrcode.service;

import nabi.qrcode.model.QRCodeRequest;

public interface QRCodeService {
	
	  byte[] generateQRCode(QRCodeRequest request) throws Exception;
	  String getCurrentCoordinates(); 
}
