package com.e4.app02.chart.figures;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.Button;
import org.eclipse.draw2d.ButtonGroup;
import org.eclipse.draw2d.ChangeEvent;
import org.eclipse.draw2d.ChangeListener;
import org.eclipse.draw2d.Clickable;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToggleButton;
import org.eclipse.draw2d.ToggleModel;
import org.eclipse.nebula.visualization.internal.xygraph.toolbar.WrappableToolbarLayout;
import org.eclipse.nebula.visualization.xygraph.figures.XYGraph;
import org.eclipse.nebula.visualization.xygraph.figures.ZoomType;
import org.eclipse.nebula.visualization.xygraph.util.XYGraphMediaFactory;

/**
 * Toolbar with only the buttons needed in WWb
 * @author p.suzzi
 *
 */
public class CustomGraphToolbar extends Figure{
	
	private final static int BUTTON_SIZE = 25;

	final private XYGraph xyGraph;

	final private ButtonGroup zoomGroup;
	
	final private Map<ZoomType, ToggleModel> zoomButtonModelMap = new HashMap<ZoomType, ToggleModel>();

	public CustomGraphToolbar(final XYGraph xyGraph) {
		this.xyGraph = xyGraph;
		setLayoutManager(new WrappableToolbarLayout());
		
		final Button autoScaleButton = new Button(XYGraphMediaFactory.getInstance()
				.getImage("images/AutoScale.png"));
		autoScaleButton.setToolTip(new Label("Perform Auto Scale"));
		addButton(autoScaleButton);
		autoScaleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				xyGraph.performAutoScale();
			}
		});
		
		// zoom buttons
		zoomGroup = new ButtonGroup();
		addZoomButton(ZoomType.DYNAMIC_ZOOM);
		addZoomButton(ZoomType.NONE);
	}
	
	private void addZoomButton(final ZoomType zoomType) {
		final ImageFigure imageFigure = new ImageFigure(zoomType.getIconImage());
		final Label tip = new Label(zoomType.getDescription());
		final ToggleButton button = new ToggleButton(imageFigure);
		button.setBackgroundColor(ColorConstants.button);
		button.setOpaque(true);
		final ToggleModel model = new ToggleModel();
		model.addChangeListener(new ChangeListener() {
			public void handleStateChanged(ChangeEvent event) {
				if (event.getPropertyName().equals("selected") && button.isSelected()) {
					xyGraph.setZoomType(zoomType);
				}
			}
		});

		button.setModel(model);
		zoomButtonModelMap.put(zoomType, model);
		button.setToolTip(tip);
		addButton(button);
		zoomGroup.add(model);
		
		if (zoomType == ZoomType.NONE)
			zoomGroup.setDefault(model);
		
		xyGraph.addPropertyChangeListener(XYGraph.PROPERTY_ZOOMTYPE, new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				zoomGroup.setSelected(zoomButtonModelMap.get(evt.getNewValue()));
			}
		});
	}

	public void addButton(Clickable button) {
		button.setPreferredSize(BUTTON_SIZE, BUTTON_SIZE);
		add(button);
	}

}

