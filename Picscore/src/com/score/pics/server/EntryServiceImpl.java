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
import com.score.pics.shared.AppResources;
import com.score.pics.shared.Helper;
import com.score.pics.shared.Sides2to5Entity;
import com.score.pics.shared.TitleContentEntry;


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
		
		if(entryExists(username, eintrag,"eintrag", AppResources.startSideIdentifier())){
		
			return AppResources.eintragSchonVorhanden(eintrag);
			
		}else{
			
			Entity e = new Entity(AppResources.startSideIdentifier(), key);
			e.setProperty("eintrag", eintrag);
			
			datastore.put(e);
			
			return eintrag;
		}
	}

	/*
	 * @param Key key:
	 * 
	 * 
	 * */
	private boolean entryExists(String username, String eintrag, String property, String side) {
		
		Key key = KeyFactory.createKey("user", username);	
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
		
		Query query = new Query(AppResources.startSideIdentifier(), key);
		
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
	public List<TitleContentEntry> getTopicsFromSide2To5(Sides2to5Entity s25e) {
		
		System.out.println("getTopicsFromSide2To5()");
		System.out.println("username: "+s25e.getUsername());
		System.out.println("side    : "+s25e.getSide());
		System.out.println("eintrag : "+s25e.getEintrag());
		
		
		String username = s25e.getUsername();
		String entryBezeichner = s25e.getEintrag()+"/"+s25e.getSide();
		
		
		Key key = KeyFactory.createKey("user", username);
		
		Query users = new Query(entryBezeichner, key);
		
		List<Entity> results = datastore.prepare(users)
				.asList(FetchOptions.Builder.withDefaults());
		
		List<TitleContentEntry> list = new ArrayList<TitleContentEntry>();
		
		for(int i = 0; i < results.size(); i++){
			TitleContentEntry tce = new TitleContentEntry();
			tce.setTitle(results.get(i).getProperty("title").toString());

			if(results.get(i).getProperty("content") instanceof Text){
				tce.setContent(((Text)results.get(i).getProperty("content")).getValue());
			}else {
				tce.setContent(results.get(i).getProperty("content").toString());
			}
			tce.setQuelle(results.get(i).getProperty("source").toString());
			System.out.println("title: "+tce.getTitle());
			list.add(tce);
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
	public TitleContentEntry saveTitleContentObject(Sides2to5Entity s25e,TitleContentEntry object) {
		
		//1. Überprüfen ob der neue Eintrag schon vorhanden ist
		//   Der neue Eintrag wird über den Title definiert. Der Title ist die ID des
		//   neuen Eintrags
		
		String title = object.getTitle();
		
		String vorherigerEintrag = s25e.getEintrag();
		String side  = s25e.getSide();
		String username = s25e.getUsername();
		
		String entityBezeichner = vorherigerEintrag+"/"+side;
		
		
		datastore = DatastoreServiceFactory.getDatastoreService();
		
		Key key = KeyFactory.createKey("user", username);		
		
		if( !entryExists(username, title, "title", entityBezeichner) ){
			
			Entity e = new Entity(entityBezeichner, key);
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

}
