package com.score.pics.server;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.score.pics.client.GAEEntryService;
import com.score.pics.shared.StringResources;
import com.score.pics.shared.TitleContentSourcePropertyDTO;
//import javax.servlet.http.Cookie; 
import com.score.pics.shared.Helper;

public class GAEEntryServiceImpl extends RemoteServiceServlet implements
		GAEEntryService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String createStartTopic(String start_topic, String username) {
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		String username_encoded = Helper.encryptPasswort(username, StringResources.getGwtDesKey());
		
		String key = username_encoded+"/"+start_topic;
		
		
		Entity topic = new Entity("rubrik", key);
		topic.setProperty("topic", start_topic);
		
		ds.put(topic);
		
		
		return start_topic;
	}

	@Override
	public String createTopicOnStart(String topic, String username) {
		
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		
		
		String entity = username+"/rubrik";
		String key = username+"/"+topic;
		
		Entity topicE = new Entity(entity, key);
		topicE.setProperty("topic", topic);
		
		ds.put(topicE);
		
		
		return topic;
	}

	@Override
	public List<String> getTopicsOnStartSide(String username) {

		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		List<String> list = new ArrayList<String>();
		
		Query query = new Query(username+"/rubrik");
		
		PreparedQuery pq = ds.prepare(query);
		
		
		for(Entity e:pq.asIterable()){
			list.add(e.getProperty("topic").toString());
			System.out.println("topic: "+e.getProperty("topic").toString());
		}
		
		return list;
	}

	@Override
	public List<TitleContentSourcePropertyDTO> getTitleContentEntries(
			TitleContentSourcePropertyDTO object) {
		
		return null;
	}

	@Override
	public TitleContentSourcePropertyDTO saveTitleContentObjectInDS(String entity, TitleContentSourcePropertyDTO object) {
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		
		
		return null;
	}

}
