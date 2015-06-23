package com.e4.app02.chart.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.nebula.visualization.xygraph.figures.XYGraph;

public class ToolbarArmedWwbChart extends Figure {

	final private XYGraph xyGraph;
	final private WwbGraphToolbar toolbar;
	
	private boolean transparent;
	private final int MARGIN = 3;
	
	public ToolbarArmedWwbChart() {
		this(new XYGraph());
	}

	public ToolbarArmedWwbChart(XYGraph xyGraph) {
		this.xyGraph = xyGraph;
		toolbar = new WwbGraphToolbar(this.xyGraph);
		xyGraph.setOpaque(false);
		toolbar.setOpaque(false);
		add(toolbar);
		add(xyGraph);
	}
	
	@Override
	protected void layout() {
		Rectangle clientArea = getClientArea().getCopy();
		if (toolbar.isVisible()) {
			toolbar.invalidate();
			Dimension size = toolbar.getPreferredSize(clientArea.width - MARGIN, -1);
			toolbar.setBounds(new Rectangle(clientArea.x + MARGIN, clientArea.y + MARGIN, size.width, size.height));
			clientArea.y += size.height + 2 * MARGIN;
			clientArea.height -= size.height + 2 * MARGIN;
		}
		xyGraph.setBounds(new Rectangle(clientArea));

		super.layout();
	}
	
	/**
	 * @return the xyGraph
	 */
	public XYGraph getXYGraph() {
		return xyGraph;
	}

	@Override
	public boolean isOpaque() {
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paintFigure(final Graphics graphics) {
		if (!transparent)
			graphics.fillRectangle(getClientArea());
		super.paintFigure(graphics);
	}
	
	
}
