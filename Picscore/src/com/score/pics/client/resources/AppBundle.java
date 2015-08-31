package com.score.pics.client.resources;

import org.helios.gwt.fonts.client.FontResource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface AppBundle extends ClientBundle {

public static final AppBundle INSTANCE = GWT.create(AppBundle.class);
	
	@Source("style.css")
	CSS getCss();
	
	@Source({"NATIONFF.TTF"})
	FontResource font();
}
