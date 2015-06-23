package com.e4.app02.controls;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.ResourceManager;

/**
 * Main output toolcontrol
 *
 * @author p.suzzi
 *
 */
public class OutputToolControl {

	@PostConstruct
	public void createGui(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBounds(0, 0, 472, 54);
		composite.setLayout(new GridLayout(4, false));

		Button btnUserInput = new Button(composite, SWT.NONE);
		btnUserInput.setImage(
				ResourceManager.getPluginImage("com.e4.app02", "icons/buttons/arrow_rotate_anticlockwise.png"));
		btnUserInput.setText("User Input");

		Button btnChart = new Button(composite, SWT.NONE);
		btnChart.setImage(ResourceManager.getPluginImage("com.e4.app02", "icons/buttons/buttons_20.png"));
		btnChart.setText("Simulation");

		Button btnViewReport = new Button(composite, SWT.NONE);
		btnViewReport.setImage(ResourceManager.getPluginImage("com.e4.app02", "icons/buttons/chart_curve.png"));
		btnViewReport.setText("View Charts");

		Button btnEditCharts = new Button(composite, SWT.NONE);
		btnEditCharts.setImage(ResourceManager.getPluginImage("com.e4.app02", "icons/buttons/chart_line_edit.png"));
		btnEditCharts.setText("Edit Charts");

	}
}