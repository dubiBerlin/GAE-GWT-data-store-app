package com.score.pics.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.score.pics.shared.Sides2to5Entity;
import com.score.pics.shared.TitleContentEntry;

@RemoteServiceRelativePath("debateapp") 
public interface EntryService extends RemoteService {

	
	public String createEntryOnStartseite(String eintrag, String username);
	public List<String> getTopicsStartSide( String username);
	public List<TitleContentEntry> getTopicsFromSide2To5( Sides2to5Entity s25e);
	public TitleContentEntry saveTitleContentObject(Sides2to5Entity s25e, TitleContentEntry object);

}
