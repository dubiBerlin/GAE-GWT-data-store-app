package com.score.pics.client.register;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
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
import com.score.pics.shared.StringResources;
import com.score.pics.shared.Helper;
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
				
				String firstname = view.getFirstname();
				String lastname = view.getLastname();
				String username = view.getUsername();
				String email = view.getEmail();
				String password = Helper.encryptPasswort(view.getPassword(), StringResources.getGwtDesKey());
				
				
				user.setFirstname(firstname);
				user.setLastname(lastname);
				user.setUsername(username);
				user.setEmail(email);
				user.setPassword(password);
				
				service.registerUser(user, new AsyncCallback<String>() {
					public void onSuccess(String result) {
						
						Window.alert(result);
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
				
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
