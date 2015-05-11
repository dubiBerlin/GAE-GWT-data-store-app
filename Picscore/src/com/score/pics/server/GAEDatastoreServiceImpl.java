package com.score.pics.server;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.score.pics.client.GAEDatastoreService;
import com.score.pics.shared.LoginUser;
import com.score.pics.shared.User;



public class GAEDatastoreServiceImpl extends RemoteServiceServlet 
									implements GAEDatastoreService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String extecuteServlet(String text) {
		
		return "Hello "+text;
	}

	@Override
	public String registerUser(User user) {
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Key key = KeyFactory.createKey("User", user.getUsername());
		
		String result = "";
		
		try{
			Entity e5 = ds.get(key);
			
			result = "Username ist schon vorhanden";
			
			
		}catch(EntityNotFoundException ex){
			Entity eUser = new Entity("User", user.getUsername());
			eUser.setProperty("Firstname", user.getFirstname());
			eUser.setProperty("Lastname", user.getLastname());
			eUser.setProperty("Username", user.getUsername());
			eUser.setProperty("Email", user.getEmail());
			eUser.setProperty("Password", user.getPassword());
			
			ds.put(eUser);
			
			result = "Erfolgreich registriert";
		}
		return result;
	}

	@Override
	public LoginUser login(LoginUser login) {
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		HttpServletRequest request = this.getThreadLocalRequest();
		
		
		
		
		String username = login.getUsername();
		String pw = login.getPassword();
		
		Key key = KeyFactory.createKey("User", username);
		
		String result = "";
		
		// Erstmal schauen ob es eine User Entity mit dem Usernamen
		// schon gibt
		try{
			Entity e5 = ds.get(key);
			
			String password = String.valueOf(e5.getProperty("Password"));
			
			// Jetzt das Passwort überprüfen
			if(pw.equals(password)){
				
				LoginUser lu = getLoginUserInSession(login);
				lu.setLoggedIn(true);
				
				return lu;
			}else{
				LoginUser lu = new LoginUser();
				lu.setLoggedIn(false);
				return lu;
			}
		}catch(EntityNotFoundException ex){
			LoginUser lu = new LoginUser();
			lu.setLoggedIn(false);
			return lu;
		}
	}
	
	
	
	private LoginUser getLoginUserInSession(LoginUser user){
		LoginUser lu = new LoginUser();
		
		
		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession session = request.getSession(true);
		
		String sessionID = session.getId();
		
		lu.setSessionID(sessionID);
		lu.setUsername(user.getUsername());
		
		session.setAttribute("loginUser", lu);
		
		return lu;
	}

	@Override
	public LoginUser checkSessionID(String sessionID) {
		
		System.out.println("checkSessionID()");
		
		LoginUser lg = new LoginUser();
		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession session = request.getSession();
		Object object = session.getAttribute("loginUser");
		
		if(object!=null && object instanceof LoginUser){
			
			System.out.println("object instanceof LoginUser");

			lg = (LoginUser)object;
			lg.setLoggedIn(true);
			return lg;
			
		}
		
		lg.setLoggedIn(false);
		return lg;
	}

	@Override
	public void logout() {
		
		HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute("loginUser");
		
	}
	
	

}
