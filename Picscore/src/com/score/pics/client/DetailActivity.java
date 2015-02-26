package com.score.pics.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedHandler;
import com.score.pics.client.events.AddTopicSide2to5Event;
import com.score.pics.client.events.AddTopicSide2to5EventHandler;
import com.score.pics.client.widgets.AddTopicSide2to5Widget;
import com.score.pics.shared.CellContent;
import com.score.pics.shared.Sides2to5Entity;
import com.score.pics.shared.TitleContentEntry;

public class DetailActivity extends MGWTAbstractActivity {

	private ClientFactory clientFactory;
	public List<CellContent>list;
	private DetailView view;
	private EventBus eventBus;
	public Sides2to5Entity se;
	
	private EntryServiceAsync service = GWT.create(EntryService.class);
	
	
	public DetailActivity(ClientFactory clientFactory, DetailView detailView){
		this.clientFactory = clientFactory;
		this.view = detailView;
	}

	
	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		
		this.eventBus =  eventBus;
		
		addHandlerRegistration(view.getCellList().addCellSelectedHandler(new CellSelectedHandler() {
			public void onCellSelected(CellSelectedEvent event) {
				
				String place = list.get(event.getIndex()).getTitle();
				
				goToNextPlace(place);
				
			}

			
		}));
		
	}

	public void setHandler(){
		addHandlerRegistration(view.getImageButton().addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				AddTopicSide2to5Widget ar = new AddTopicSide2to5Widget(se, eventBus, clientFactory);
				ar.show();
				
			}
		}));
		
		addHandlerRegistration(view.getSettingsButton().addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				
				Window.alert("Settings");
			}
		}));
		
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
		
		
	}
	
	
	public void getStartList(String placeToken, String side){
		
		list = new ArrayList<CellContent>();
		
		se = new Sides2to5Entity();
		se.setUsername(clientFactory.getUserName());
		se.setEintrag(placeToken);
		se.setSide(side);
		
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
				GUIHelper.setBackGroundColorInCellList(view.getCellListWidget(), list);
			}
			public void onFailure(Throwable caught) {}
		});
		
	}
	
	public void goToNextPlace(String place) {}
}
	

