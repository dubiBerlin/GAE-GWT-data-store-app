package com.score.pics.shared;

public class CellContent {

	private String title;
	private String content;
	private String source;
	
	public CellContent(String t, String c, String s) {
		title = t;
		content = c;
		source = s;
	}

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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	

}
