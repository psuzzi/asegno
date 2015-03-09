 
package com.asegno.e4app.first.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class APart {
	
	
	@Inject
	EMenuService menuService;
	
	@Inject
	public APart() {
	}
	
	@PostConstruct
	public void postConstruct(Composite parent, EMenuService menuService) {
		System.out.println( "APart postConstruct, initialized, menu service:" + menuService);
		TableViewer viewer = new TableViewer(parent, SWT.FULL_SELECTION|SWT.MULTI);
		menuService.registerContextMenu(viewer.getControl(), "com.asegno.e4app.first.popupmenu.0");
	}
	
	
	
	
}