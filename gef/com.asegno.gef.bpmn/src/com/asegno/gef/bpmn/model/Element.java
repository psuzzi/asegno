package com.asegno.gef.bpmn.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.draw2d.geometry.Rectangle;

/** Base model for elements of a schema */
public class Element {

	transient protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	protected void firePropertyChange(String prop, Object oldVal, Object newVal) {
		pcs.firePropertyChange(prop, oldVal, newVal);
	}

	public static final String P_NAME = "name";
	public static final String P_BOUNDS = "bounds";
	
	
	private String name;
	private Rectangle bounds;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		pcs.firePropertyChange(P_NAME, this.name, this.name = name);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void setBounds(Rectangle bounds) {
		pcs.firePropertyChange(P_BOUNDS, this.bounds, this.bounds = bounds);
	}
	

}
