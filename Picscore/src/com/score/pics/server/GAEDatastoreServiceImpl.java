package com.score.pics.server;


import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.score.pics.client.GAEDatastoreService;
import com.score.pics.shared.ChangePasswordDTO;
import com.score.pics.shared.Helper;
import com.score.pics.shared.LoginUser;
import com.score.pics.shared.StringResources;
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
	public boolean registerUser(User user) {
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Key key = KeyFactory.createKey("User", user.getUsername());
		
		boolean result = false;
		
		try{
			Entity e5 = ds.get(key);
			
			result = false;
			
			
		}catch(EntityNotFoundException ex){
			
			
			if(sendEmailRegistration(user.getEmail(), user.getUsername())){
				Entity eUser = new Entity("User", user.getUsername());
				eUser.setProperty("Username", user.getUsername());
				eUser.setProperty("Email", user.getEmail());
				eUser.setProperty("Password", user.getPassword());
				
				ds.put(eUser);
				result = true;
			}
		}
		return result;
	}

	@Override
	public LoginUser login(LoginUser login) {
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
//		HttpServletRequest request = this.getThreadLocalRequest();
		
		
		String username = login.getUsername();
		String pw = login.getPassword();
		
		Key key = KeyFactory.createKey("User", username);
		
		
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
		
		LoginUser lg = new LoginUser();
		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession session = request.getSession();
		Object object = session.getAttribute("loginUser");
		
		if(object!=null && object instanceof LoginUser){

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
	
	private boolean sendEmailRegistration(String userMail, String username){
		return sendEmail(userMail, username, StringResources.userRegistrationEmailSubject(), StringResources.userRegistrationEmailText(username)); 
	}
	
	private boolean sendEmail(String userMail, String username, String subject, String text){
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		
		try {
			Message msg = new MimeMessage(session);
			try {
				msg.setFrom(new InternetAddress(StringResources.AdminMail(), StringResources.appURL()));
			} catch (UnsupportedEncodingException e) {
				return false;
			}
			try {
				msg.addRecipient(Message.RecipientType.TO,
						new InternetAddress(userMail, username));
			} catch (UnsupportedEncodingException e) {
				return false;
			}
			
			// Betreff
			msg.setSubject( subject );
			System.out.println("subject: "+subject);
			// Hauptteil
			msg.setText(text);
			System.out.println("text: "+text);
			Transport.send(msg);
			
			return true;
			
		} catch (AddressException e) {
			return false;
		} catch (MessagingException e) {
			return false;
		}
	}

	@Override
	public boolean changePassword(ChangePasswordDTO pwDTO) {
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Key key = KeyFactory.createKey("User", pwDTO.getUsername());
		
		boolean ok = false;
		
		try {
			
			Entity e5 = ds.get(key);
			
			String password = String.valueOf(e5.getProperty("Password"));
			
			if(pwDTO.getOldPassword().equals(password)){
				
				e5.setProperty("Password", pwDTO.getNewPassword());
				ds.put(e5);
				return true;
			}else{
				return false;
			}
		} catch (EntityNotFoundException e) {
			return false;
		}
	}
	
	@Override
	public boolean passwordForgotten(String email) {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Query query = new Query("User");

		PreparedQuery pq = datastore.prepare(query);
		
		for(Entity result : pq.asIterable()){
			String foundEmail = (String)result.getProperty("Email");
			
			if(foundEmail.equals(email)){
				
				String randomPw = createRandomPassword();
				
				String decryptedRandomPw = Helper.decryptPasswort(randomPw);
				
				result.setProperty("Password", decryptedRandomPw);
				
				sendEmail(email, "", "Reset password on Information Collector", "Your new password is "+randomPw+". Please log into "+StringResources.appURL()+" to change it.");
				
				datastore.put(result);
				
				return true;
			}
			
		}
		
		return false;
	}
	
	private String createRandomPassword(){
		
		String abc = StringResources.getABC123();
		
		Random random = new Random();
		
		int passwortLength = 8;
		
		String randomPw = "";
		
		for(int i = 0; i < passwortLength; i++){
			randomPw = randomPw +""+abc.charAt(random.nextInt(abc.length()));
		}		
		return randomPw;
	}

}
