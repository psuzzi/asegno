 
package com.asegno.e4app.first.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.widgets.Shell;
/**
 * Shows about dialog
 * @author p.suzzi
 *
 */
public class AboutHandler {
	
	@ Inject
	@ Named (IServiceConstants.ACTIVE_SHELL)
	Shell shell;
	
	@Execute
	public void execute() {
		
		
		TitleAreaDialog dialog = new TitleAreaDialog(shell);
		dialog.setTitle("About");
		dialog.setMessage("Basic application, released under EPL");
	}
		
}