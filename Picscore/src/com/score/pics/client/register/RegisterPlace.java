package com.score.pics.client.register;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class RegisterPlace extends Place {
	
	private String id;
	
	public RegisterPlace(String id){
		this.id = id;
	}
	
	public RegisterPlace(){}
	
	public String getToken(){
		return id;
	}
	

	public static class RegisterPlaceTokenizer implements PlaceTokenizer<RegisterPlace>{

		@Override
		public RegisterPlace getPlace(String token) {
			return new RegisterPlace(token);
		}

		@Override
		public String getToken(RegisterPlace place) {
			return place.getToken();
		}
		
	}
	
	
}
