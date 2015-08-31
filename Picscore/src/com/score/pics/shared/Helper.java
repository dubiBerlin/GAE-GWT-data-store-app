package com.score.pics.shared;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.googlecode.gwt.crypto.bouncycastle.DataLengthException;
import com.googlecode.gwt.crypto.bouncycastle.InvalidCipherTextException;
import com.googlecode.gwt.crypto.client.TripleDesCipher;

public class Helper {

	public Helper() {}

	
	public static String encryptPasswort(String pw, byte[]keys){
		
		byte[]GWT_DES_KEY = keys;
		
		TripleDesCipher cipher = new TripleDesCipher();
	    cipher.setKey(GWT_DES_KEY);
	    String dec =pw;
	    
	    try {
	    	dec = cipher.encrypt(dec);
	    } catch (DataLengthException e) {
	    	e.printStackTrace();
	    } catch (IllegalStateException e) {
	    	e.printStackTrace();
	    } catch (InvalidCipherTextException e) {
	    	e.printStackTrace();
	    }
	    return dec;
	}
	
	
	public static String encryptPasswort(String pw){
		
		byte[]GWT_DES_KEY = StringResources.getGwtDesKey();
		
		TripleDesCipher cipher = new TripleDesCipher();
		cipher.setKey(GWT_DES_KEY);
		String dec =pw;
		
		try {
			dec = cipher.encrypt(dec);
		} catch (DataLengthException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (InvalidCipherTextException e) {
			e.printStackTrace();
		}
		return dec;
	}
	
	public static String decryptPasswort(String pw, byte[]keys){
		
		byte[]GWT_DES_KEY = keys;
		
		TripleDesCipher cipher = new TripleDesCipher();
		cipher.setKey(GWT_DES_KEY);
		String dec =pw;
		
		try {
			dec = cipher.decrypt(dec);
		} catch (DataLengthException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (InvalidCipherTextException e) {
			e.printStackTrace();
		}
		return dec;
	}
	
	public static String decryptPasswort(String pw){
		
		byte[]GWT_DES_KEY = StringResources.getGwtDesKey();
		
		TripleDesCipher cipher = new TripleDesCipher();
		cipher.setKey(GWT_DES_KEY);
		String dec =pw;
		
		try {
			dec = cipher.decrypt(dec);
		} catch (DataLengthException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (InvalidCipherTextException e) {
			e.printStackTrace();
		}
		return dec;
	}
	
	  public static boolean isValidEmailAddress(String email) {
          String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
          
          RegExp regExp = RegExp.compile(ePattern); 
          MatchResult matcher = regExp.exec(email);
          
          boolean matcherFound = matcher != null;
          
          if(matcherFound){
        	  return true;
          }else{
        	  return false; 
          }
          
   }
}
