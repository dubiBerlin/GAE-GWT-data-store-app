package com.score.pics.client.widgets;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.button.image.CancelImageButton;
import com.googlecode.mgwt.ui.client.widget.dialog.overlay.DialogOverlay;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.input.MTextArea;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Alignment;
import com.score.pics.client.ClientFactory;
import com.score.pics.client.EntryService;
import com.score.pics.client.EntryServiceAsync;
import com.score.pics.client.GUIHelper;
import com.score.pics.client.events.AddTopicSide2to5Event;
import com.score.pics.client.resources.AppBundle;
import com.score.pics.shared.Sides2to5Entity;
import com.score.pics.shared.TitleContentEntry;

public class AddTopicWithTitle extends DialogOverlay {

	private MTextBox  titleTxtBox, sourceBox;
	private MTextArea textBox;
	private Button saveBttn;
	private Label distance1, distance2, distance3, distance4,distance5, resultMessage;
	private Label titleHeader, contentHeader, sourceHeader;
	private EventBus eventBus;
	private CancelImageButton close;
	private ClientFactory clientFactory;
	
//	private GAEEntryServiceAsync service = GWT.create(GAEEntryService.class);
	private EntryServiceAsync service = GWT.create(EntryService.class);
	private Sides2to5Entity se;
	
	
	public AddTopicWithTitle(final Sides2to5Entity se, final EventBus evenbus, final ClientFactory clientFactory) {
		
		AppBundle.INSTANCE.getCss().ensureInjected();
		
		this.clientFactory = clientFactory;
		this.eventBus = evenbus;
		this.se = se;
		
		FlexPanel mainPnl = new FlexPanel();
		mainPnl.getElement().getStyle().setBackgroundColor("white");
		mainPnl.setSize("100%", "100%");
		mainPnl.setAlignment(Alignment.CENTER);
		
		
		close = new CancelImageButton();
		close.getElement().getStyle().setBackgroundColor("#3498DB");
		HeaderPanel headerPnl = GUIHelper.getHeaderPanel("new entry for "+se.getEintrag(), close, false);
		
		close.addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				hide();
			}
		});
		
		FlexPanel body = new FlexPanel();
		body.setAlignment(Alignment.CENTER);
		body.setWidth("100%");
		
		
		distance1 = GUIHelper.distance10PX();
		titleHeader = GUIHelper.ueberschrift("Title");
		titleTxtBox = new MTextBox();
		titleTxtBox.getElement().getStyle().setBorderColor("#3498DB");
		titleTxtBox.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		titleTxtBox.getElement().getStyle().setBorderWidth(1, Unit.PX);
		titleTxtBox.setWidth("90%");
		
		distance2 = GUIHelper.distance10PX();
		contentHeader = GUIHelper.ueberschrift("Content");
		textBox = new MTextArea();
		textBox.getElement().getStyle().setBorderColor("#3498DB");
		textBox.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		textBox.getElement().getStyle().setBorderWidth(1, Unit.PX);
		textBox.setWidth("90%");
		
		distance5 = GUIHelper.distance10PX();
		sourceHeader = GUIHelper.ueberschrift("Source");
		sourceBox = new MTextBox();
		sourceBox.getElement().getStyle().setBorderColor("#3498DB");
		sourceBox.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		sourceBox.getElement().getStyle().setBorderWidth(1, Unit.PX);
		sourceBox.setWidth("90%");
		
		distance2 = GUIHelper.distance10PX();
		saveBttn = new Button("Speichern");
		saveBttn.setImportant(true);
		saveBttn.setWidth("90%");
		
		final String sessionID = Cookies.getCookie("sid");
		
		if(sessionID!=null){
			saveBttn.addTapHandler(new TapHandler() {
				public void onTap(TapEvent event) {
					if(titleTxtBox.getText()!=null && !titleTxtBox.getText().equals("")){
						
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
		}
		
		
		distance3 = GUIHelper.distance10PX();
		saveBttn.getElement().getStyle().setBorderColor("white");
		
		distance4 = GUIHelper.distance10PX();
		
		resultMessage = new Label();
		resultMessage.setStyleName(AppBundle.INSTANCE.getCss().resultLabel());
		
		body.add(distance1);
		body.add(titleHeader);
		body.add(titleTxtBox);
		body.add(distance2);
		body.add(contentHeader);
		body.add(textBox);
		body.add(distance5);
		body.add(sourceHeader);
		body.add(sourceBox);
		body.add(distance3);
		body.add(saveBttn);
		body.add(distance4);
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
	

	private TitleContentEntry getTitleContentEntryObject() {
		TitleContentEntry tce = new TitleContentEntry();
		tce.setTitle(titleTxtBox.getText());
		tce.setContent(textBox.getText());
		tce.setQuelle(sourceBox.getText());
		return tce;
	}

}
