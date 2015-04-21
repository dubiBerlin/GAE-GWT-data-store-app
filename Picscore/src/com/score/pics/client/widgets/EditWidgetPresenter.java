package com.score.pics.client.widgets;

import com.google.gwt.core.shared.GWT;
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
import com.score.pics.shared.CellContent;
import com.score.pics.shared.Sides2to5Entity;
import com.score.pics.shared.TitleContentSourceProperty;

public class EditWidgetPresenter extends DialogOverlay {

	
	// Es wird die selbe View benutzt die für das hinzufügen eines neuen Eintrags benutzt wird
	private AddTopicSide2to5View view;	
	private CellContent cellContent;
	private EntryServiceAsync service = GWT.create(EntryService.class);
	private Sides2to5Entity se;
	private ClientFactory clientFactory;
	private EventBus eventBus;
	
	public EditWidgetPresenter(final EventBus evenbus, final ClientFactory clientFactory) {

		this.clientFactory = clientFactory;
		this.eventBus = evenbus;
		
		view = new AddTopicSide2to5ViewImpl();
		
		view.setHeaderTitle("Edit");
		
		view.getCancelImageButton().addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				hide();
			}
		});
		
		view.getSaveButton().addTapHandler(new TapHandler() {
			public void onTap(TapEvent event) {
				String new_title = view.getTitle();//cellContent.getContent();
				String old_title = cellContent.getTitle();
				String new_content = view.getContent();
				String new_source = view.getSource();
				
				

				// 1. check if the title was changed. The title is part of the description of the entity on the next side.
				//    If the title was changed than all the entity description of the next side have to ba changed.
				TitleContentSourceProperty tce = new TitleContentSourceProperty();
				tce.setTitle(cellContent.getTitle());
				if(!new_title.equals(old_title)){
					tce.setNew_title(new_title);
				}
				tce.setContent(new_content);
				tce.setQuelle(new_source);
//				
//				Window.alert("Ancestor-path: "+se.getAncestorPath()+"" +
//						"\n" +
//							"old title: "+se.getTitle()+
//						"\n" +
//							"new title: "+tce.getNew_title()+
//						"\n"
//							+"side: "+se.getSide()
//							);
				
				service.edit(se, tce, new AsyncCallback<TitleContentSourceProperty>() {
					public void onSuccess(TitleContentSourceProperty result) {
						evenbus.fireEvent(new AddTopicSide2to5Event(result));
					}
					public void onFailure(Throwable caught) {
						
					}
				});
			}
		});
		
		add(view.asWidget());
	}


	public CellContent getCellContent() {
		return cellContent;
	}


	public void setCellContent(CellContent cellContent) {
		this.cellContent = cellContent;
		
		view.setTitle(cellContent.getTitle());
		view.setContent(cellContent.getContent());
		view.setSource(cellContent.getSource());
		
	}

	@Override
	protected Animation getShowAnimation() {
		return Animations.DISSOLVE;
	}

	@Override
	protected Animation getHideAnimation() {
		return Animations.DISSOLVE_REVERSE;
	}


	public Sides2to5Entity getSe() {
		return se;
	}


	public void setSe(Sides2to5Entity se) {
		this.se = se;
	}

}
