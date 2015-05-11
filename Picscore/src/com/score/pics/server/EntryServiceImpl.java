package com.score.pics.server;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
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
		List<TitleContentSourceProperty> list = new ArrayList<TitleContentSourceProperty>();
		
		
		
		if(username !=null){
			Key key = getKey( side,  username, ancestorPath);
			
			Query users = new Query(side, key);
			
			List<Entity> results = datastore.prepare(users)
					.asList(FetchOptions.Builder.withDefaults   ());
			
			
			for(int i = 0; i < results.size(); i++){
				TitleContentSourceProperty tce = new TitleContentSourceProperty();
				tce.setTitle(results.get(i).getProperty("title").toString());

				if(results.get(i).getProperty("content") instanceof Text){
					tce.setContent(((Text)results.get(i).getProperty("content")).getValue());
				}else {
					tce.setContent(results.get(i).getProperty("content").toString());
				}
				tce.setQuelle(results.get(i).getProperty("source").toString());
//				System.out.println("title: "+tce.getTitle());
				
				list.add(tce);
			}	
		}
		
	
		return list;
	}
	
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
			System.out.println("Title schon vorhanden");
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
							addChild(StringResources.startSideIdentifier(), ancestorPath.get(1)).
							addChild(StringResources.side2Identifier(), ancestorPath.get(2)).
							addChild(StringResources.side3Identifier(), ancestorPath.get(3)).getKey();
				}else{
					if(side.equals(StringResources.side5Identifier())){
						key = new KeyFactory.Builder("benutzer", username).
								addChild(StringResources.startSideIdentifier(), ancestorPath.get(1)).
								addChild(StringResources.side2Identifier(), ancestorPath.get(2)).
								addChild(StringResources.side3Identifier(), ancestorPath.get(3)).
								addChild(StringResources.side4Identifier(), ancestorPath.get(4)).getKey();
					}
				}
			}
		}
//		System.out.println("\nreturned key: "+key);
		return key;
	}

	

	/* Code for UPDATING the GAE Datastore
	 * These methodes are being used to update the existing Entities and the appropriate properties.
	 * The reason why 
	 * */

	@Override
	public TitleContentSourceProperty editTitleProperty(Sides2to5Entity s25e, String title) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
//	@Override
//	public TitleContentSourceProperty delete(Sides2to5Entity s25e, TitleContentSourceProperty object) {
//		
//		datastore = DatastoreServiceFactory.getDatastoreService();
//		
//		String title = object.getTitle();
//		String side  = s25e.getSide();
//		String username = s25e.getUsername();
//		List<String>ancestorPath = s25e.getAncestorPath();	
//		
//		System.out.println("Side: "+side);
//		
//		// Hat der eintrag kinder?
//
//		// 1. Auf alle child Entries zugreifen
//		String next_side = getNextSide(side);
//		System.out.println("next side: "+next_side);
//		
//		
//		List<String>erweiternAncestorPath = ancestorPath;
//		erweiternAncestorPath.add(title);
//		
//		Key childKey = getKey(next_side, username, erweiternAncestorPath);
//		
//		Query users = new Query(next_side, childKey);
//		
//		List<Entity> results = datastore.prepare(users).asList(FetchOptions.Builder.withDefaults());
//		
//		if(!results.isEmpty()){
//			System.out.println("Delete");
//			for(int i = 0; i < results.size(); i++){
//				
//				traverse(results.get(i), ancestorPath, username);
////				datastore.delete(results.get(i).getKey());
//			}
//			
//			
//		}
//		
//		System.out.println("Der Ancestor-path am Ende:");
//		printAncestorPath(ancestorPath);
//		return null;
//	}
	
	
	@Override
	public boolean delete(Sides2to5Entity s25e, TitleContentSourceProperty tcsProperty) {
		
		datastore = DatastoreServiceFactory.getDatastoreService();
		
		String title = tcsProperty.getTitle();
		String side  = s25e.getSide();
		String username = s25e.getUsername();
		List<String>ancestorPath = s25e.getAncestorPath();	
		
		// Hat der eintrag kinder?
		
		// 1. Auf alle child Entries zugreifen
		String next_side = getNextSide(side);		
		
		List<String>erweiternAncestorPath = ancestorPath;
		erweiternAncestorPath.add(title);
		
		Key childKey = getKey(next_side, username, erweiternAncestorPath);
		
		Query users = new Query(next_side, childKey);
		
		List<Entity> results = datastore.prepare(users).asList(FetchOptions.Builder.withDefaults());
		
		if(!results.isEmpty()){
			for(int i = 0; i < results.size(); i++){
				datastore.delete(results.get(i).getKey());
				traverse(results.get(i), ancestorPath, username);				
			}
		}
		
		// 2. Die ausgesuchte Entity löschen
		deleteSingleEntity(s25e, tcsProperty);
		
		return true;
	}
	
	private void deleteSingleEntity(Sides2to5Entity s25e, TitleContentSourceProperty tcsProperty){
		
		String side  = s25e.getSide();
		String username = s25e.getUsername();
		List<String>ancestorPath = s25e.getAncestorPath();	
		
		Key key = getKey( side,  username, ancestorPath);
		Filter titleFilter =  new FilterPredicate("title",
                FilterOperator.EQUAL,
                tcsProperty.getTitle());

		Query q = new Query(side).setAncestor(key).setFilter(titleFilter);
		
		PreparedQuery pq = datastore.prepare(q);
		
		Entity e = pq.asSingleEntity();
		datastore.delete(e.getKey());
		
	}
	
	private void traverse(Entity entity, List<String> ancestorPath, String username){
		System.out.println("\nTRAVERSE");

		
		datastore = DatastoreServiceFactory.getDatastoreService();
		
		String side = entity.getKind();
		System.out.println("SIDE: "+side);
		System.out.println("TITLE: "+entity.getProperty("title"));
		
		String next_side = getNextSide(side);
		
		System.out.println("NEXT SIDE: "+next_side);
		
		String title = entity.getProperty("title").toString();
		
//		printAncestorPath(ancestorPath);
		
		List<String>erweiternAncestorPath = copyList(ancestorPath);
		erweiternAncestorPath.add(title);
		
//		printAncestorPath(erweiternAncestorPath);
		
		Key childKey = getKey(next_side, username, erweiternAncestorPath);
		
		Query users = new Query(next_side, childKey);
		
		List<Entity> results = datastore.prepare(users).asList(FetchOptions.Builder.withDefaults());
		
		if(results.size()>0){
			System.out.println("result is not empty");
			for(int i = 0; i < results.size(); i++){
				System.out.println("title: "+results.get(i).getProperty("title"));
				datastore.delete(results.get(i).getKey());
				traverse(results.get(i), erweiternAncestorPath, username);
//				System.out.println("ITS DELETED IN THE traverse() METHOD");
//				datastore.delete(results.get(i).getKey());
			}	
			return;
		}else{
			System.out.println("nix gefunden");			
			return;
		}
	}
	
	private List<String> copyList(List<String> list){
		List<String> copiedList = new ArrayList<String>();
		
		for(int i = 0; i < list.size(); i++){
			copiedList.add(list.get(i));
		}
		return copiedList;
	}
	
	private void printAncestorPath(List<String> ancestorPath){
		for(int i = 0; i < ancestorPath.size(); i++){
			System.out.print(ancestorPath.get(i)+" ; ");
		}
		System.out.println("");
	}
	

	/**/
	@Override
	public TitleContentSourceProperty edit(Sides2to5Entity s25e, TitleContentSourceProperty object) {
		
		datastore = DatastoreServiceFactory.getDatastoreService();
		
		String title = object.getTitle();
		String side  = s25e.getSide();
		String username = s25e.getUsername();
		List<String>ancestorPath = s25e.getAncestorPath();	
		
		Key key = getKey( side,  username, ancestorPath);
		
		// 1. Überprüfen ob der neue Eintrag schon vorhanden ist
		//    Der neue Eintrag wird über den Title definiert. Der Title ist die ID der children
		//    Einträge
		if(object.getNew_title()==null){
			
			Filter titleFilter =  new FilterPredicate("title",
					                      FilterOperator.EQUAL,
					                      object.getTitle());
			
			Query q = new Query(side).setAncestor(key).setFilter(titleFilter);
	
			PreparedQuery pq = datastore.prepare(q);
			
			Entity e = pq.asSingleEntity();
			
			if(e==null){
				System.out.println("No entity found");
				return null;
			}else{

				storeTitleContentsourceEntity(e, null, object.getContent(), object.getQuelle());
			}
			
		}else{
			/* 1. check if the new title already exists
			 * 2. The title of this entry was changed. The title is the id of the child entries
			 *    so all child entries have to be deleted and new child entries have to be created
			 *    with the new id=title and the contents.
			 *    
			 * */
			if(!title.equals(object.getNew_title())&& (object.getNew_title()!=null || !object.getNew_title().equals(""))){				
				/*
				 * 1. does child Entries exist? If yes, all the child entries have to be changed */ 
				if(!side.equals(StringResources.side5Identifier())){
					
					// 1. Auf alle child Entries zugreifen
					String next_side = getNextSide(side);
					
					List<String>erweiternAncestorPath = ancestorPath;
					erweiternAncestorPath.add(title);
					
					Key childKey = getKey(next_side, username, erweiternAncestorPath);
					
					Query users = new Query(next_side, childKey);
					
					List<Entity> results = datastore.prepare(users).asList(FetchOptions.Builder.withDefaults());
					
					/*
					 *  Der neue Key für die Child-Entities
					 */
					// 1. Für doe child-Entries einen neuen AncestorPath erstellen mit dem neuen Titel als ID
//					System.out.println("Der neue ancestor path:");
					List<String> newAncestorPath = ancestorPath;
					newAncestorPath.remove(newAncestorPath.size()-1);
					newAncestorPath.add(object.getNew_title());
					
					
					Key new_key = getKey(next_side, username, newAncestorPath);
					
					if(results.size()>0){
						for(int i = 0; i < results.size(); i++){
							System.out.println(results.get(i).getProperty("title"));
							
							
							String copyTitle = results.get(i).getProperty("title").toString();
							String copyContent = results.get(i).getProperty("content").toString();
							String copySource = results.get(i).getProperty("source").toString();

							Entity e = new Entity(next_side, new_key);
							storeTitleContentsourceEntity(e, copyTitle, copyContent, copySource);
							
							datastore.delete(results.get(i).getKey());
						}
						
						/* Jetzt die alten Child-entities löschen die ja die den alten, veränderten, nicht mehr vorhandenen
						 * title als ID haben. 
						 * Alle Entities mit dem Key werden nun gelöscht.*/
						
						/*
						 * Zuletzt den aktuellen veränderten Eintrag (title,content, source)  ändern und speichern
						 * */
						Filter titleFilter =  new FilterPredicate("title",
			                      FilterOperator.EQUAL,
			                      object.getTitle());
	
						Query q = new Query(side).setAncestor(key).setFilter(titleFilter);
					
						PreparedQuery pq = datastore.prepare(q);
						
						Entity e = pq.asSingleEntity();
						
						if(e==null){
							System.out.println("No entity found");
							return null;
						}else{
							System.out.println("Entsprechende entity gefunden");
							storeTitleContentsourceEntity(e, object.getNew_title(), object.getContent(), object.getQuelle());
						}
						TitleContentSourceProperty return_object = new TitleContentSourceProperty();
						return_object.setTitle(object.getNew_title());
						return_object.setContent(object.getContent());
						return_object.setQuelle(object.getQuelle());
						System.out.println("return Object ");
						return return_object;
					}
					
					/* Wenn der veränderte keine Child Einträge besitzt dann einfach die Entitiy neu speichern */
					else{
						
						Filter titleFilter =  new FilterPredicate("title",
			                      FilterOperator.EQUAL,
			                      object.getTitle());
	
						Query q = new Query(side).setAncestor(key).setFilter(titleFilter);
					
						PreparedQuery pq = datastore.prepare(q);
						
						Entity e = pq.asSingleEntity();
						
						if(e==null){
							System.out.println("No entity found");
							return null;
						}else{
							System.out.println("Entsprechende entity gefunden");
							storeTitleContentsourceEntity(e, object.getNew_title(), object.getContent(), object.getQuelle());
						}
						
					}
						
				}
				return object;
			}
		}		
		return object;
		
	}

	private void storeTitleContentsourceEntity(Entity e, String title, String content, String source){
		
		if(title!=null){
			e.setProperty("title", title);
		}
		if(content!=null){
			if(content.length() > 500){
				Text textContent = new Text(content);
				e.setProperty("content", textContent);
				
			}else{
				e.setProperty("content", content);
			}
		}
		if(source!=null){
			e.setProperty("source", source);
		}
		datastore.put(e);
	}

	/*
	 * Returns the name of the next side
	 * */
	private String getNextSide(String side) {
//		System.out.println("getNextSide(): "+side);
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
