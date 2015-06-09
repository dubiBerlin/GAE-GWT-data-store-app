package com.score.pics.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.score.pics.shared.Sides2to5EntityDTO;
import com.score.pics.shared.TitleContentSourcePropertyDTO;


public interface EntryServiceAsync {

	void createEntryOnStartseite(String eintrag, String username, AsyncCallback<String> callback)throws IllegalArgumentException;
	
	void getTopicsStartSide( String username, AsyncCallback<List<TitleContentSourcePropertyDTO>> callback)throws IllegalArgumentException;
	
	void getTopicsFromSide2To5( Sides2to5EntityDTO s25e, AsyncCallback<List<TitleContentSourcePropertyDTO>> callback)throws IllegalArgumentException;
	void saveTitleContentObject(Sides2to5EntityDTO s25e,  TitleContentSourcePropertyDTO object, AsyncCallback<TitleContentSourcePropertyDTO> callback)throws IllegalArgumentException;
	void edit(Sides2to5EntityDTO s25e, TitleContentSourcePropertyDTO object, AsyncCallback<TitleContentSourcePropertyDTO> callback)throws IllegalArgumentException;
	void delete(Sides2to5EntityDTO s25e, TitleContentSourcePropertyDTO object, AsyncCallback<Boolean> callback)throws IllegalArgumentException;
	void editTitleProperty(Sides2to5EntityDTO s25e, String title, AsyncCallback<TitleContentSourcePropertyDTO> callback)throws IllegalArgumentException;



}
