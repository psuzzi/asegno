package com.e4.app02.controls;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Tool control for the status bar
 *
 * @author p.suzzi
 *
 */
public class StatusToolControl {

	private static final String STATUS = "Status";
	public static String ELEMENT_ID = "com.asegno.e4.app.second.toolcontrol.statustoolcontrol";
	private Label lblNewLabel;

	@PostConstruct
	public void createGui(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		lblNewLabel = new Label(parent, SWT.NONE);
		lblNewLabel.setText(STATUS);

	}

	public void setMessage(String message) {
		String text = STATUS + ((message == null || message.length() == 0) ? "" : (": " + message));
		lblNewLabel.setText(STATUS + ": " + text);
	}
}