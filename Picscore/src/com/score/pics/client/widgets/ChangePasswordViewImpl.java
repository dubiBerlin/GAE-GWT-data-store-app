package com.score.pics.client.widgets;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.button.image.CancelImageButton;
import com.googlecode.mgwt.ui.client.widget.dialog.overlay.DialogOverlay;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.input.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Alignment;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Justification;
import com.score.pics.client.helper.GUIHelper;
import com.score.pics.client.resources.AppBundle;
import com.score.pics.shared.StringResources;

public class ChangePasswordViewImpl extends DialogOverlay implements ChangePasswordView {

	
	private FlexPanel mainPnl;
	private Label distance, resultMessage;
	private CancelImageButton closeBttn;
	private Button saveBttn;
	private MPasswordTextBox oldPassword, newPassword, newPasswordRepeat;
	private GUIHelper guiHelper = new GUIHelper();
	
	
	public ChangePasswordViewImpl() {
		
		AppBundle.INSTANCE.getCss().ensureInjected();
		
		mainPnl = new FlexPanel();
		mainPnl.getElement().getStyle().setBackgroundColor("white");
		mainPnl.setSize("100%", "100%");
		mainPnl.setAlignment(Alignment.CENTER);
		
		mainPnl.add(getHeaderPanel());
		mainPnl.add(getBody());
		
		add(mainPnl);
	}
	
	
	@Override
	public Widget asWidget() {
		
		return mainPnl;
	}
	
	
	private FlexPanel getBody(){

		FlexPanel body = new FlexPanel();
		body.setAlignment(Alignment.CENTER);
		body.setJustification(Justification.SPACE_BETWEEN);
		body.getElement().getStyle().setBackgroundColor("white");
		body.setWidth("100%");
		
		oldPassword = new MPasswordTextBox(); 
		oldPassword.setWidth("90%");
		newPassword = new MPasswordTextBox();
		newPassword.setWidth("90%");
		newPasswordRepeat = new MPasswordTextBox();
		newPasswordRepeat.setWidth("90%");
		saveBttn = new Button(StringResources.getStringSave());
		saveBttn.getElement().getStyle().setBorderColor("white");
		saveBttn.setWidth("90%");
		saveBttn.setConfirm(true);
		
		Label oldPasswordLabel = guiHelper.ueberschrift(StringResources.getStringOldPassword());
		Label newPasswordLabel = guiHelper.ueberschrift(StringResources.getStringNewPassword());
		Label newPasswordRepeatLabel = guiHelper.ueberschrift(StringResources.getStringNewPasswordRepeat());
		

		distance = guiHelper.distance10PX();
		body.add(distance);
		body.add(oldPasswordLabel);
		distance = guiHelper.distance5PX();
		body.add(distance);
		body.add(oldPassword);

		distance = guiHelper.distance10PX();
		body.add(distance);
		body.add(newPasswordLabel);
		distance = guiHelper.distance5PX();
		body.add(distance);
		body.add(newPassword);
		distance = guiHelper.distance10PX();
		body.add(distance);
		body.add(newPasswordRepeatLabel);distance = guiHelper.distance5PX();
		body.add(distance);
		body.add(newPasswordRepeat);
		distance = guiHelper.distance10PX();
		body.add(distance);
		body.add(saveBttn);
		distance = guiHelper.distance10PX();
		body.add(distance);
		resultMessage = new Label();
		resultMessage.setStyleName(AppBundle.INSTANCE.getCss().resultLabel());
		resultMessage.getElement().setId(StringResources.getIdOfResultLabel());
		body.add(resultMessage);
		
		return body;
	}


	private HeaderPanel getHeaderPanel(){
//		HeaderPanel headerPanel = new HeaderPanel();
//		headerPanel.getElement().getStyle().setBackgroundColor("#3498DB");
//		headerPanel.setWidth("100%");
//		
//		Label label = new Label("Passwort ändern");
//		label.setStyleName(AppBundle.INSTANCE.getCss().headerLabel());
//		
//		headerPanel.add(label);
//		
//		return headerPanel;
		
		closeBttn = new CancelImageButton();
		
		HeaderPanel headerPanel = guiHelper.getHeaderPanel(StringResources.getStringChangePassword(), closeBttn, false);
		return headerPanel;
	}
	
	
	
	@Override
	protected Animation getShowAnimation() {
		return Animations.SLIDE_UP;
	}

	@Override
	protected Animation getHideAnimation() {
		return Animations.SLIDE_UP_REVERSE;
	}


	@Override
	public HasTapHandlers getSaveButton() {
		return saveBttn;
	}
	
	@Override
	public void show() {
		super.show();
	}
	
	@Override
	public void hide() {
		super.hide();
	}


	@Override
	public String getOldPassword() {
		return oldPassword.getText();
	}


	@Override
	public String getNewPassword() {
		return newPassword.getText();
	}

	@Override
	public String getNewPasswordRepeat() {
		return newPasswordRepeat.getText();
	}


	@Override
	public HasTapHandlers getCloseButton() {
		return closeBttn;
	}


	@Override
	public void setResultMessageColor(String color) {
		resultMessage.getElement().getStyle().setColor(color);
	}


	@Override
	public void setResultMessage(String text) {
		resultMessage.setText(text);
	}

}
