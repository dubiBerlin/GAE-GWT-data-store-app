package com.score.pics.client.widgets;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;
import com.googlecode.mgwt.ui.client.widget.dialog.ConfirmDialog.ConfirmCallback;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.dialog.overlay.DialogOverlay;
import com.score.pics.client.ClientFactory;
import com.score.pics.client.GAEDatastoreService;
import com.score.pics.client.GAEDatastoreServiceAsync;
import com.score.pics.client.events.DeleteEditShareEvent;
import com.score.pics.client.login.LoginPlace;


public class SettingsWidgetPresenter extends DialogOverlay {

	private EventBus eventBus;
	private ClientFactory clientFactory;
//	private EntryServiceAsync entryService = GWT.create(EntryService.class);
	private GAEDatastoreServiceAsync service = GWT.create(GAEDatastoreService.class);
	private SettingsView view;
	private boolean delete_it, edit_it, share_it;
	
	public SettingsWidgetPresenter(EventBus eventbus, final ClientFactory clientFactory) {

		this.clientFactory = clientFactory;
		this.eventBus = eventbus;
		
		view = new SettingsViewImpl();
		
		delete_it = false;
		edit_it = false;
		share_it = false;
		
		view.setDeleteValue(delete_it);
		view.setEditValue(edit_it);
		view.setShareValue(share_it);
		
		view.getCancelImageButton().addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				hide();
				eventBus.fireEvent(new DeleteEditShareEvent(delete_it, edit_it, share_it));
			}
		});
		
		final String sessionID = Cookies.getCookie("sid");
		
		view.getEditHandler().addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				if(edit_it){
					edit_it = false;
					view.setEditValue(edit_it);
				}else{
					edit_it = true;
					delete_it = false;
					share_it = false;
					setValuesForAllThree();
				}
			}
		});
		view.getDeleteHandler().addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				if(delete_it){
					delete_it = false;
					view.setDeleteValue(delete_it);
				}else{
					delete_it = true;
					edit_it = false;
					share_it = false;
					setValuesForAllThree();
				}
			}
		});
		
		view.getShareHandler().addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				if(share_it){
					share_it = false;
					view.setShareValue(share_it);
				}else{
					share_it = true;
					edit_it = false;
					delete_it= false;
					setValuesForAllThree();
				}
			}
		});
		
		view.getLogoutButton().addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				Dialogs.confirm("Ausloggen?", "",new ConfirmCallback() {
					public void onOk() {
							
						service.logout(new  AsyncCallback<Void>() {
							public void onSuccess(Void result) {
								hide();
								clientFactory.getPlaceController().goTo(new LoginPlace());
							}
							
							public void onFailure(Throwable caught) {
								Window.alert("Verbindungsproblem");
							}
						});
						
					}
					
					@Override
					public void onCancel() {
						Window.alert("Schön dass du noch da bleibst");
					}
				});
				
				
			}
		});
		
		
		add(view.asWidget());
		
	}


	@Override
	protected Animation getShowAnimation() {
		return Animations.DISSOLVE;
	}

	@Override
	protected Animation getHideAnimation() {
		return Animations.DISSOLVE_REVERSE;
	}

	
	public void setValuesForAllThree(){
		view.setDeleteValue(delete_it);
		view.setEditValue(edit_it);
		view.setShareValue(share_it);
	}

}
