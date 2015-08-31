package com.score.pics.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.score.pics.shared.TitleContentSourcePropertyDTO;

public interface GAEEntryServiceAsync {

	void createStartTopic(String rubrik, String username, AsyncCallback<String> callback)throws IllegalArgumentException;
	
	void getTopicsOnStartSide( String username, AsyncCallback<List<String>> callback)throws IllegalArgumentException;
	
	void createTopicOnStart(String rubrik, String username, AsyncCallback<String> callback)throws IllegalArgumentException;
	void getTitleContentEntries( TitleContentSourcePropertyDTO object, AsyncCallback<List<TitleContentSourcePropertyDTO>> callback)throws IllegalArgumentException;
	void saveTitleContentObjectInDS(String id,  TitleContentSourcePropertyDTO object, AsyncCallback<TitleContentSourcePropertyDTO> callback)throws IllegalArgumentException;
	void passwordForgotten(String email, AsyncCallback<String> callback)throws IllegalArgumentException;
}
