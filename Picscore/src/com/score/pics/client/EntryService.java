package com.score.pics.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.score.pics.shared.Sides2to5EntityDTO;
import com.score.pics.shared.TitleContentSourcePropertyDTO;

@RemoteServiceRelativePath("debateapp") 
public interface EntryService extends RemoteService {

	
	public String createEntryOnStartseite(String eintrag, String username);
	public List<TitleContentSourcePropertyDTO> getTopicsStartSide( String username);
	public List<TitleContentSourcePropertyDTO> getTopicsFromSide2To5( Sides2to5EntityDTO s25e);
	public TitleContentSourcePropertyDTO saveTitleContentObject(Sides2to5EntityDTO s25e, TitleContentSourcePropertyDTO object);
	public TitleContentSourcePropertyDTO edit(Sides2to5EntityDTO s25e, TitleContentSourcePropertyDTO object);
	public boolean delete(Sides2to5EntityDTO s25e, TitleContentSourcePropertyDTO object);
	public TitleContentSourcePropertyDTO editTitleProperty(Sides2to5EntityDTO s25e, String title);

}
