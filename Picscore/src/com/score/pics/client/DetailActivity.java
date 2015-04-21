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
import com.googlecode.mgwt.ui.client.widget.dialog.ConfirmDialog.ConfirmCallback;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedHandler;
import com.score.pics.client.events.AddTopicSide2to5Event;
import com.score.pics.client.events.AddTopicSide2to5EventHandler;
import com.score.pics.client.events.DeleteEditShareEvent;
import com.score.pics.client.events.DeleteEditShareHandler;
import com.score.pics.client.widgets.AddTopicSide2to5Widget;
import com.score.pics.client.widgets.EditWidgetPresenter;
import com.score.pics.client.widgets.SettingsWidgetPresenter;
import com.score.pics.shared.CellContent;
import com.score.pics.shared.Sides2to5Entity;
import com.score.pics.shared.TitleContentSourceProperty;

public class DetailActivity extends MGWTAbstractActivity {

	private ClientFactory clientFactory;
	public List<CellContent>list;
	private DetailView view;
	private EventBus eventBus;
	public Sides2to5Entity se;
	
	private EntryServiceAsync service = GWT.create(EntryService.class);
	protected boolean delete;
	protected boolean edit;
	protected boolean share;
	
	
	public DetailActivity(ClientFactory clientFactory, DetailView detailView){
		this.clientFactory = clientFactory;
		this.view = detailView;
	}

	
	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		
		this.eventBus =  eventBus;
		
		
		/*Handler for the CellList*/
		addHandlerRegistration(view.getCellList().addCellSelectedHandler(new CellSelectedHandler() {
			public void onCellSelected(CellSelectedEvent event) {
				
				int index = event.getIndex();
				
				String place = list.get(index).getTitle();
				if(delete){
					Dialogs.confirm("Delete?", place, new ConfirmCallback() {
						public void onOk() {
//							removeItems(key);
//							deleteCategory(deletekey);
//							updateList();
						}
						public void onCancel() {}
					});
				}else{
					if(edit){
						
						CellContent cc = list.get(index);
						EditWidgetPresenter ewp = new EditWidgetPresenter(eventBus, clientFactory);
						ewp.setCellContent(cc);
						ewp.setSe(se);
						ewp.show();
						
					}else{
						if(share){
							Window.alert("Share: "+place);
						}else{
							
							goToNextPlace(place);
						}
					}
				}			
			}
		}));
		
	}

	public void setHandler(){
		addHandlerRegistration(view.getImageButton().addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				AddTopicSide2to5Widget ar = new AddTopicSide2to5Widget(eventBus, clientFactory);
				ar.setSe(se);
				ar.show();
				
			}
		}));
		
		addHandlerRegistration(view.getSettingsButton().addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				
				SettingsWidgetPresenter sw = new SettingsWidgetPresenter(eventBus, clientFactory);
				sw.show();
			}
		}));
		
		eventBus.addHandler(AddTopicSide2to5Event.TYPE, new AddTopicSide2to5EventHandler() {
			public void speichern(AddTopicSide2to5Event event) {
				if(event.getTce()!=null){
					String title = event.getTce().getTitle();
					String content = event.getTce().getContent();
					String source= event.getTce().getQuelle();
					
					CellContent cc = new CellContent(title, content, source);
					
					list.add(cc);
					
					view.render(list);
					view.refresh();
					GUIHelper.setBackGroundColorInCellList(view.getCellListWidget(), list);
					
				}else{
					Window.alert("Eintrag ist schon vorhanden");
				}
				
			}
		});
		
		eventBus.addHandler(DeleteEditShareEvent.TYPE, new DeleteEditShareHandler() {
			public void deleteOrEditCategory(DeleteEditShareEvent event) {
				
				delete = event.isDelete();
				edit = event.isEdit();
				share = event.isShare();
			}
		});
	}
	
	
	public void getStartList(String placeToken, String side){

		list = new ArrayList<CellContent>();
		
		se = new Sides2to5Entity();
		se.setUsername(clientFactory.getUserName());
		se.setTitle(placeToken);
		se.setSide(side);
		se.setAncestorPath(clientFactory.getAncestorPath());
	
		
		service.getTopicsFromSide2To5(se, new AsyncCallback<List<TitleContentSourceProperty>>() {
			public void onSuccess(List<TitleContentSourceProperty> result) {
				
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
	
	/*
	 * Must be implemented by the Side2-Side4 Activities because 
	 * of the goto(Places());
	 * */	
	public void goToNextPlace(String place) {}
	


	public void printValue() {	}
	
	
}
	

