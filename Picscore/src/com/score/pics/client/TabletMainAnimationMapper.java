package com.score.pics.client;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.mvp.client.AnimationMapper;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;
import com.score.pics.client.login.LoginPlace;
import com.score.pics.client.register.RegisterPlace;

public class TabletMainAnimationMapper implements AnimationMapper {

	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {
		
		if(newPlace instanceof RegisterPlace && oldPlace instanceof LoginPlace){
			return Animations.SLIDE;
		}
		if(newPlace instanceof LoginPlace && oldPlace instanceof RegisterPlace){
			return Animations.SLIDE_REVERSE;
		}
		
		
		
		return null;
	}

}
