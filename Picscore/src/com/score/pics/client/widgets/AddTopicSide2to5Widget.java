package com.score.pics.client.widgets;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;
import com.googlecode.mgwt.ui.client.widget.dialog.overlay.DialogOverlay;
import com.score.pics.client.ClientFactory;
import com.score.pics.client.EntryService;
import com.score.pics.client.EntryServiceAsync;
import com.score.pics.client.events.AddTopicSide2to5Event;
import com.score.pics.shared.Sides2to5Entity;
import com.score.pics.shared.TitleContentEntry;

public class AddTopicSide2to5Widget extends DialogOverlay {

	private EntryServiceAsync service = GWT.create(EntryService.class);
	private Sides2to5Entity se;
	private EventBus eventBus;
	private ClientFactory clientFactory;
	private AddTopicSide2to5View view;
	
	public AddTopicSide2to5Widget(final Sides2to5Entity se, final EventBus evenbus, final ClientFactory clientFactory) {

		this.clientFactory = clientFactory;
		this.eventBus = evenbus;
		this.se = se;
		
		view = new AddTopicSide2to5ViewImpl();
		
		
		view.setHeaderTitle(se.getEintrag());
		
		final String sessionID = Cookies.getCookie("sid");
		
		if(sessionID!=null){
			view.getSaveButton().addTapHandler(new TapHandler() {
				public void onTap(TapEvent event) {
					if(view.getTitle()!=null && !view.getTitle().equals("")){
						
						TitleContentEntry tce = getTitleContentEntryObject();
						
						service.saveTitleContentObject(se, tce, new AsyncCallback<TitleContentEntry>() {
							public void onSuccess(TitleContentEntry result) {
								
								evenbus.fireEvent(new AddTopicSide2to5Event(result));
								
							}
							public void onFailure(Throwable caught) {}
						});
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
	

	private TitleContentEntry getTitleContentEntryObject() {
		TitleContentEntry tce = new TitleContentEntry();
		tce.setTitle(view.getTitle());
		tce.setContent(view.getContent());
		tce.setQuelle(view.getSource());
		return tce;
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
