package com.score.pics.client.login;

import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.score.pics.client.ClientFactory;
import com.score.pics.client.GAEDatastoreService;
import com.score.pics.client.GAEDatastoreServiceAsync;
import com.score.pics.client.register.RegisterPlace;
import com.score.pics.client.start.StartPlace;
import com.score.pics.shared.Helper;
import com.score.pics.shared.LoginUser;
import com.score.pics.shared.StringResources;

public class LoginActivity extends MGWTAbstractActivity {

	
	public LoginView view;
	public ClientFactory clientFactory; 
		
	private GAEDatastoreServiceAsync service = GWT.create(GAEDatastoreService.class);
	
	
	public LoginActivity(ClientFactory clientFactory){
		this.clientFactory = clientFactory;
	}
	
	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		
		view = clientFactory.getLoginViewImpl();
		
		String sessionID = Cookies.getCookie("sid");
		
		/*
		 * Ist Cookie gesetzt navigiere zur Start Seite
		 * */
		if(sessionID!=null){
			service.checkSessionID(sessionID, new AsyncCallback<LoginUser>() {
				public void onSuccess(LoginUser result) {
					
					if(result.isLoggedIn()){
						clientFactory.setUserName(result.getUsername());
						clientFactory.getPlaceController().goTo(new StartPlace(result.getUsername()));
					}else{
						buildGUI(panel);
					}
				}
				
				@Override
				public void onFailure(Throwable caught) {
					buildGUI(panel);
				}
			});
		}else{
			buildGUI(panel);
		}
	}
	
	private void buildGUI(AcceptsOneWidget panel){
		addHandlerRegistration(view.getLoginButton().addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				
				if(view.getUsername()!=null || !view.getUsername().trim().equals("")){
					if(view.getPassword()!=null || !view.getPassword().trim().equals("")){
						
						String username = view.getUsername();
						String password = Helper.encryptPasswort(view.getPassword(), StringResources.getGwtDesKey());
						
						executeLogin(username, password);
						
					}
				}				
			}
		}));
		
		addHandlerRegistration(view.getRegisterButton().addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				
				clientFactory.getPlaceController().goTo(new RegisterPlace());
				
			}
		}));
		panel.setWidget(view);
	}
	
	/*
	 * 
	 * 
	 * */
	private void executeLogin(String username, String pw){
		LoginUser ld = new LoginUser();
		ld.setUsername(username);
		ld.setPassword(pw);
		
		service.login(ld, new AsyncCallback<LoginUser>() {
			public void onSuccess(LoginUser result) {
				
				if(result.isLoggedIn()){
					
					final long DURATION = 1000*60*60*24*1;
					
					Date expires = new Date(System.currentTimeMillis() + DURATION);
					
					String sessionID = result.getSessionID();
					
					Cookies.setCookie("sid", sessionID, expires, null, "/", false);
					clientFactory.setUserName(result.getUsername());
					clientFactory.getPlaceController().goTo(new StartPlace(view.getUsername()));
				
				}else{
					view.setResult("Username oder Passwort sind falsch");
				}
			}
			public void onFailure(Throwable caught) {}
		});
	}

}
