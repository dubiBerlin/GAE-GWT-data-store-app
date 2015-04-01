package com.score.pics.server;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Text;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.score.pics.client.EntryService;
import com.score.pics.shared.StringResources;
import com.score.pics.shared.Sides2to5Entity;
import com.score.pics.shared.TitleContentSourceProperty;


/*
 *  
"kann man das, was dort steht, nicht sehr vielseitig interpretieren?﻿"

es gibt sowas nicht wie "man kann den koran so oder so interpretieren"
das ist schwachsinn und eine lüge der medien und der moslems um die menschen im westen zu beruhigen und damit sie denken "ok die moslems hier interpretieren den koran friedlich und die isis und andere jihadisten interpretieren den koran gewalttätig"

wie man dann koran zu interpetrieren und zu verstehen hat, dass
 hat mohammad vorgemacht. 
 er hat gezeigt wie man den koran zu interpretieren und zu verstehen hat.
 


*/
public class EntryServiceImpl extends RemoteServiceServlet implements
		EntryService {

	private DatastoreService datastore;
	

	/*
	 * Erstellt einen Eintrag NUR auf der Startseite
	 * 
	 * @param String Eintrag: 1. Ist der Eintrag den der User eingegeben hat.
	 * 						  2. Wird in der CellList dargestellt
	 * 						  3. Wird im property "eintrag" der Entity gespeichert
	 * 
	 * @param String username: Username des users wird als id bzw Key für die Entity 
	 * 						   benutzt	
	 * */
	public String createEntryOnStartseite(String eintrag, String username) {
		datastore = DatastoreServiceFactory.getDatastoreService();
		
		Key key = KeyFactory.createKey("user", username);	
		
		if(entryExists(key, StringResources.startSideIdentifier(),"eintrag", eintrag)){
		
			return StringResources.eintragSchonVorhanden(eintrag);
			
		}else{
			
			Entity e = new Entity(StringResources.startSideIdentifier(), key);
			e.setProperty("eintrag", eintrag);
			
			datastore.put(e);
			
			return eintrag;
		}
	}
	
	private boolean entryExists(Key user_key, String entity ,String property, String propertyContent){
		
		// Hole alles von Entity Startseite die zum user_key gehört von user
		Query photoQuery = new Query(entity).setAncestor(user_key);
		
		
		List<Entity> results = datastore.prepare(photoQuery)
				.asList(FetchOptions.Builder.withDefaults());
		
		
		for(int i = 0; i < results.size(); i++){
			String e = (String) results.get(i).getProperty(property);
			
			if(e.equals(propertyContent)){
				return true;
			}
		}
		
		return false;
	}

//	/*
//	 * @param Key key:
//	 * 
//	 * 
//	 * */
//	private boolean entryExists(String username, String eintrag, String property, String side) {
//		
//		Key key = KeyFactory.createKey("user", username);	
//		Query query = new Query(side).setAncestor(key);
//		
//		List<Entity> results = datastore.prepare(query)
//				.asList(FetchOptions.Builder.withDefaults());
//		
//		
//		for(int i = 0; i < results.size(); i++){
//			String e = (String) results.get(i).getProperty(property);
//			
//			if(e.equals(eintrag)){
//				return true;
//			}
//		}
//		
//		return false;
//		
//	}
	
	/*
	 * @param Key key:
	 * 
	 * 
	 * */
	private boolean entryExistsPart2(Key key, String eintrag, String property, String side) {
		
		Query query = new Query(side).setAncestor(key);
		
		List<Entity> results = datastore.prepare(query)
				.asList(FetchOptions.Builder.withDefaults());
		
		
		for(int i = 0; i < results.size(); i++){
			String e = (String) results.get(i).getProperty(property);
			
			if(e.equals(eintrag)){
				return true;
			}
		}
		
		return false;
		
	}

	@Override
	public List<String> getTopicsStartSide(String username) {
		
		datastore = DatastoreServiceFactory.getDatastoreService();

		Key key = KeyFactory.createKey("user", username);
		
		
		List<String> list = new ArrayList<String>();
		
		Query query = new Query(StringResources.startSideIdentifier(), key);
		
		PreparedQuery pq = datastore.prepare(query);
		
		
		
		for(Entity e:pq.asIterable()){
			list.add(e.getProperty("eintrag").toString());
		}
		
		return list;
	}

	/*
	 * Die Einträge von Seite2,3,4,5
	 * 
	 * */
	@Override
	public List<TitleContentSourceProperty> getTopicsFromSide2To5(Sides2to5Entity s25e) {
		
//		String entityKind = s25e.getSide();
//		String key_username = s25e.getUsername();
//		
//		
//		Key key = KeyFactory.createKey(entityKind, key_username);
		
		String side  = s25e.getSide();
		String username = s25e.getUsername();
		List<String>ancestorPath = s25e.getAncestorPath();	
		
		datastore = DatastoreServiceFactory.getDatastoreService();
		
		Key key = getKey( side,  username, ancestorPath);
		
		
//		System.out.println("Kompletter Key: "+key);
		
		
		Query users = new Query(side, key);
		
		List<Entity> results = datastore.prepare(users)
				.asList(FetchOptions.Builder.withDefaults   ());
		
		List<TitleContentSourceProperty> list = new ArrayList<TitleContentSourceProperty>();
		
		for(int i = 0; i < results.size(); i++){
			TitleContentSourceProperty tce = new TitleContentSourceProperty();
			tce.setTitle(results.get(i).getProperty("title").toString());

			if(results.get(i).getProperty("content") instanceof Text){
				tce.setContent(((Text)results.get(i).getProperty("content")).getValue());
			}else {
				tce.setContent(results.get(i).getProperty("content").toString());
			}
			tce.setQuelle(results.get(i).getProperty("source").toString());
//			System.out.println("title: "+tce.getTitle());
			
			list.add(tce);
		}		
		return list;
	}
	
//	/*
//	 * Die Einträge von Seite2,3,4,5
//	 * 
//	 * */
//	@Override
//	public List<TitleContentSourceProperty> getTopicsFromSide2To5(Sides2to5Entity s25e) {
//		
//		String username = s25e.getUsername();
//		String entryBezeichner = s25e.getTitle()+"/"+s25e.getSide();
//		
//		
//		Key key = KeyFactory.createKey("user", username);
//		
//		Query users = new Query(entryBezeichner, key);
//		
//		List<Entity> results = datastore.prepare(users)
//				.asList(FetchOptions.Builder.withDefaults());
//		
//		List<TitleContentSourceProperty> list = new ArrayList<TitleContentSourceProperty>();
//		
//		for(int i = 0; i < results.size(); i++){
//			TitleContentSourceProperty tce = new TitleContentSourceProperty();
//			tce.setTitle(results.get(i).getProperty("title").toString());
//
//			if(results.get(i).getProperty("content") instanceof Text){
//				tce.setContent(((Text)results.get(i).getProperty("content")).getValue());
//			}else {
//				tce.setContent(results.get(i).getProperty("content").toString());
//			}
//			tce.setQuelle(results.get(i).getProperty("source").toString());
//			System.out.println("title: "+tce.getTitle());
//			list.add(tce);
//		}
//		
//		
//		return list;
//	}
//	
//	/*
//	 * Die Einträge von Seite2,3,4,5
//	 * 
//	 * */
//	@Override
//	public List<TitleContentSourceProperty> getTopicsFromSide2To5(Sides2to5Entity s25e) {
//		
//		String username = s25e.getUsername();
//		String entryBezeichner = s25e.getTitle()+"/"+s25e.getSide();
//		
//		
//		Key key = KeyFactory.createKey("user", username);
//		
//		Query users = new Query(entryBezeichner, key);
//		
//		List<Entity> results = datastore.prepare(users)
//				.asList(FetchOptions.Builder.withDefaults());
//		
//		List<TitleContentSourceProperty> list = new ArrayList<TitleContentSourceProperty>();
//		
//		for(int i = 0; i < results.size(); i++){
//			TitleContentSourceProperty tce = new TitleContentSourceProperty();
//			tce.setTitle(results.get(i).getProperty("title").toString());
//			
//			if(results.get(i).getProperty("content") instanceof Text){
//				tce.setContent(((Text)results.get(i).getProperty("content")).getValue());
//			}else {
//				tce.setContent(results.get(i).getProperty("content").toString());
//			}
//			tce.setQuelle(results.get(i).getProperty("source").toString());
//			System.out.println("title: "+tce.getTitle());
//			list.add(tce);
//		}
//		
//		
//		return list;
//	}
	
	/*
	 * @param TitleContentEntry:    Titel ist die ID eines jeden Eintrags und darf pro Seite 
	 * 							    nur einmnal vorkommen. Muss daher überprüft werden ob schon enthalten.
	 * @param Sides2to5Entity s25e: Enthält alle Inhalte um die Entity zu erzeugen. Die Entity Bezeichnung 
	 * 								und den Key.
	 * 
	 * */
	public TitleContentSourceProperty saveTitleContentObject(Sides2to5Entity s25e,TitleContentSourceProperty object) {
		
		//1. Überprüfen ob der neue Eintrag schon vorhanden ist
		//   Der neue Eintrag wird über den Title definiert. Der Title ist die ID des
		//   neuen Eintrags
		
		String title = object.getTitle();
		String side  = s25e.getSide();
		String username = s25e.getUsername();
		List<String>ancestorPath = s25e.getAncestorPath();	
		
		datastore = DatastoreServiceFactory.getDatastoreService();
		
		Key key = getKey( side,  username, ancestorPath);
		
		
		
		if( !entryExistsPart2(key, title, "title", side) ){
			
			Entity e = new Entity(side,key);
			e.setProperty("title", title);
			
			if(object.getContent().length()>500){
				Text textContent = new Text(object.getContent());
				e.setProperty("content", textContent);
				
			}else{
				String content = object.getContent();
				e.setProperty("content", content);
			}
			
			
			String source = object.getQuelle();
			e.setProperty("source", source);
			
			datastore.put(e);
			
			return object;
			
		}else{
			return null;
		}
	}
	
	private Key getKey(String side, String username, List<String> ancestorPath){
		Key key = null;
		/* Build the Key*/
		if(side.equals(StringResources.side2Identifier())){
			key = new KeyFactory.Builder("benutzer", username).addChild("startside", ancestorPath.get(1)).getKey();
		}else{
			if(side.equals(StringResources.side3Identifier())){
				key = new KeyFactory.Builder("benutzer", username).
						addChild(StringResources.startSideIdentifier(), ancestorPath.get(1)).
						addChild(StringResources.side2Identifier(), ancestorPath.get(2)).getKey();
			}else{
				if(side.equals(StringResources.side4Identifier())){
					key = new KeyFactory.Builder("benutzer", username).
							addChild(StringResources.side2Identifier(), ancestorPath.get(1)).
							addChild(StringResources.side2Identifier(), ancestorPath.get(2)).
							addChild(StringResources.side3Identifier(), ancestorPath.get(3)).getKey();
				}else{
					if(side.equals(StringResources.side5Identifier())){
						key = new KeyFactory.Builder("benutzer", username).
								addChild(StringResources.side2Identifier(), ancestorPath.get(1)).
								addChild(StringResources.side2Identifier(), ancestorPath.get(2)).
								addChild(StringResources.side3Identifier(), ancestorPath.get(3)).
								addChild(StringResources.side4Identifier(), ancestorPath.get(4)).getKey();
					}
				}
			}
		}
		return key;
	}

	
//	/*
//	 * @param TitleContentEntry:    Titel ist die ID eines jeden Eintrags und darf pro Seite 
//	 * 							    nur einmnal vorkommen. Muss daher überprüft werden ob schon enthalten.
//	 * @param Sides2to5Entity s25e: Enthält alle Inhalte um die Entity zu erzeugen. Die Entity Bezeichnung 
//	 * 								und den Key.
//	 * 
//	 * */
//	public TitleContentSourceProperty saveTitleContentObject(Sides2to5Entity s25e,TitleContentSourceProperty object) {
//		
//		//1. Überprüfen ob der neue Eintrag schon vorhanden ist
//		//   Der neue Eintrag wird über den Title definiert. Der Title ist die ID des
//		//   neuen Eintrags
//		
//		String title = object.getTitle();
//		
//		String vorherigerEintrag = s25e.getTitle();
//		String side  = s25e.getSide();
//		String username = s25e.getUsername();
//		
//		String entityBezeichner = vorherigerEintrag+"/"+side;
//		
//		
//		datastore = DatastoreServiceFactory.getDatastoreService();
//		
//		Key key = KeyFactory.createKey("user", username);		
//		
//		if( !entryExists(username, title, "title", entityBezeichner) ){
//			
//			Entity e = new Entity(entityBezeichner, key);
//			e.setProperty("title", title);
//			
//			if(object.getContent().length()>500){
//				Text textContent = new Text(object.getContent());
//				e.setProperty("content", textContent);
//				
//			}else{
//				String content = object.getContent();
//				e.setProperty("content", content);
//			}
//			
//			
//			String source = object.getQuelle();
//			e.setProperty("source", source);
//			
//			datastore.put(e);
//			
//			return object;
//			
//		}else{
//			return null;
//		}
//	}

	/* Code for UPDATING the GAE Datastore
	 * These methodes are being used to update the existing Entities and the appropriate properties.
	 * The reason why 
	 * */

	@Override
	public TitleContentSourceProperty editTitleProperty(Sides2to5Entity s25e, String title) {
		// TODO Auto-generated method stub
		return null;
	}

	/**/
	@Override
	public TitleContentSourceProperty edit(Sides2to5Entity s25e, TitleContentSourceProperty object) {
		
		datastore = DatastoreServiceFactory.getDatastoreService();
		
		// 1. Überprüfen ob der neue Eintrag schon vorhanden ist
		//    Der neue Eintrag wird über den Title definiert. Der Title ist die ID des
		//    neuen Eintrags
		String title = object.getTitle();
		
		String vorherigerEintrag = s25e.getTitle();
		String side  = s25e.getSide();
		String entityBezeichner = vorherigerEintrag+"/"+side;	
		
		String username = s25e.getUsername();
		
		Key key = KeyFactory.createKey("user", username);		
		
			
		Entity e = new Entity(entityBezeichner, key);
			
		/* If the title was changed  */
		if(object.getNew_title()!=null || !object.getNew_title().equals("")){
				
			// 1. set the new title
			e.setProperty("title", object.getNew_title());
				
			// 2. check if there are any Entities(of next sides of course) which identifiers contains 
			//    the old title because they have to be changed too.
			String nextSide = getNextSide(side);
			
			// create the key
			Key key2 = KeyFactory.createKey("user", username);
			// create descrption
			String entityDescription = title+"/"+nextSide;
			
			Query users = new Query(entityDescription, key);
			
			List<Entity> results = datastore.prepare(users)
					.asList(FetchOptions.Builder.withDefaults());
		}			
			
		/* If String has more than 500 characters it has to be saved as a Text-object into GAE Datastore  */
		if(object.getContent().length()>500){
			Text textContent = new Text(object.getContent());
			e.setProperty("content", textContent);
			
		}else{
			String content = object.getContent();
			e.setProperty("content", content);
		}
		
		
		String source = object.getQuelle();
		e.setProperty("source", source);
		
		datastore.put(e);
		
		return object;
		
	}

	/*
	 * Returns the name of the next side
	 * */
	private String getNextSide(String side) {
		
		if(side.equals(StringResources.startSideIdentifier())){
			return StringResources.side2Identifier();
		}
		if(side.equals(StringResources.side2Identifier())){
			return StringResources.side3Identifier();
		}
		if(side.equals(StringResources.side3Identifier())){
			return StringResources.side4Identifier();
		}
		if(side.equals(StringResources.side4Identifier())){
			return StringResources.side5Identifier();
		}
		return "";

	}
	
	/*
	 * Returns the name of the next side
	 * */
	private String getPreviousSide(String side) {
		
		if(side.equals(StringResources.side2Identifier())){
			return StringResources.startSideIdentifier();
		}
		if(side.equals(StringResources.side3Identifier())){
			return StringResources.side2Identifier();
		}
		if(side.equals(StringResources.side4Identifier())){
			return StringResources.side3Identifier();
		}
		if(side.equals(StringResources.side5Identifier())){
			return StringResources.side4Identifier();
		}
		return "";
		
	}

}
