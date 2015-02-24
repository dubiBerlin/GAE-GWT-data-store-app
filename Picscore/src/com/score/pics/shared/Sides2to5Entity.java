package com.score.pics.shared;

import java.io.Serializable;

	/*
	 * Seite2 - Seite5
	 * Deren Inhalte werden als Entities im DS gespeichert die folgenden
	 * Aufbau haben z.B. von Seite zwei
	 * 
	 * 	Entity			     | Key
	 *  vorEintrag+"/"+side2 | username
	 * 
	 * Die Entity setzt sich zusammen aus dem Eintrag der Liste in der
	 * vorherigen Seite (vorEintrag) und dem bezeichner der aktuellen Seite
	 * (side2). Der Key dieser Entity ist der username.
	 * 
	 * */

public class Sides2to5Entity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String eintrag;
	private String side;
	private String username;
	
	
	public Sides2to5Entity() {}


	public String getEintrag() {
		return eintrag;
	}


	public void setEintrag(String eintrag) {
		this.eintrag = eintrag;
	}


	public String getSide() {
		return side;
	}


	public void setSide(String side) {
		this.side = side;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	
	
	
}
