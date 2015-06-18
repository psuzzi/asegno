package com.asegno.e4.app.second.controls;

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
		composite.setLayout(new GridLayout(4, false));

		Button btnUserInput = new Button(composite, SWT.NONE);
		btnUserInput.setImage(ResourceManager.getPluginImage("com.asegno.e4.app.second", "icons/misc.png"));
		btnUserInput.setText("User Input");

		Button btnChart = new Button(composite, SWT.NONE);
		btnChart.setImage(ResourceManager.getPluginImage("com.asegno.e4.app.second", "icons/play.png"));
		btnChart.setText("Simulation");

		Button btnViewReport = new Button(composite, SWT.NONE);
		btnViewReport.setImage(ResourceManager.getPluginImage("com.asegno.e4.app.second", "icons/chart_curve.png"));
		btnViewReport.setText("View Charts");

		Button btnEditCharts = new Button(composite, SWT.NONE);
		btnEditCharts.setImage(ResourceManager.getPluginImage("com.asegno.e4.app.second", "icons/chart_line_edit.png"));
		btnEditCharts.setText("Edit Charts");

	}
}