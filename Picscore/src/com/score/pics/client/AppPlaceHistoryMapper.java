package com.score.pics.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.score.pics.client.login.LoginPlace;
import com.score.pics.client.register.RegisterPlace;
import com.score.pics.client.side2.SidePlace2;
import com.score.pics.client.side3.SidePlace3;
import com.score.pics.client.side4.SidePlace4;
import com.score.pics.client.side5.SidePlace5;
import com.score.pics.client.start.StartPlace;

@WithTokenizers({
	RegisterPlace.RegisterPlaceTokenizer.class,
	LoginPlace.LoginPlaceTokenizer.class,
	StartPlace.StartPlaceTokenizer.class,
	SidePlace2.SidePlaceTokenizer2.class,
	SidePlace3.SidePlaceTokenizer3.class,
	SidePlace4.SidePlaceTokenizer4.class,
	SidePlace5.SidePlaceTokenizer5.class
	
})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {

}
