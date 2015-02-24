package com.score.pics.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.score.pics.shared.TitleContentEntry;

@RemoteServiceRelativePath("entryservice") 
public interface GAEEntryService extends RemoteService {
	
	public String createStartTopic(String topic, String username);
	public String createTopicOnStart(String topic, String username);
	public List<String> getTopicsOnStartSide( String username);
	public List<TitleContentEntry> getTitleContentEntries( TitleContentEntry object);
	public TitleContentEntry saveTitleContentObjectInDS(String id, TitleContentEntry object);

}
