package com.score.pics.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.score.pics.shared.TitleContentEntry;

public interface GAEEntryServiceAsync {

	void createStartTopic(String rubrik, String username, AsyncCallback<String> callback)throws IllegalArgumentException;
	
	void getTopicsOnStartSide( String username, AsyncCallback<List<String>> callback)throws IllegalArgumentException;
	
	void createTopicOnStart(String rubrik, String username, AsyncCallback<String> callback)throws IllegalArgumentException;
	void getTitleContentEntries( TitleContentEntry object, AsyncCallback<List<TitleContentEntry>> callback)throws IllegalArgumentException;
	void saveTitleContentObjectInDS(String id,  TitleContentEntry object, AsyncCallback<TitleContentEntry> callback)throws IllegalArgumentException;
}
