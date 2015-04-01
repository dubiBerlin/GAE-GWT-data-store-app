package com.score.pics.shared;

import java.io.Serializable;

public class TitleContentSourceProperty implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 147474933947475L;
	
	
	private String title;
	private String content;
	private String quelle;
	private String new_title;

	public TitleContentSourceProperty() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getQuelle() {
		return quelle;
	}

	public void setQuelle(String quelle) {
		this.quelle = quelle;
	}

	public String getNew_title() {
		return new_title;
	}

	public void setNew_title(String new_title) {
		this.new_title = new_title;
	}
	
	
}
