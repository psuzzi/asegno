
package com.asegno.e4.app.second.parts;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.nebula.visualization.xygraph.dataprovider.CircularBufferDataProvider;
import org.eclipse.nebula.visualization.xygraph.dataprovider.ISample;
import org.eclipse.nebula.visualization.xygraph.figures.Axis;
import org.eclipse.nebula.visualization.xygraph.figures.ToolbarArmedXYGraph;
import org.eclipse.nebula.visualization.xygraph.figures.Trace;
import org.eclipse.nebula.visualization.xygraph.figures.Trace.PointStyle;
import org.eclipse.nebula.visualization.xygraph.figures.XYGraph;
import org.eclipse.nebula.visualization.xygraph.util.Preferences;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import com.asegno.e4.app.second.ColorConstants;

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

		xyGraph = new XYGraph();

		ToolbarArmedXYGraph toolbarArmedXYGraph = new ToolbarArmedXYGraph(xyGraph);

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

		mmListener = new MouseMotionListener() {

			boolean underlined = false;

			@Override
			public void mouseDragged(MouseEvent me) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent me) {
				xyGraph.setCursor(Cursors.CROSS);
				underlined = true;
				xyGraph.repaint();
			}

			@Override
			public void mouseExited(MouseEvent me) {
				underlined = false;
				xyGraph.setCursor(null);
				xyGraph.repaint();
			}

			@Override
			public void mouseHover(MouseEvent me) {
				// TODO Auto-generated method stub
				Point location = new Point(me.getLocation().x, me.getLocation().y);
				Trace trace = getHoveredTrace(location, 5);
			}

			@Override
			public void mouseMoved(MouseEvent me) {
				// TODO Auto-generated method stub

			}

		};
		xyGraph.getPlotArea().addMouseMotionListener(mmListener);

	}

	MouseMotionListener mmListener;
	private XYGraph xyGraph;

	private final static int CIRCLE_DIAMETER = 8;

	/**
	 * Find the closest trace hovered within the given radius from the given
	 * location. Complexity = nTraces*nSamples
	 *
	 * @param location
	 * @param radius
	 * @return
	 */
	private Trace getHoveredTrace(Point location, int radius) {
		List<Trace> traces = xyGraph.getPlotArea().getTraceList();
		double[] minDistances = new double[traces.size()];
		for (int i = 0; i < traces.size(); i++) {
			Trace trace = traces.get(i);
			Axis xAxis = trace.getXAxis();
			Axis yAxis = trace.getYAxis();
			ISample tempSample = null;
			minDistances[i] = Double.POSITIVE_INFINITY;
			double d;
			for (ISample s : trace.getHotSampleList()) {
				d = Math.sqrt(Math.pow(xAxis.getValuePosition(s.getXValue(), false) - location.x, 2)
						+ Math.pow(yAxis.getValuePosition(s.getYValue(), false) - location.y, 2));
				if (minDistances[i] > d) {
					minDistances[i] = d;
					tempSample = s;
				}
				if (tempSample != null) {
					System.out.println(String.format("sample (%s, %s) at point (%s, %s)", tempSample.getXValue(),
							tempSample.getYValue(), location.x, location.y));

					double xValue = tempSample.getXValue();
					double yValue = tempSample.getYValue();
					int x = xAxis.getValuePosition(xValue, false);
					int y = yAxis.getValuePosition(yValue, false);

					xyGraph.getPlotArea().add(new CircleFigure(x, y, CIRCLE_DIAMETER));
					xyGraph.getPlotArea().revalidate();
					xyGraph.getPlotArea().repaint();
					// xyGraph.repaint();
				}
			}
		}
		// TODO Auto-generated method stub
		return null;
	}

	public static class CircleFigure extends Figure{

		private int x;
		private int y;
		private int diameter;

		public CircleFigure(int x, int y, int diameter) {
			this.x = x;
			this.y = y;
			this.diameter = diameter;
		}

		@Override
		protected void paintFigure(Graphics graphics) {
			if (Preferences.useAdvancedGraphics())
				graphics.setAntialias(SWT.ON);
			graphics.setForegroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
			graphics.drawOval(x - (diameter / 2), y - (diameter / 2), diameter, diameter);
		}

	}

	// private void addMouseListener(Trace trace) {
	// MouseMotionListener mml = new MouseMotionListener.Stub() {
	// @Override
	// public void mouseMoved(MouseEvent me) {
	// Point mouseLocation = new Point(me.getLocation().x, me.getLocation().y);
	// ISample tempSample = null;
	// double minD = Double.POSITIVE_INFINITY;
	// double d;
	// for (ISample s : trace.getHotSampleList()) {
	// d = Math.sqrt(Math.pow(trace.getXAxis().getValuePosition(s.getXValue(),
	// false) - mouseLocation.x, 2)
	// + Math.pow(trace.getYAxis().getValuePosition(s.getYValue(), false) -
	// mouseLocation.y, 2));
	// if (minD > d) {
	// minD = d;
	// tempSample = s;
	// }
	// }
	// if (tempSample != null) {
	// System.out.println(String.format("sample (%s, %s) at point (%s, %s)",
	// tempSample.getXValue(),
	// tempSample.getYValue(), mouseLocation.x, mouseLocation.y));
	//
	// }
	// // me.consume();
	// }
	// };
	// trace.addMouseMotionListener(mml);
	//
	// }

	/**
	 * Release resources
	 */
	@PreDestroy
	void dispose() {
		xyGraph.removeMouseMotionListener(mmListener);
	}

	public static class CirclePointer extends Figure {

	}

}