/*
 * Databinder: a simple bridge from Wicket to Hibernate
 * Copyright (C) 2006  Nathan Hamblen nathan@technically.us

 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package net.databinder.components;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.internal.HtmlHeaderContainer;
import org.apache.wicket.model.IModel;

/**
 * TextField that can be told to focus itself on the next request.  Works in cojunction with 
 * the onload handler. 
 */
public class FocusableTextField extends TextField {
	private boolean wantsFocus = false;

	/**
	 * 	@param id Wicket id
	 * @param model text field model
	 */
	public FocusableTextField(String id, IModel model) {
		super (id, model);
		add(ScriptLink.headerContributor(FocusableTextField.class));
	}

	/**
	 * @param id Wicket id
	 */
	public FocusableTextField(String id) {
		this(id, (IModel) null);
	}

	/**
	 * Page parameter no longer necessary; use other constructor.
	 * @deprecated
	 */
	public FocusableTextField(String id, IModel model, WebPage page) {
		this(id, model);
	}
	/**
	 * Page parameter no longer necessary; use other constructor.
	 * @deprecated
	 */
	public FocusableTextField(String id, WebPage page) {
		this(id);
	}
	
	@Override
	public void renderHead(HtmlHeaderContainer container) {
		super.renderHead(container);
		container.getHeaderResponse().renderOnLoadJavascript("initFocusableTextField();");
	}
	
	/**
	 * Request focus on next rendering.
	 */
	public void requestFocus() {
		wantsFocus = true;
	}
	
	/** Adds flagging id attribute if focus has been requested. */
	@Override
	protected void onComponentTag(ComponentTag tag) {
		if (wantsFocus) {
			tag.put("id", "focusMe");
			wantsFocus = false;
		}
		super.onComponentTag(tag);
	}
}