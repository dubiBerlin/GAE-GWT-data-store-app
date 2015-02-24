package com.score.pics.client.start;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList;
import com.googlecode.mgwt.ui.client.widget.list.celllist.HasCellSelectedHandler;

public interface StartView extends IsWidget {
	
	
	public void setHeaderText(String text);
	public HasTapHandlers getImageButton();
	public HasTapHandlers getSettingsButton();
	public HasCellSelectedHandler getCellList();
	public void rendet(List<String> list);
	public void refresh();
	public void clear();
	public CellList<String> getCellListWidget();
}
