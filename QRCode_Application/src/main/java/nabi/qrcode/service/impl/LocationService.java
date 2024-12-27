package nabi.qrcode.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationService {
	
	private static final String GEOLOCATION_API_URL = "https://ipinfo.io/json?token=016ee523b640c6";
	 public String getCurrentLocation() {
	     
	        RestTemplate restTemplate = new RestTemplate();
	        
	       
	        ResponseEntity<String> response = restTemplate.getForEntity(GEOLOCATION_API_URL, String.class);

	      
	        if (response.getStatusCode().is2xxSuccessful()) {
	            return response.getBody(); // Return the JSON response as a string (location details)
	        } else {
	            return "Unable to fetch location"; // Handle errors if needed
	        }
	    }

}
