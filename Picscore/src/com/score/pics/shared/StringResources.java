package com.score.pics.shared;

public class StringResources {

	
	private final static byte[] GWT_DES_KEY = new byte[] { -110, 121, -65, 22, -60, 61, -22, 
		-60, 21, -122, 41, -89, -89, -68, -8, 41, -119, -51, -12, -36, 19, -8, -17, 47 };

	public static byte[] getGwtDesKey() {
		return GWT_DES_KEY;
	}
	
	
	public static String eintragSchonVorhanden(String eintrag){
		return eintrag+" ist in der Liste schon vorhanden";
	}
	
	public static String startSideIdentifier(){
		return "startseite";
	}
	
	public static String side2Identifier(){
		return "seite2";
	}
	
	public static String side3Identifier(){
		return "seite3";
	}
	
	public static String side4Identifier(){
		return "seite4";
	}
	
	public static String side5Identifier(){
		return "seite5";
	}
	
	
	/* String Resources for the UI */
	public  static String getStringDelete(){
		return "delete";
	}
	
	public static  String getStringEdit(){
		return "edit";
	}
	
	public static  String getStringShare(){
		return "share";
	}
	
	public static  String getStringSave(){
		return "Save";
	}
	
	public  static String getStringUsername(){
		return "Username";
	}
	
	public static  String getStringEmail(){
		return "Email";
	}
	
	public  static String getStringPassword(){
		return "Password";
	}
	
	public  static String getStringPasswordRepeat(){
		return "Password repeat";
	}

	public  static String getStringOldPassword(){
		return "Old password";
	}
	
	public  static String getStringNewPassword(){
		return "New password";
	}
	
	public  static String getStringNewPasswordRepeat(){
		return "New password repeat";
	}
	
	public  static String getStringLogin(){
		return "Login";
	}
	
	public  static String getStringRegister(){
		return "Register";
	}
	
	public  static String getStringSend(){
		return "Send";
	}
	
	public  static String getStringChangePassword(){
		return "Change Password";
	}
	
	public  static String getStringPasswordForgottenHeaderLabel(){
		return "Password forgotten";
	}
	
	public  static String getStringResetPassword(){
		return "Reset Password";
	}
	
	public  static String getStringSendPasswordToEmailLabel(){
		return "Send me a new password";
	}
	
	public  static String getStringCompleteTheInput(){
		return "Complete the input";
	}
	
	public  static String getStringPasswordSendToEmail(String emailAdress){
		return "Password was send to "+emailAdress;
	}
	
	
	public  static String getStringNotValidEmailAdress(){
		return "This is not a valid Email adress";
	}
	
	
	public  static String getStringEmailNotFound(){
		return "Email not found";
	}
	
	public static String getABC123(){
		return "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	}
	
	// MELDUNGEN, FEHLER UND SUCCESS
	
	/*
	 * The css for the class=error_message is located in war/picscore.css
	 * */
	public static String getErrorMessage(String errorMessage, String id){
		return "<div id='"+id+"' >"+errorMessage+"</div>";
	}//class='error_message'
	
	// Success-Meldung
	public static String getSuccesMessage(String successMessage, String id){
		return "<div id='"+id+"' >"+successMessage+"</div>";
	}//class='success_message'
	
	
	public static String serverConnectionFailed(){
		return "Verbindungsproblem";
	}
	
	public static String getPasswordChangedSuccessfully(){
		return "Password was changed successfully";
	}
	
	public static String getCheckInput(){
		return "Please check the input, something is wrong";
	}
	
	
	/* GAE Java mail stuff */
	public static String userRegistrationEmailSubject(){
		return "Your gwtrecipes.appspot.com account has been activated";
	}
	
	public static String userRegistrationEmailText(String username){
		return "Hello "+username+ ", welcome on Information Collector!";
	}
	
	public static String appURL(){
		return "gwtrecipes.appspot.com";
	}
	
	public static String AdminMail(){
		return "gwtrecipes@appspot.gserviceaccount.com";
	}
	
	/* UI IDs */
	/* This is the same ID like the id in the CSS File for resultLabel */
	public static String getIdOfResultLabel(){
		return "resultmessageId";
	}
	
	
	/* CSS stuff */
	public static String getErrorColor(){
		return "#F64747";
	}

	public static String getSuccessColor(){
		return " #3498DB";
	}
}
