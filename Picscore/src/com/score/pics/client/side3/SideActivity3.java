package com.score.pics.client.side3;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.score.pics.client.ClientFactory;
import com.score.pics.client.DetailActivity;

public class SideActivity3 extends DetailActivity {

	private SideView3 view;
	private final ClientFactory clientFactory;
	
	public SideActivity3(ClientFactory clientFactory) {
		super(clientFactory, clientFactory.getSide3ViewImpl());
		this.clientFactory = clientFactory;
	}
	

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		
		view=clientFactory.getSide3ViewImpl();
		
		
		SidePlace3 place = (SidePlace3)clientFactory.getPlaceController().getWhere();
		
		token = place.getToken();
		
		String sessionID = Cookies.getCookie("sid");
		
		if(sessionID!=null){
			setHandler();
		}
		
		view.setHeaderTitle(token);
		
		panel.setWidget(view);
	}
}
