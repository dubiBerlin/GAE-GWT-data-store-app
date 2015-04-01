package com.score.pics.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.widget.animation.AnimationWidget;
import com.score.pics.client.login.LoginPlace;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Picscore implements EntryPoint {
	
	public void onModuleLoad() {
		
		MGWT.applySettings(MGWTSettings.getAppSetting());
		final ClientFactory clientFactory = new ClientFactoryImpl();
		createPhoneDisplay(clientFactory);
		AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(clientFactory.getPlaceController(), clientFactory.getEventBus(), new LoginPlace());
		historyHandler.handleCurrentHistory();		
	}
	
	private void createPhoneDisplay(final ClientFactory clientFactory ){
		AnimationWidget display = new AnimationWidget();
		
		PhoneActivityMapper appActivityMapper = new PhoneActivityMapper(clientFactory);
		PhoneAnimationMapper appAnimationMapper = new PhoneAnimationMapper();
		
		AnimatingActivityManager activityManager = new AnimatingActivityManager(appActivityMapper, appAnimationMapper, clientFactory.getEventBus());
		
		activityManager.setDisplay(display);
		
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				clientFactory.deleteLastTokenAncestorPath();
				clientFactory.deleteLastTokenAncestorPath();
			}
		});
		
		RootPanel.get().add(display);
		
	}
}
