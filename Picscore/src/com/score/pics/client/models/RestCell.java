package com.score.pics.client.models;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.googlecode.mgwt.ui.client.widget.list.celllist.Cell;
import com.score.pics.shared.CellContent;

public class RestCell implements Cell<CellContent>{

	private static Template TEMPLATE = GWT.create(Template.class);
	
	public interface Template extends SafeHtmlTemplates{
		@SafeHtmlTemplates.Template("<table width='100%'>" +
										"<tr>" +
//											"<td align='left' style='font-size:1.1em;'>{0}</td>" +
											"<td align='left' >{0}</td>" +
											//"<th align='left' style='bord'><h4>{0}</h4></th>" +
										"<tr>" +
//										"<tr>" +
//											"<td style='height:5px'></td>" +
//										"<tr>" +
										"<tr>" +
											"<td align='left'>{1}</td>" +
										"<tr>" +
//										"<tr>" +
//											"<td style='height:5px'></td>" +
//										"<tr>" +
										"<tr>" +
											"<td align='left'>{2}</td>" +
										"<tr>" +
									"</table>")
		SafeHtml content(String title, String content, String source);
	}

	@Override
	public void render(SafeHtmlBuilder safeHtmlBuilder, CellContent model) {
		SafeHtml content = TEMPLATE.content(model.getTitle(), model.getContent(), model.getSource());
		safeHtmlBuilder.append(content);
		
	}

	@Override
	public boolean canBeSelected(CellContent model) {
		return true;
	}

}
