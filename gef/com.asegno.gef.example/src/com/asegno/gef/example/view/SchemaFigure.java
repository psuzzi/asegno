package com.asegno.gef.example.view;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.LineBorder;

/** View element for the schema */
public class SchemaFigure extends FreeformLayer {

	public SchemaFigure() {
		super();
		setLayoutManager(new FreeformLayout());
		setBorder(new LineBorder(1));
	}

}
