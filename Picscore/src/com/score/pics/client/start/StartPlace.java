package com.score.pics.client.start;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class StartPlace extends Place {

	private String token;
	
	public StartPlace(String token) {
		this.token = token;
	}
	
	public String getToken(){
		return token;
	}
	
	
	public static class StartPlaceTokenizer implements PlaceTokenizer<StartPlace>{

		@Override
		public StartPlace getPlace(String token) {
			// TODO Auto-generated method stub
			return new StartPlace(token);
		}

		@Override
		public String getToken(StartPlace place) {
			// TODO Auto-generated method stub
			return place.getToken();
		}
		
	}

}
