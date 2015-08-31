package com.score.pics.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.score.pics.shared.TitleContentSourcePropertyDTO;

@RemoteServiceRelativePath("entryservice") 
public interface GAEEntryService extends RemoteService {
	
	public String createStartTopic(String topic, String username);
	public String createTopicOnStart(String topic, String username);
	public List<String> getTopicsOnStartSide( String username);
	public List<TitleContentSourcePropertyDTO> getTitleContentEntries( TitleContentSourcePropertyDTO object);
	public TitleContentSourcePropertyDTO saveTitleContentObjectInDS(String id, TitleContentSourcePropertyDTO object);
	public String passwordForgotten(String email);
}
