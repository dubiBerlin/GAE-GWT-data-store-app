package com.score.pics.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.score.pics.client.widgets.AddTopicWithTitle;
import com.score.pics.shared.CellContent;

public class DetailActivity extends MGWTAbstractActivity {

	private ClientFactory clientFactory;
	private List<CellContent>list;
	private DetailView view;
	private EventBus eventBus;
	public String token;
	
	private GAEEntryServiceAsync service = GWT.create(GAEEntryService.class);
	
	
	public DetailActivity(ClientFactory clientFactory, DetailView detailView){
		this.clientFactory = clientFactory;
		this.view = detailView;
	}

	
	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		
		this.eventBus =  eventBus;
		
//		String sessionID = Cookies.getCookie("sid");
//		
//		if(sessionID!=null){
//			addHandlerRegistration(view.getImageButton().addTapHandler(new TapHandler() {
//				public void onTap(TapEvent event) {
//					AddTopicWithTitle ar = new AddTopicWithTitle(token, eventBus, clientFactory);
//					ar.show();
//				}
//			}));
//			
//			addHandlerRegistration(view.getSettingsButton().addTapHandler(new TapHandler() {
//				public void onTap(TapEvent event) {
//					
//					Window.alert("Settings");
//				}
//			}));
			
		
	}

	public void setHandler(){
		addHandlerRegistration(view.getImageButton().addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
//				AddTopicWithTitle ar = new AddTopicWithTitle(token, eventBus, clientFactory);
//				ar.show();
			}
		}));
		
		addHandlerRegistration(view.getSettingsButton().addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				
				Window.alert("Settings");
			}
		}));
	}
	

	private List<CellContent>getList(){
		this.list = new ArrayList<CellContent>();
		
		
		
		return list;
	}
}
	

