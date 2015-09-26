
package com.asegno.gef.example.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Sample part to show GEF Capabilities in Eclipse E4
 * 
 * @author p.suzzi
 *
 */
public class GefSamplePart  {

	@Inject
	public GefSamplePart() {
	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		
		Label l = new Label(parent, SWT.FILL);
		l.setText("Editor Base");
		
	}

	
}