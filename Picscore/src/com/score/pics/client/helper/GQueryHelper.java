package com.score.pics.client.helper;

import static com.google.gwt.query.client.GQuery.$;
import static com.google.gwt.query.client.plugins.Effects.Effects;

import com.google.gwt.query.client.Function;

public class GQueryHelper {

	public GQueryHelper() {
		// TODO Auto-generated constructor stub
	}
	
	public static void fadeInFadeOut(String parentId, String childDiv, final String childId){
		if($("#"+parentId).children().isEmpty()){
			$("#"+parentId).as(Effects).append($(childDiv)).hide().fadeIn(3000, new Function(){
				public void f() {
			        
					$("#"+childId).fadeOut(new Function(){
						public void f() {
							$(this).remove();
						}
					});
			      }
			});
		}
	}


}
