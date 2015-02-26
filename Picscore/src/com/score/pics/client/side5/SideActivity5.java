package com.score.pics.client.side5;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.score.pics.client.ClientFactory;
import com.score.pics.client.DetailActivity;
import com.score.pics.shared.AppResources;

public class SideActivity5 extends DetailActivity {

	private SideView5 view;
	private final ClientFactory clientFactory;
	
	public SideActivity5(ClientFactory clientFactory) {
		super(clientFactory, clientFactory.getSide5ViewImpl() );
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		
		view=clientFactory.getSide5ViewImpl();
		
		
		SidePlace5 place = (SidePlace5)clientFactory.getPlaceController().getWhere();
		
		
		getStartList(place.getToken(), AppResources.side3Identifier());
		
		String sessionID = Cookies.getCookie("sid");
		
		if(sessionID!=null){
			setHandler();
		}
		
		view.setHeaderTitle(place.getToken());
		
		panel.setWidget(view);
	}
}
