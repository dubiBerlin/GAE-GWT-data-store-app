package com.score.pics.client.widgets;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;
import com.googlecode.mgwt.ui.client.widget.dialog.overlay.DialogOverlay;
import com.score.pics.client.ClientFactory;
import com.score.pics.client.EntryService;
import com.score.pics.client.EntryServiceAsync;

public class SettingsWidget  extends DialogOverlay {


	private EventBus eventBus;
	private ClientFactory clientFactory;
	private EntryServiceAsync entryService = GWT.create(EntryService.class);
	
	public SettingsWidget(EventBus evenbus, final ClientFactory clientFactory) {

		this.clientFactory = clientFactory;
		this.eventBus = evenbus;
		
		final SettingsView view = new SettingsViewImpl();
		
		view.getCancelImageButton().addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				hide();
			}
		});
		
		final String sessionID = Cookies.getCookie("sid");
		
		if(sessionID!=null){
//			view.getSaveButton().addTapHandler(new TapHandler() {
//				public void onTap(TapEvent event) {
//					final String topik = view.getTopic();
//					
//					if(topik!=null && !topik.equals("")){
//						
//						entryService.createEntryOnStartseite(topik, clientFactory.getUserName(), new AsyncCallback<String>() {
//							public void onSuccess(String result) {
//								if(result.equals(topik)){
//									eventBus.fireEvent(new StartAddTopic(result));
//								}else{
//									view.setMessage("Etwas ging schief");
//								}
//								
//							}
//							public void onFailure(Throwable caught) {
//								// TODO Auto-generated method stub
//								
//							}
//						});						
//					}
//					else{
//						view.setMessage("Eingabe fehlt");
//					}
//				}
//			});
		}
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

	

}
