package com.score.pics.client;

import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.button.image.NewImageButton;
import com.googlecode.mgwt.ui.client.widget.button.image.StorageImageButton;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList;
import com.googlecode.mgwt.ui.client.widget.list.celllist.HasCellSelectedHandler;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.score.pics.client.GUIHelper;
import com.score.pics.client.models.RestCell;
import com.score.pics.shared.CellContent;

public class DetailViewImpl implements DetailView {
	
	private CellList<CellContent> cellList;	// CellList ger�sst
	private ScrollPanel scrollPanel;
	private FlexPanel mainPanel;
	private Label headerLabel;
	private HeaderPanel headerpanel;
	private NewImageButton imageBttn;
	private StorageImageButton settingsBttn;
	
	public DetailViewImpl() {

		mainPanel = new FlexPanel();
		mainPanel.setSize("100%", "100%");
		
		imageBttn = new NewImageButton();
		headerLabel = new Label();
		settingsBttn = new StorageImageButton();
		
		headerpanel = GUIHelper.getHeaderPanel( headerLabel,imageBttn, settingsBttn);
		
		
		scrollPanel = new ScrollPanel();
		
		cellList = new CellList<CellContent>(new RestCell());
		scrollPanel.add(cellList);
		
		mainPanel.add(headerpanel);
		mainPanel.add(scrollPanel);
	}

	@Override
	public Widget asWidget() {
		return mainPanel;
	}
	
	@Override
	public CellList<CellContent> getCellList() {
		return cellList;
	}


	@Override
	public HasCellSelectedHandler getList() {
		return cellList;
	}

	@Override
	public void render(List<CellContent> options) {
		cellList.render(options);
	}

	@Override
	public ScrollPanel getScrollPanel() {
		return scrollPanel;
	}

	@Override
	public void setHeaderTitle(String text) {
		this.headerLabel.setText(text);
	}

	@Override
	public String getHeaderPanelCenterText() {
		return this.headerLabel.getText();
	}

	@Override
	public void refresh() {
		scrollPanel.refresh();
	}

	@Override
	public CellList<CellContent> getCellListWidget() {
		return cellList;
	}

	@Override
	public HasTapHandlers getImageButton() {
		return imageBttn;
	}

	@Override
	public HasTapHandlers getSettingsButton() {
		return settingsBttn;
	}



}
