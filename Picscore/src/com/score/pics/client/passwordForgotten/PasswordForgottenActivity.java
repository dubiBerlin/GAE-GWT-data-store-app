package com.score.pics.client.passwordForgotten;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.score.pics.client.ClientFactory;
import com.score.pics.client.GAEDatastoreService;
import com.score.pics.client.GAEDatastoreServiceAsync;
import com.score.pics.client.helper.GQueryHelper;
import com.score.pics.shared.Helper;
import com.score.pics.shared.StringResources;

public class PasswordForgottenActivity extends MGWTAbstractActivity {
	
	private ClientFactory clientFactory;
	private GAEDatastoreServiceAsync service = GWT.create(GAEDatastoreService.class);
	private PasswordForgottenView view;

	public PasswordForgottenActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getPasswordForgottenViewImpl();
		
		addHandlerRegistration(view.getBackButton().addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				History.back();
			}
		}));
		
		
		addHandlerRegistration(view.getSendButton().addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				
				if(view.getEmailAdress().length()>0 && view.getEmailAdress()!=null){
					
					if(Helper.isValidEmailAddress(view.getEmailAdress())){
						
						service.passwordForgotten(view.getEmailAdress(), new AsyncCallback<Boolean>() {
							public void onSuccess(Boolean result) {
								
								if(result.booleanValue()){

									view.setMessageColor(StringResources.getSuccessColor());
									GQueryHelper.fadeInFadeOut(StringResources.getIdOfResultLabel(), 
											StringResources.getErrorMessage(StringResources.getStringPasswordSendToEmail(view.getEmailAdress()), "childID"), "childID");
								}else{
									view.setMessageColor(StringResources.getErrorColor());
									GQueryHelper.fadeInFadeOut(StringResources.getIdOfResultLabel(), 
											StringResources.getErrorMessage(StringResources.getStringEmailNotFound(), "childID"), "childID");
								}
								
							}
							public void onFailure(Throwable caught) {}
						});
						
						
					}else{
						
						view.setMessageColor(StringResources.getErrorColor());
						GQueryHelper.fadeInFadeOut(StringResources.getIdOfResultLabel(), 
								StringResources.getErrorMessage(StringResources.getStringNotValidEmailAdress(), "childID"), "childID");
					}
				}else{
					
					view.setMessageColor(StringResources.getErrorColor());
					GQueryHelper.fadeInFadeOut(StringResources.getIdOfResultLabel(), 
							StringResources.getErrorMessage(StringResources.getStringCompleteTheInput(), "childID"), "childID");
				}
				
			}
		}));
		
		
		panel.setWidget(view);
	}

	/*
	 * Random password musst be created server side.
	 * No random number generation server side at that point so the random numbers have to be created client side and send to the server.
	 * Every number has to be casted into a string and has to be decrypted and put into a list and on the server side 
	 * every of these values have to be encrypted again.
	 * */
//	private List<String> createRandomNumber(){
//		
//		List<String> list = new ArrayList<String>();
//		
//		int length = 8; // musst be the length of the random password
//		
//		int size = StringResources.getABC123().length();//"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//		
////		String randomNumber = "";
//		
//		for(int i = 0; i < length; i++){
//			
//			String numberToString = String.valueOf(Random.nextInt(size));
//			
//			list.add(Helper.decryptPasswort(numberToString));
//		}
//		return list;
//	}
}
