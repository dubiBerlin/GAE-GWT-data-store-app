package com.score.pics.shared;

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
}
