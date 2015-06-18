
package com.asegno.e4.app.second.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.nebula.visualization.xygraph.dataprovider.CircularBufferDataProvider;
import org.eclipse.nebula.visualization.xygraph.dataprovider.ISample;
import org.eclipse.nebula.visualization.xygraph.figures.ToolbarArmedXYGraph;
import org.eclipse.nebula.visualization.xygraph.figures.Trace;
import org.eclipse.nebula.visualization.xygraph.figures.Trace.PointStyle;
import org.eclipse.nebula.visualization.xygraph.figures.XYGraph;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.asegno.e4.app.second.ColorConstants;
import com.asegno.e4.app.second.parts.listeners.MouseMotionAdapter;

/**
 * Part containing a Chart
 *
 * @author p.suzzi
 *
 */
public class ChartPart {

	public static String ELEMENT_ID = "com.asegno.e4.app.second.part.center.chart";

	@Inject
	public ChartPart() {

	}

	@PostConstruct
	public void postConstruct(Composite parent) {

		final Canvas canvas = new Canvas(parent, SWT.NONE);
		final LightweightSystem lws = new LightweightSystem(canvas);

		XYGraph xyGraph = new XYGraph();

		ToolbarArmedXYGraph toolbarArmedXYGraph = new ToolbarArmedXYGraph(xyGraph);

		xyGraph.setTitle("Toolbar Armed XYGraph Example");
		xyGraph.setBackgroundColor(ColorConstants.AZURE);
		xyGraph.setForegroundColor(ColorConstants.DARK_GRAY);


		lws.setContents(toolbarArmedXYGraph);

		xyGraph.primaryXAxis.setShowMajorGrid(true);
		xyGraph.primaryYAxis.setShowMajorGrid(true);

		CircularBufferDataProvider traceDataProvider = new CircularBufferDataProvider(false);
		traceDataProvider.setBufferSize(100);
		traceDataProvider.setCurrentXDataArray(new double[] { 1, 2, 3, 4, 50 });
		traceDataProvider.setCurrentYDataArray(new double[] { 11, 44, 55, 45, 88, 98, 52, 23 });

		// create the trace
		Trace trace = new Trace("Trace1-XY Plot", xyGraph.primaryXAxis, xyGraph.primaryYAxis, traceDataProvider);

		// set trace property
		trace.setPointStyle(PointStyle.XCROSS);
		// addMouseListener(trace);

		// add the trace to xyGraph
		xyGraph.addTrace(trace);

	}

	private void addMouseListener(Trace trace) {
		MouseMotionListener mml = new MouseMotionAdapter() {
			@Override
			public void mouseHover(MouseEvent me) {
				Point mouseLocation = new Point(me.getLocation().x, me.getLocation().y);
				ISample tempSample = null;
				double minD = Double.POSITIVE_INFINITY;
				double d;
				for (ISample s : trace.getHotSampleList()) {
					d = Math.sqrt(Math.pow(trace.getXAxis().getValuePosition(s.getXValue(), false) - mouseLocation.x, 2)
							+ Math.pow(trace.getYAxis().getValuePosition(s.getYValue(), false) - mouseLocation.y, 2));
					if (minD > d) {
						minD = d;
						tempSample = s;
					}
				}
				if (tempSample != null) {
					System.out.println(String.format("sample (%s, %s) at point (%s, %s)", tempSample.getXValue(),
							tempSample.getYValue(), mouseLocation.x, mouseLocation.y));

				}
				// me.consume();
			}
		};
		trace.addMouseMotionListener(mml);

	}

	/**
	 * Release resources
	 */
	@PreDestroy
	void dispose() {

	}

	public static class CirclePointer extends Figure {

	}

}