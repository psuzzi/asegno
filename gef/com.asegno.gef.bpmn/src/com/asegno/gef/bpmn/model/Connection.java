package com.asegno.gef.bpmn.model;

/** model of a connection */
public class Connection extends Element {
	
	private static final String P_SOURCE = "source";
	private static final String P_TARGET = "target";

	private Block source;
	
	private Block target;
	
	public Block getSource() {
		return source;
	}
	
	public void setSource(Block source) {
		pcs.firePropertyChange(P_SOURCE, this.source, this.source = source);
	}
	
	public Block getTarget() {
		return target;
	}
	
	public void setTarget(Block target) {
		pcs.firePropertyChange(P_TARGET, this.target, this.target = target);
	}
}
