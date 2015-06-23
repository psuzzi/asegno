 
package com.e4.app02.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.nebula.visualization.xygraph.dataprovider.CircularBufferDataProvider;
import org.eclipse.nebula.visualization.xygraph.figures.ToolbarArmedXYGraph;
import org.eclipse.nebula.visualization.xygraph.figures.Trace;
import org.eclipse.nebula.visualization.xygraph.figures.Trace.PointStyle;
import org.eclipse.nebula.visualization.xygraph.figures.XYGraph;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.e4.app02.ColorConstants;
import com.e4.app02.chart.figures.ToolbarArmedWwbChart;

public class XYChartPart {
	
	public static String ELEMENT_ID = "com.e4.app02.part.center.chart";
	
	MouseMotionListener mmListener;
	private XYGraph xyGraph;
	
	@Inject
	public XYChartPart() {
		//TODO Your code here
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		final Canvas canvas = new Canvas(parent, SWT.NONE);
		final LightweightSystem lws = new LightweightSystem(canvas);

		xyGraph = new XYGraph();

		ToolbarArmedWwbChart toolbarArmedXYGraph = new ToolbarArmedWwbChart(xyGraph);

		xyGraph.setTitle("Toolbar Armed XYGraph Example");
		xyGraph.setBackgroundColor(ColorConstants.AZURE);
		xyGraph.setForegroundColor(ColorConstants.DARK_GRAY);

		lws.setContents(toolbarArmedXYGraph);

		xyGraph.primaryXAxis.setShowMajorGrid(true);
		xyGraph.primaryYAxis.setShowMajorGrid(true);

		CircularBufferDataProvider traceDataProvider = new CircularBufferDataProvider(false);
		traceDataProvider.setBufferSize(100);
		traceDataProvider.setCurrentXDataArray(new double[] { 1, 2, 3, 4, 50, 78, 55 });
		traceDataProvider.setCurrentYDataArray(new double[] { 11, 44, 55, 45, 88, 98, 52, 23 });

		// create the trace
		Trace trace = new Trace("Trace1-XY Plot", xyGraph.primaryXAxis, xyGraph.primaryYAxis, traceDataProvider);

		// set trace property
		trace.setPointStyle(PointStyle.XCROSS);

		// add the trace to xyGraph
		xyGraph.addTrace(trace);
	}
	
	
	
	
}