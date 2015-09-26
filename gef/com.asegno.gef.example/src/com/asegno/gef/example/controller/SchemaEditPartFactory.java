package com.asegno.gef.example.controller;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.asegno.gef.example.model.Schema;

/**  */
public class SchemaEditPartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof Schema) {
			return new SchemaEditPart((Schema) model);
		}
		return null;
	}

}
