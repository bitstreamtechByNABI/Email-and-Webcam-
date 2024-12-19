package com.email.self.service;

public interface EmailMethod {
	
	String shopping(String[] item, Double[] price, String[] emailId) throws Exception;

}
