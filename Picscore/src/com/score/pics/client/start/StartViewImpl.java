package com.score.pics.client.start;

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
import com.score.pics.client.helper.GUIHelper;

public class StartViewImpl implements StartView {
	
	private FlexPanel mainPnl;
	private Label headerLabel;
	private NewImageButton imageBttn;
	private StorageImageButton settingsBttn;
	private CellList<String>cellList;
	private ScrollPanel scrllPnl;
	
	public StartViewImpl() {
		
		mainPnl = new FlexPanel();
		mainPnl.setSize("100%", "100%");
		

		imageBttn = new NewImageButton();
		imageBttn.getElement().getStyle().setBackgroundColor("#3498DB");
		
		settingsBttn = new StorageImageButton();
		settingsBttn.getElement().getStyle().setBackgroundColor("#3498DB");
		
		headerLabel = new Label();
		
		HeaderPanel headerpanel = GUIHelper.getHeaderPanel(headerLabel, imageBttn, settingsBttn);
		
		
		scrllPnl = new ScrollPanel();
		cellList = new CellList<String>(new StartCell());
		scrllPnl.add(cellList);
	
		mainPnl.add(headerpanel);
		mainPnl.add(scrllPnl);
		
		
		
	}

	@Override
	public Widget asWidget() {
		return mainPnl;
	}

	@Override
	public void setHeaderText(String text) {
		this.headerLabel.setText(text);
	}

	@Override
	public HasTapHandlers getImageButton() {
		return imageBttn;
	}


	@Override
	public HasCellSelectedHandler getCellList() {
		return cellList;
	}

	@Override
	public void rendet(List<String> list) {
		cellList.render(list);
	}

	@Override
	public void refresh() {
		scrllPnl.refresh();
	}

	@Override
	public void clear() {
		scrllPnl.clear();
	}

	@Override
	public CellList<String> getCellListWidget() {
		return cellList;
	}

	@Override
	public HasTapHandlers getSettingsButton() {
		return settingsBttn;
	}

}
