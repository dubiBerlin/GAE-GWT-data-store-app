package com.score.pics.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.score.pics.shared.TitleContentSourceProperty;

@RemoteServiceRelativePath("entryservice") 
public interface GAEEntryService extends RemoteService {
	
	public String createStartTopic(String topic, String username);
	public String createTopicOnStart(String topic, String username);
	public List<String> getTopicsOnStartSide( String username);
	public List<TitleContentSourceProperty> getTitleContentEntries( TitleContentSourceProperty object);
	public TitleContentSourceProperty saveTitleContentObjectInDS(String id, TitleContentSourceProperty object);

}
