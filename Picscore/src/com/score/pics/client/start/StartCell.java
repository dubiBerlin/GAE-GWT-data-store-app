package com.score.pics.client.start;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.googlecode.mgwt.ui.client.widget.list.celllist.Cell;

public class StartCell implements Cell<String>{

	private static Template TEMPLATE = GWT.create(Template.class);
	
	public interface Template extends SafeHtmlTemplates{
		@SafeHtmlTemplates.Template("<table width='100%'>" +
										"<tr>" +
											"<td align='left'>{0}</td>" +
										"<tr>" +
									"</table>")
		SafeHtml content(String content);
	}

	@Override
	public void render(SafeHtmlBuilder safeHtmlBuilder, String model) {
		SafeHtml content = TEMPLATE.content(model);
		safeHtmlBuilder.append(content);
		
	}

	@Override
	public boolean canBeSelected(String model) {
		
		return true;
	}
	
	public StartCell() {
		// TODO Auto-generated constructor stub
	}

}
