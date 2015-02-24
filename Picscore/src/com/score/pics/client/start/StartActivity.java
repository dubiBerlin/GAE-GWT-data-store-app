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
import com.score.pics.client.GAEEntryService;
import com.score.pics.client.GAEEntryServiceAsync;
import com.score.pics.client.GUIHelper;
import com.score.pics.client.events.StartAddTopic;
import com.score.pics.client.events.StartaddTopicEventHandler;
import com.score.pics.client.side2.SidePlace2;
import com.score.pics.client.widgets.AddTopicWidget;


public class StartActivity extends MGWTAbstractActivity {

	private ClientFactory clientFactory;
	private StartView view;
	private List<String>list;
	
//	private GAEEntryServiceAsync service = GWT.create(GAEEntryService.class);
	private EntryServiceAsync entryService = GWT.create(EntryService.class);
	
	public StartActivity(ClientFactory clientFactory){
		this.clientFactory = clientFactory;
	}
	
	
	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		view = clientFactory.getStartViewImpl();
		
		
		StartPlace place = (StartPlace)clientFactory.getPlaceController().getWhere();
		
		view.setHeaderText(place.getToken());
		
		getStartList(place.getToken());
		
		String sessionID = Cookies.getCookie("sid");
		
		if(sessionID!=null){
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
			
			
		}
		
		addHandlerRegistration(view.getCellList().addCellSelectedHandler(new CellSelectedHandler() {
			public void onCellSelected(CellSelectedEvent event) {
				
				int index = event.getIndex();
				
				clientFactory.getPlaceController().goTo(new SidePlace2(list.get(index)));
				
			}
		}));
		
		panel.setWidget(view);
	
	}
	
	
	private void getStartList(String username){
		
//		service.getTopicsOnStartSide(username, new AsyncCallback<List<String>>() {
//			public void onSuccess(List<String> result) {
//				list = result;
//				view.rendet(list);
//				view.refresh();
//				GUIHelper.setBackGroundColorInCellList(view.getCellListWidget(), list);
//			}
//			
//			public void onFailure(Throwable caught) {}
//		});
		
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
