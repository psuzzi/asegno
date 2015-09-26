package com.asegno.gef.example.controller;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.asegno.gef.example.model.Schema;
import com.asegno.gef.example.view.SchemaFigure;

public class SchemaEditPart extends AbstractGraphicalEditPart {

	public SchemaEditPart(Schema model) {
		setModel(model);
	}
	
	@Override
	protected IFigure createFigure() {
		return new SchemaFigure();
	}

	@Override
	protected void createEditPolicies() {
	}

}
