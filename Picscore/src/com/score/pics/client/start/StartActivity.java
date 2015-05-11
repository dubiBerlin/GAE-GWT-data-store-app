package com.score.pics.client.start;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedHandler;
import com.score.pics.client.ClientFactory;
import com.score.pics.client.EntryService;
import com.score.pics.client.EntryServiceAsync;
import com.score.pics.client.GAEDatastoreService;
import com.score.pics.client.GAEDatastoreServiceAsync;
import com.score.pics.client.events.StartAddTopic;
import com.score.pics.client.events.StartaddTopicEventHandler;
import com.score.pics.client.helper.GUIHelper;
import com.score.pics.client.login.LoginPlace;
import com.score.pics.client.side2.SidePlace2;
import com.score.pics.client.widgets.AddTopicWidget;
import com.score.pics.shared.LoginUser;


public class StartActivity extends MGWTAbstractActivity {

	private ClientFactory clientFactory;
	private StartView view;
	private List<String>list;
	
//	private GAEEntryServiceAsync service = GWT.create(GAEEntryService.class);
	private EntryServiceAsync entryService = GWT.create(EntryService.class);
	private GAEDatastoreServiceAsync service = GWT.create(GAEDatastoreService.class);
	
	public StartActivity(ClientFactory clientFactory){
		this.clientFactory = clientFactory;
	}
	
	
	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
	
		
		view = clientFactory.getStartViewImpl();
		
		StartPlace place = (StartPlace)clientFactory.getPlaceController().getWhere();
		
		view.setHeaderText(place.getToken());
		
		clientFactory.pushHistoryTracker(clientFactory.getUserName());
				
		getStartList(place.getToken());
		
		String sessionID = Cookies.getCookie("sid");
		
		if(sessionID!=null){
			service.checkSessionID(sessionID, new AsyncCallback<LoginUser>() {
				public void onSuccess(LoginUser result) {
					
					if(result.isLoggedIn()){
					
						addHandlerRegistration(view.getImageButton().addTapHandler(new TapHandler() {
							public void onTap(TapEvent event) {
								AddTopicWidget ar = new AddTopicWidget(eventBus, clientFactory);
								ar.show();
							}
						}));
						
						eventBus.addHandler(StartAddTopic.TYPE, new StartaddTopicEventHandler(){
							public void speichern(StartAddTopic event) {
								
								list.add(event.getTopic());
								
								//view.clear();
								
								view.rendet(list);
								
								view.refresh();
								
								GUIHelper.setBackGroundColorInCellList(view.getCellListWidget(), list);
							}
							
						});
						
						addHandlerRegistration(view.getCellList().addCellSelectedHandler(new CellSelectedHandler() {
							public void onCellSelected(CellSelectedEvent event) {
								
								int index = event.getIndex();
								//cl
								clientFactory.getPlaceController().goTo(new SidePlace2(list.get(index)));
								
							}
						}));
						
						panel.setWidget(view);
					
					}else{
						clientFactory.getPlaceController().goTo(new LoginPlace());
						
						
					}
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Verbindungsproblem");
				}
			});
			
			
		}
		else{
			Window.alert("SessionID == null");
			clientFactory.getPlaceController().goTo(new LoginPlace());
		}
		
	
	}
	
	
	private void getStartList(String username){
		
		entryService.getTopicsStartSide(username, new AsyncCallback<List<String>>() {
			
			@Override
			public void onSuccess(List<String> result) {
				
				list = result;
				view.rendet(list);
				view.refresh();
				GUIHelper.setBackGroundColorInCellList(view.getCellListWidget(), list);
			
			}
			public void onFailure(Throwable caught) {}
		});
		
	}
	

}
