package com.score.pics.client.widgets;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.score.pics.client.ClientFactory;
import com.score.pics.client.GAEDatastoreService;
import com.score.pics.client.GAEDatastoreServiceAsync;
import com.score.pics.client.helper.GQueryHelper;
import com.score.pics.shared.ChangePasswordDTO;
import com.score.pics.shared.Helper;
import com.score.pics.shared.StringResources;

public class ChangePasswordWidgetPresenter {
	
	private EventBus eventBus;
	private ChangePasswordView view;
	
	
	private GAEDatastoreServiceAsync service = GWT.create(GAEDatastoreService.class);
	
	
	
	
	public ChangePasswordWidgetPresenter(EventBus eventbus, final ClientFactory clientFactory) {

		this.eventBus = eventbus;
		
		view = clientFactory.getChangePasswordViewImpl();
		
		view.getCloseButton().addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				view.hide();
			}
		});
		
		view.getSaveButton().addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				
				if(view.getOldPassword().trim().length()>0 && view.getNewPassword().trim().length()>0 && view.getNewPasswordRepeat().equals(view.getNewPassword())){
					
					String username = clientFactory.getUserName();
					String oldPassword = Helper.encryptPasswort(view.getOldPassword(), StringResources.getGwtDesKey());
					String newPassword = Helper.encryptPasswort(view.getNewPassword(), StringResources.getGwtDesKey());
					String newPasswordRepeat = Helper.encryptPasswort(view.getNewPasswordRepeat(), StringResources.getGwtDesKey());
					
					
					ChangePasswordDTO cpDTO = new ChangePasswordDTO();
					cpDTO.setUsername(username);
					cpDTO.setOldPassword(oldPassword);
					cpDTO.setNewPassword(newPassword);
					cpDTO.setNewPasswordRepeat(newPasswordRepeat);
					
					service.changePassword(cpDTO, new AsyncCallback<Boolean>() {
						public void onSuccess(Boolean result) {
							if(result){
								
								view.setResultMessageColor("#3498DB");
								
								GQueryHelper.fadeInFadeOut(StringResources.getIdOfResultLabel(), 
										StringResources.getErrorMessage(StringResources.getPasswordChangedSuccessfully(), "childID"), "childID");
							}else{
							}
							
						}
						
						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}
					});
				}else{
					view.setResultMessageColor("red");
					GQueryHelper.fadeInFadeOut(
							StringResources.getIdOfResultLabel(), 
							StringResources.getErrorMessage(StringResources.getCheckInput(), 
							"childID"), 
					"childID");
				}
			}
		});
	}

	
	
	public void show(){
		view.show();
	}
}
