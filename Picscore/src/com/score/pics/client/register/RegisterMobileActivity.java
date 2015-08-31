package com.score.pics.client.register;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Timer;
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
import com.score.pics.client.login.LoginPlace;
import com.score.pics.shared.Helper;
import com.score.pics.shared.StringResources;
import com.score.pics.shared.User;

public class RegisterMobileActivity extends MGWTAbstractActivity {

	private ClientFactory clientFactory;
	private RegisterMobileView view;
	
	private GAEDatastoreServiceAsync service = GWT.create(GAEDatastoreService.class);
	
	
	public RegisterMobileActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		
		view = clientFactory.getRegisterMobileView();
		
		addHandlerRegistration(view.getButton().addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				
				User user = new User();
				String username = view.getUsername().trim();
				String email = view.getEmail().trim();
				String pw = view.getPassword().trim();
				String pw_repeat = view.getPasswordRepeat().trim();
				
				if(pw.equals(pw_repeat)){
					if(username!=null && !username.equals("")){
						if(email!=null && !email.equals("")){
							String password = Helper.encryptPasswort(view.getPassword(), StringResources.getGwtDesKey());
							
							
							user.setUsername(username);
							user.setEmail(email);
							user.setPassword(password);
							
							service.registerUser(user, new AsyncCallback<Boolean>() {
								public void onSuccess(Boolean result) {
									
									if(result.booleanValue()){
										GQueryHelper.fadeInFadeOut("messageID", 
												StringResources.getSuccesMessage("Die Registrierung verlief erfolgreich", "id"), "id");
									
										Timer t = new Timer(){
											public void run() {
												clientFactory.getPlaceController().goTo(new LoginPlace());
											};
										};
										t.schedule(3000);
									}
									else{
										GQueryHelper.fadeInFadeOut(
												"messageID", 
												StringResources.getErrorMessage("Überprüfe die eingegebenen Daten", "childID"), 
												"childID"
										);
									}
									
								}
								
								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub
									
								}
							});
						}else{
							GQueryHelper.fadeInFadeOut("messageID", 
									StringResources.getErrorMessage("Die Email ist nicht korrekt", "childID"), "childID");
						}
					}else{
						GQueryHelper.fadeInFadeOut("messageID", 
								StringResources.getErrorMessage("Der Benutzername ist nicht korrekt", "childID"), "childID");
					}
				}else{
					GQueryHelper.fadeInFadeOut("messageID", 
							StringResources.getErrorMessage("Passwort stimmt nicht überein", "childID"), "childID");
				}
				
				
				
				
			}
		}));
		
//		view.getButtonClick().addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				String text = view.getText();
//				
//				
//				
//				if(text!=null || !text.trim().equals("")){
//					service.extecuteServlet(text, new AsyncCallback<String>() {
//						
//						@Override
//						public void onSuccess(String result) {
//							Window.alert("result:"+result);
//							view.setMessage(result);
//						}
//						
//						@Override
//						public void onFailure(Throwable caught) {
//							// TODO Auto-generated method stub
//							
//						}
//					});
//				}
//			}
//		});
		
		panel.setWidget(view);
		
	}
}
