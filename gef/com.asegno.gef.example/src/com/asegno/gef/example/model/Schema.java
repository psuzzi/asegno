package com.asegno.gef.example.model;

import java.util.ArrayList;
import java.util.List;

/** Diagram Container  */
public class Schema extends Element{
	
	public static String P_BLOCKS = "blocks";
	public static String P_CONNECTIONS = "connections";
	
	private List<Block> blocks = new ArrayList<Block>();
	private List<Connection> connections = new ArrayList<Connection>();
	
	public List<Block> getBlocks() {
		return blocks;
	}
	
	public List<Connection> getConnections() {
		return connections;
	}
	
	public void addBlock(Block block){
		blocks.add(block);
		pcs.firePropertyChange(P_BLOCKS, null, block);
	}
	
	public void removeBlock(Block block){
		blocks.remove(block);
		pcs.firePropertyChange(P_BLOCKS, block, null);
	}
	
	public void addConnection(Connection connection){
		connections.add(connection);
		pcs.firePropertyChange(P_CONNECTIONS, null, connection);
	}
	
	public void removeConnection(Connection connection){
		connections.remove(connection);
		pcs.firePropertyChange(P_CONNECTIONS, connection, null);
	}
	
}
