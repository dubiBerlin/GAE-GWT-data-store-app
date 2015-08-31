package com.score.pics.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.score.pics.shared.ChangePasswordDTO;
import com.score.pics.shared.LoginUser;
import com.score.pics.shared.User;

public interface GAEDatastoreServiceAsync {

	void extecuteServlet(String text, AsyncCallback<String> callback)throws IllegalArgumentException;
	void registerUser(User user, AsyncCallback<Boolean> callback)throws IllegalArgumentException;
	void login(LoginUser login, AsyncCallback<LoginUser> callback)throws IllegalArgumentException;
	void checkSessionID(String sessionID, AsyncCallback<LoginUser> callback)throws IllegalArgumentException;
	void logout(AsyncCallback<Void> callback)throws  IllegalArgumentException;
	void changePassword(ChangePasswordDTO pwDTO, AsyncCallback<Boolean> callback)throws IllegalArgumentException;
	void passwordForgotten(String email, AsyncCallback<Boolean> callback)throws IllegalArgumentException;
}
