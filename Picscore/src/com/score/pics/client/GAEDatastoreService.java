package com.score.pics.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.score.pics.shared.LoginUser;
import com.score.pics.shared.User;

@RemoteServiceRelativePath("gaeDatastore") 
public interface GAEDatastoreService extends RemoteService{

	public String extecuteServlet(String text);
	
	public boolean registerUser(User user);

	public LoginUser login(LoginUser login);
	
	public LoginUser checkSessionID(String sessionID);
	
	public void logout();
	
	
}
