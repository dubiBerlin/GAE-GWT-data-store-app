package com.score.pics.client.widgets;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.dialog.overlay.DialogOverlay;
import com.score.pics.client.ClientFactory;
import com.score.pics.client.EntryService;
import com.score.pics.client.EntryServiceAsync;
import com.score.pics.client.events.AddTopicSide2to5Event;
import com.score.pics.shared.Sides2to5EntityDTO;
import com.score.pics.shared.TitleContentSourcePropertyDTO;

public class AddTopicSide2to5Widget extends DialogOverlay {

	private EntryServiceAsync service = GWT.create(EntryService.class);
	private Sides2to5EntityDTO se;
	private EventBus eventBus;
	// is the key of the previous entity. necessary for ancestorpath
	private String headerTitle; 
	private ClientFactory clientFactory;
	private AddTopicSide2to5View view;
	
	public AddTopicSide2to5Widget( final EventBus evenbus, final ClientFactory clientFactory) {

		this.clientFactory = clientFactory;
		this.eventBus = evenbus;
		
		view = new AddTopicSide2to5ViewImpl();
		
		final String sessionID = Cookies.getCookie("sid");
		
		if(sessionID!=null){
			view.getSaveButton().addTapHandler(new TapHandler() {
				public void onTap(TapEvent event) {
					if(view.getTitle()!=null && !view.getTitle().equals("")){
						
						if(!titleExists(view.getTitle())){
							
							TitleContentSourcePropertyDTO tce = getTitleContentEntryObject();
							
							service.saveTitleContentObject(se, tce, new AsyncCallback<TitleContentSourcePropertyDTO>() {
								public void onSuccess(TitleContentSourcePropertyDTO result) {
									
									evenbus.fireEvent(new AddTopicSide2to5Event(result));
									
								}
								public void onFailure(Throwable caught) {}
							});
						}else{
							Dialogs.alert("The title "+view.getTitle()+" still exists", "", null);
						}
					}
				}
			});
			
			view.getCancelImageButton().addTapHandler(new TapHandler() {
				public void onTap(TapEvent event) {
					hide();
				}
			});
		}	
		add(view.asWidget());
	}
	

	public Sides2to5EntityDTO getSe() {
		return se;
	}


	public void setSe(Sides2to5EntityDTO se) {
		this.se = se;
		headerTitle = se.getTitle();
		view.setHeaderTitle(headerTitle);
	}


	private TitleContentSourcePropertyDTO getTitleContentEntryObject() {
		TitleContentSourcePropertyDTO tce = new TitleContentSourcePropertyDTO();
		tce.setTitle(view.getTitle());
		tce.setContent(view.getContent());
		tce.setQuelle(view.getSource());
		return tce;
	}
	
	private boolean titleExists(String title){
		for(int i=0; i<clientFactory.getCellContentList().size();i++){
			if(title.equalsIgnoreCase(clientFactory.getCellContentList().get(i).getTitle())){
				return true;
			}
		}
		return false;
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
