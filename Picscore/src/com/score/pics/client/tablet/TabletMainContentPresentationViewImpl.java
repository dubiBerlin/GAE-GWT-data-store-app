package com.score.pics.client.tablet;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPropertyHelper.Alignment;
import com.score.pics.client.resources.AppBundle;

public class TabletMainContentPresentationViewImpl implements TabletMainContentPresentationView {
	
	
	private FlexPanel mnPnl, hrdPnl;
	private Label title, content,source;
	
	public TabletMainContentPresentationViewImpl() {
		mnPnl = new FlexPanel();
		mnPnl.getElement().getStyle().setBackgroundColor("white");
		mnPnl.setSize("100%", "100%");
		mnPnl.setAlignment(Alignment.CENTER);
	
		
		hrdPnl = new FlexPanel();
		hrdPnl.getElement().getStyle().setBackgroundColor("#3498DB");
		hrdPnl.setWidth("100%");
		
		Label label = new Label("Information collector");
		label.setStyleName(AppBundle.INSTANCE.getCss().headerLabel());
		
		hrdPnl.add(label);
		
		mnPnl.add(hrdPnl);
		mnPnl.add(getBody());
		
	}
	
	private FlexPanel getBody(){
		FlexPanel body = new FlexPanel();
		
		title = new Label("Title");
		content = new Label("Content");
		source = new Label("Source");
		
		body.add(title);
		body.add(content);
		body.add(source);
		
		return body;
	}

	@Override
	public Widget asWidget() {
		return mnPnl;
	}

	@Override
	public void setTitle(String text) {
		this.title.setText(text);
	}

	@Override
	public void setContent(String text) {
		this.content.setText(text);
	}

	@Override
	public void setSource(String text) {
		this.source.setText(text);
	}


}
