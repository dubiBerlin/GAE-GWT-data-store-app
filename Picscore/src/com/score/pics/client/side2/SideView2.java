package com.score.pics.client.side2;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList;
import com.googlecode.mgwt.ui.client.widget.list.celllist.HasCellSelectedHandler;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.score.pics.shared.CellContent;


public interface SideView2 extends IsWidget{


	public HasCellSelectedHandler getList();
	public CellList<CellContent> getCellList();
	public void render(List<CellContent> options);
	public ScrollPanel getScrollPanel();
	public void setHeaderTitle(String text);
	public void refresh();
	public String getHeaderPanelCenterText();
	public CellList<CellContent> getCellListWidget();
	public HasTapHandlers getImageButton();
	public HasTapHandlers getSettingsButton();
}
