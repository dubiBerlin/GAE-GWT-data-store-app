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
	
	/* String Resources for the SettingsWidgetPresenter */
	public String settingsDelete(){
		return "delete";
	}
	
	public String settingsEdit(){
		return "edit";
	}
	
	public String settingsShare(){
		return "share";
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
	
}
