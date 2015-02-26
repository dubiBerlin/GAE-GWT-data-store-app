package com.score.pics.client.side4;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/*
 * Place: 
 * */

public class SidePlace4  extends Place{

	private final String token;
	
	public SidePlace4(String id) {
		this.token = id;
	}
	
	public String getToken(){
		return token;
	}
	
	public static class SidePlaceTokenizer4 implements PlaceTokenizer<SidePlace4>{

		@Override
		public SidePlace4 getPlace(String id) {
			return new SidePlace4(id);
		}

		@Override
		public String getToken(SidePlace4 place) {
			return place.getToken();
		}
	}
}
