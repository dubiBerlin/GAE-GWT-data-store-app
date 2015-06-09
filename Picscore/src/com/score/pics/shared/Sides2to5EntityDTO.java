package com.score.pics.shared;

import java.io.Serializable;
import java.util.List;

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

public class Sides2to5EntityDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;
	// for the Entity ancestor path. this is the key of the previous side
	//private String key_of_previous_side; 
	private String side;
	private String username;
	private List<String>ancestorPath;
	
	public Sides2to5EntityDTO() {}


	public List<String> getAncestorPath() {
		return ancestorPath;
	}


	public void setAncestorPath(List<String> ancestorPath) {
		this.ancestorPath = ancestorPath;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String eintrag) {
		this.title = eintrag;
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
