
package com.asegno.gef.example.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.swt.widgets.Composite;

import com.asegno.gef.example.controller.SchemaEditPartFactory;
import com.asegno.gef.example.model.Schema;

/**
 * Sample part to show GEF Capabilities in Eclipse E4
 * 
 * @author p.suzzi
 *
 */
public class GefSamplePartEclipse3 extends GraphicalEditorWithFlyoutPalette {

	@Inject
	public GefSamplePartEclipse3() {
		// edit domain necessary
		setEditDomain(new DefaultEditDomain(this));
	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		super.createPartControl(parent);
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		return new PaletteRoot();
	}

	@Override
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		getGraphicalViewer().setContents(new Schema());
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
	}
	
	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		// viewer <-- factory + root edit part
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(new SchemaEditPartFactory());
		viewer.setRootEditPart(new ScalableFreeformRootEditPart());
	}
	
}