package com.score.pics.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.score.pics.shared.Sides2to5Entity;
import com.score.pics.shared.TitleContentSourceProperty;

@RemoteServiceRelativePath("debateapp") 
public interface EntryService extends RemoteService {

	
	public String createEntryOnStartseite(String eintrag, String username);
	public List<String> getTopicsStartSide( String username);
	public List<TitleContentSourceProperty> getTopicsFromSide2To5( Sides2to5Entity s25e);
	public TitleContentSourceProperty saveTitleContentObject(Sides2to5Entity s25e, TitleContentSourceProperty object);
	public TitleContentSourceProperty edit(Sides2to5Entity s25e, TitleContentSourceProperty object);
	public boolean delete(Sides2to5Entity s25e, TitleContentSourceProperty object);
	public TitleContentSourceProperty editTitleProperty(Sides2to5Entity s25e, String title);

}
