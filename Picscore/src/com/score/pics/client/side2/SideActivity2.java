package com.score.pics.client.side2;

import java.util.ArrayList;
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
import com.score.pics.client.events.AddTopicSide2to5Event;
import com.score.pics.client.events.AddTopicSide2to5EventHandler;
import com.score.pics.client.events.StartAddTopic;
import com.score.pics.client.events.StartaddTopicEventHandler;
import com.score.pics.client.side3.SidePlace3;
import com.score.pics.client.widgets.AddTopicWithTitle;
import com.score.pics.shared.AppResources;
import com.score.pics.shared.CellContent;
import com.score.pics.shared.Sides2to5Entity;
import com.score.pics.shared.TitleContentEntry;

public class SideActivity2 extends MGWTAbstractActivity {


	private SideView2 view;
	private ClientFactory clientFactory;
	private List<CellContent>list;
	
	//private GAEEntryServiceAsync service = GWT.create(GAEEntryService.class);
	private EntryServiceAsync service = GWT.create(EntryService.class);
	
	public SideActivity2(ClientFactory clientFactory){
		this.clientFactory = clientFactory;
	}

	
	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		
		view = clientFactory.getSide2ViewImpl();
		
		final SidePlace2 place = (SidePlace2)clientFactory.getPlaceController().getWhere();
		
		view.setHeaderTitle(place.getToken());
		
		list = new ArrayList<CellContent>();
		
		
		
		final Sides2to5Entity se = new Sides2to5Entity();
		se.setUsername(clientFactory.getUserName());
		se.setEintrag(place.getToken());
		se.setSide(AppResources.side2Identifier());
		
		service.getTopicsFromSide2To5(se, new AsyncCallback<List<TitleContentEntry>>() {
			public void onSuccess(List<TitleContentEntry> result) {
				
				for(int i = 0; i < result.size(); i++){
					String title = result.get(i).getTitle();
					String content = result.get(i).getContent();
					String source = result.get(i).getQuelle();
					CellContent cc = new CellContent(title, content, source);
					list.add(cc);
				}
				
				view.render(list);
				view.refresh();
				
			}
			public void onFailure(Throwable caught) {}
		});

		String sessionID = Cookies.getCookie("sid");
		
		if(sessionID!=null){
			addHandlerRegistration(view.getImageButton().addTapHandler(new TapHandler() {
				public void onTap(TapEvent event) {
					AddTopicWithTitle ar = new AddTopicWithTitle(se, eventBus, clientFactory);
					ar.show();
				}
			}));
			
			addHandlerRegistration(view.getSettingsButton().addTapHandler(new TapHandler() {
				public void onTap(TapEvent event) {
					
					Window.alert("Settings");
				}
			}));
			
			eventBus.addHandler(StartAddTopic.TYPE, new StartaddTopicEventHandler(){
				public void speichern(StartAddTopic event) {
//					view.clear();
//					view.rendet(list);
//					view.refresh();
//					GUIHelper.setBackGroundColorInCellList(view.getCellListWidget(), list);
				}
			});
			
			eventBus.addHandler(AddTopicSide2to5Event.TYPE, new AddTopicSide2to5EventHandler() {
				public void speichern(AddTopicSide2to5Event event) {
					
					String title = event.getTce().getTitle();
					String content = event.getTce().getContent();
					String source= event.getTce().getQuelle();
					
					CellContent cc = new CellContent(title, content, source);
					
					list.add(cc);
					
					view.render(list);
					view.refresh();
					GUIHelper.setBackGroundColorInCellList(view.getCellListWidget(), list);
					
				}
			});
			
			addHandlerRegistration(view.getCellList().addCellSelectedHandler(new CellSelectedHandler() {
				public void onCellSelected(CellSelectedEvent event) {
					
					String token = list.get(event.getIndex()).getTitle();
					
					clientFactory.getPlaceController().goTo(new SidePlace3(token));
					
				}
			}));
		}
		
		GUIHelper.setBackGroundColorInCellList(view.getCellListWidget(), list);
		
		panel.setWidget(view);
		
	}
	

	
	
	
}
