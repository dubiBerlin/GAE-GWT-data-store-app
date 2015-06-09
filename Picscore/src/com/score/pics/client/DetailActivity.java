
package com.score.pics.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
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
import com.score.pics.client.helper.GUIHelper;
import com.score.pics.client.login.LoginPlace;
import com.score.pics.client.widgets.AddTopicSide2to5Widget;
import com.score.pics.client.widgets.EditWidgetPresenter;
import com.score.pics.client.widgets.SettingsWidgetPresenter;
import com.score.pics.shared.CellContent;
import com.score.pics.shared.LoginUser;
import com.score.pics.shared.Sides2to5EntityDTO;
import com.score.pics.shared.TitleContentSourcePropertyDTO;

public class DetailActivity extends MGWTAbstractActivity {

	private ClientFactory clientFactory;
	public List<CellContent>list;
	private DetailView view;
	private EventBus eventBus;
	public Sides2to5EntityDTO se;
	public String placeToken, side;
	
	private EntryServiceAsync service = GWT.create(EntryService.class);

	private GAEDatastoreServiceAsync service2 = GWT.create(GAEDatastoreService.class);
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
		
		String sessionID = Cookies.getCookie("sid");
		
		if(sessionID!=null){
			
			service2.checkSessionID(sessionID, new AsyncCallback<LoginUser>() {
				public void onSuccess(LoginUser result) {
					
					if(result.isLoggedIn()){
						
						getStartList(placeToken, side);
						
						/*Handler for the CellList*/
						addHandlerRegistration(view.getCellList().addCellSelectedHandler(new CellSelectedHandler() {
							public void onCellSelected(CellSelectedEvent event) {
								
								final int index = event.getIndex();
								
								String place = list.get(index).getTitle();
								if(delete){
									Dialogs.confirm("Delete?", place, new ConfirmCallback() {
										public void onOk() {
											delete(index);
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
											
											/*
											 * Just redirect to the next side as example from
											 *  Side2 to Side3
											 * */
											goToNextPlace(place);
										}
									}
								}			
							}
						}));
					}else{
						clientFactory.getPlaceController().goTo(new LoginPlace());
					}
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Verbindungsproblem "+caught.getMessage());
				}
			});
			
			
		}else{
			clientFactory.getPlaceController().goTo(new LoginPlace());
		}
		
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
					getStartList( placeToken,  side);
					
				}else{
					Window.alert("Der Eintrag ist schon vorhanden");
				}
				
			}
		});
		
		/*
		 * This event is fired after the user presses the close button.
		 * It provides three boolean values in order to know what has to happen after
		 * the user presses on a cell of the cellList. There are only three options
		 * of choice delete, edit and share. Only one can be true.
		 * */
		eventBus.addHandler(DeleteEditShareEvent.TYPE, new DeleteEditShareHandler() {
			public void deleteOrEditCategory(DeleteEditShareEvent event) {
				
				delete = event.isDelete();
				edit = event.isEdit();
				share = event.isShare();
			}
		});
		
		/* If the user navigates back to the previous side through pressing
		 * on the back button, the current list of the current side will be deleted
		 * because if the user navigates again from the previous side to the
		 * next side, the old entries are visible for a second.*/
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			public void onValueChange(ValueChangeEvent<String> event) {
				list = new ArrayList<CellContent>();
			}
		});

	}
	
	private void delete(final int index){
		
		TitleContentSourcePropertyDTO tcp = new TitleContentSourcePropertyDTO();
		tcp.setTitle(list.get(index).getTitle());
		tcp.setContent(list.get(index).getContent());
		tcp.setQuelle(list.get(index).getSource());
		
		service.delete(se, tcp, new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean bool) {
				if(bool.booleanValue()){
					list.remove(index);
					refreshList();
				}else{
					Window.alert("FALSE");
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Verbindungsproblem");	
			}
		});
	}
	
	
	public void getStartList(String placeToken, String side){

		list = new ArrayList<CellContent>();
		
		se = new Sides2to5EntityDTO();
		se.setUsername(clientFactory.getUserName());
		se.setTitle(placeToken);
		se.setSide(side);
		se.setAncestorPath(clientFactory.getAncestorPath());
	
		
		service.getTopicsFromSide2To5(se, new AsyncCallback<List<TitleContentSourcePropertyDTO>>() {
			public void onSuccess(List<TitleContentSourcePropertyDTO> result) {
				
				for(int i = 0; i < result.size(); i++){
					String title = result.get(i).getTitle()+"";
					String content = result.get(i).getContent()+"";
					String source = result.get(i).getQuelle()+"";
					CellContent cc = new CellContent(title, content, source);
					
					list.add(cc);
				}
				refreshList();
			}
			public void onFailure(Throwable caught) {}
		});
		
	}
	
	
//	public void getStartList(String placeToken, String side){
//		
//		list = new ArrayList<CellContent>();
//		
//		for(int i = 0; i < 10; i++){
//			String title = "title "+i;
//			String content = "content "+i;
//			String source = "source "+i;
//			CellContent cc = new CellContent(title, content, source);
//			list.add(cc);
//		}
//		
//		refreshList();
//		
//	}
	
	/*
	 * Must be implemented by the Side2-Side4 Activities because 
	 * of the goto(Places());
	 * */	
	public void goToNextPlace(String place) {}
	
	private void refreshList(){
		view.render(list);
		view.refresh();
		GUIHelper.setBackGroundColorInCellList(view.getCellListWidget(), list);
	}

	
	
}