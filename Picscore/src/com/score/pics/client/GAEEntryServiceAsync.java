package com.score.pics.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.score.pics.shared.TitleContentSourceProperty;

public interface GAEEntryServiceAsync {

	void createStartTopic(String rubrik, String username, AsyncCallback<String> callback)throws IllegalArgumentException;
	
	void getTopicsOnStartSide( String username, AsyncCallback<List<String>> callback)throws IllegalArgumentException;
	
	void createTopicOnStart(String rubrik, String username, AsyncCallback<String> callback)throws IllegalArgumentException;
	void getTitleContentEntries( TitleContentSourceProperty object, AsyncCallback<List<TitleContentSourceProperty>> callback)throws IllegalArgumentException;
	void saveTitleContentObjectInDS(String id,  TitleContentSourceProperty object, AsyncCallback<TitleContentSourceProperty> callback)throws IllegalArgumentException;
}
