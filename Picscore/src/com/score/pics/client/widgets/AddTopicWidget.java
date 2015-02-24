package com.score.pics.client.widgets;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.button.image.CancelImageButton;
import com.googlecode.mgwt.ui.client.widget.dialog.overlay.DialogOverlay;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Alignment;
import com.score.pics.client.ClientFactory;
import com.score.pics.client.EntryService;
import com.score.pics.client.EntryServiceAsync;
import com.score.pics.client.GAEEntryService;
import com.score.pics.client.GAEEntryServiceAsync;
import com.score.pics.client.GUIHelper;
import com.score.pics.client.events.StartAddTopic;
import com.score.pics.client.resources.AppBundle;
import com.google.web.bindery.event.shared.EventBus;

public class AddTopicWidget extends DialogOverlay {

	private MTextBox textBox;
	private Button saveBttn;
	private Label distance1, distance2, distance3, resultMessage;
	private EventBus eventBus;
	private CancelImageButton close;
	private ClientFactory clientFactory;
	
//	private GAEEntryServiceAsync service = GWT.create(GAEEntryService.class);
	private EntryServiceAsync entryService = GWT.create(EntryService.class);
	
	
	public AddTopicWidget(EventBus evenbus, final ClientFactory clientFactory) {
		
		AppBundle.INSTANCE.getCss().ensureInjected();
		
		this.clientFactory = clientFactory;
		this.eventBus = evenbus;
		
		FlexPanel mainPnl = new FlexPanel();
		mainPnl.getElement().getStyle().setBackgroundColor("white");
		mainPnl.setSize("100%", "100%");
		mainPnl.setAlignment(Alignment.CENTER);
		
		close = new CancelImageButton();
		close.getElement().getStyle().setBackgroundColor("#3498DB");
		HeaderPanel headerPnl = GUIHelper.getHeaderPanel("Create Topic", close, false);
		
		close.addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				hide();
			}
		});
		
		
		
		
		FlexPanel body = new FlexPanel();
		body.setAlignment(Alignment.CENTER);
		body.setWidth("100%");
		
		
		distance1 = GUIHelper.distance10PX();
		textBox = new MTextBox();
		textBox.getElement().getStyle().setBorderColor("#3498DB");
		textBox.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		textBox.getElement().getStyle().setBorderWidth(1, Unit.PX);
		textBox.setWidth("90%");
		distance2 = GUIHelper.distance10PX();
		saveBttn = new Button("Speichern");
		saveBttn.setImportant(true);
		saveBttn.setWidth("90%");
		
		final String sessionID = Cookies.getCookie("sid");
		
		if(sessionID!=null){
			saveBttn.addTapHandler(new TapHandler() {
				public void onTap(TapEvent event) {
					final String topik = textBox.getText();
					
					if(topik!=null && !topik.equals("")){
						
						entryService.createEntryOnStartseite(topik, clientFactory.getUserName(), new AsyncCallback<String>() {
							public void onSuccess(String result) {
								if(result.equals(topik)){
									eventBus.fireEvent(new StartAddTopic(result));
								}else{
									resultMessage.setText("Etwas ging schief");
								}
								
							}
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}
						});
//						service.createTopicOnStart(topik,clientFactory.getUserName() , new AsyncCallback<String>() {
//							public void onSuccess(String result) {
//								if(result.equals(topik)){
//									eventBus.fireEvent(new StartAddTopic(result));
//								}else{
//									resultMessage.setText("Etwas ging schief");
//								}
//								
//							}
//							public void onFailure(Throwable caught) {}
//						});
						
						
					}
					else{
						resultMessage.setText("Eingabe fehlt");
					}
				}
			});
		}
		
		
		
		saveBttn.getElement().getStyle().setBorderColor("white");
		distance3 = GUIHelper.distance10PX();
		
		resultMessage = new Label();
		resultMessage.setStyleName(AppBundle.INSTANCE.getCss().resultLabel());
		
		body.add(distance1);
		body.add(textBox);
		body.add(distance2);
		body.add(saveBttn);
		body.add(distance3);
		body.add(resultMessage);
		
		mainPnl.add(headerPnl);
		mainPnl.add(body);
		
		add(mainPnl);
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
