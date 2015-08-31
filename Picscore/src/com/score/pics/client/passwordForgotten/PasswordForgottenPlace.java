package com.score.pics.client.passwordForgotten;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PasswordForgottenPlace extends Place {


	private String id;
	
	public PasswordForgottenPlace(String id){
		this.id = id;
	}

	public String getToken(){
		return id;
	}
	
	public static class PasswordForgottenPlaceTokenizer  implements PlaceTokenizer<PasswordForgottenPlace>{

		@Override
		public PasswordForgottenPlace getPlace(String token) {
			return new PasswordForgottenPlace(token);
		}

		@Override
		public String getToken(PasswordForgottenPlace place) {
			// TODO Auto-generated method stub
			return place.getToken();
		} 
		
	}
	
}
