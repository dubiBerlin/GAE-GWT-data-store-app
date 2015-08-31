package com.score.pics.client.tablet.mainwidget;

import com.google.gwt.user.client.ui.Label;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;
import com.googlecode.mgwt.ui.client.widget.dialog.overlay.DialogOverlay;
import com.googlecode.mgwt.ui.client.widget.dialog.overlay.DialogOverlayAppearance;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;

public class CellContentTabletWidget extends DialogOverlay {

	public CellContentTabletWidget() {
		
	}

	public CellContentTabletWidget(DialogOverlayAppearance appearance) {
		
		FlexPanel mnPnl = new FlexPanel();
		mnPnl.setSize("100%", "100%");
		
		FlexPanel titlePnl = new FlexPanel();
		Label title = new Label("Title:");
		titlePnl.add(title);
		
		
		FlexPanel contentPnl = new FlexPanel();
		Label content = new Label("Content:");
		contentPnl.add(content);
		
		FlexPanel sourcePnl = new FlexPanel();
		Label source = new Label("Source:");
		sourcePnl.add(source);
		
		mnPnl.add(titlePnl);
		mnPnl.add(contentPnl);
		mnPnl.add(sourcePnl);
		
		add(mnPnl);
	}

	@Override
	protected Animation getShowAnimation() {
		return Animations.SLIDE;
	}

	@Override
	protected Animation getHideAnimation() {
		return Animations.SLIDE_REVERSE;
	}

}
