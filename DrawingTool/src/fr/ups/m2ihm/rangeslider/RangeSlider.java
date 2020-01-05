/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.rangeslider;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JLayeredPane;

/**
 *
 * @author arrouisa
 */
public class RangeSlider extends JLayeredPane {

    private final Track track;
    private final Thumb minThumb;
    private final Thumb maxThumb;
    private final Range range;
    private final RangeSliderModel model;

    public static final Color DEFAULT_COLOR = new Color(41, 128, 185);
    public static final Color DEFAULT_TRACK_COLOR = new Color(41, 128, 185, 50);
    public static final int DEFAULT_MAXALLOWED = 100;
    public static final int DEFAULT_MINALLOWED = 0;
    public static final int DEFAULT_MAX = 90;
    public static final int DEFAULT_MIN = 10;

    private double ratio;
    private int groupWidth, groupHeight, groupX, groupY;

    private int mapToPosition(double value) {
        return (int) ((track.getWidth() * value) / (model.getMaxAllowed() - model.getMinAllowed())) + groupX;
    }

    private double mapToValue(int offSet) {
        return (((model.getMaxAllowed() - model.getMinAllowed()) * offSet) / ((double) (track.getWidth()))) + model.getMinAllowed();
    }
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    private enum State {
        Init,
        Pressing,
        Dragging,
    }

    private class RangeMouseAdapter extends MouseAdapter {

        private int x0;
        private State state;

        public RangeMouseAdapter() {
            x0 = 0;
            state = State.Init;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            switch (state) {
                case Init:
                    state = State.Pressing;
                    x0 = e.getX();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            switch (state) {
                default:
                    state = State.Init;
                    break;
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            switch (state) {
                case Pressing:
                case Dragging:
                    state = State.Dragging;
                    Pair range = new Pair(model.getMinValue() + mapToValue(e.getX() - x0),
                            model.getMaxValue() + mapToValue(e.getX() - x0));
                    model.setRange(range);
                    break;
                default:
                    break;
            }
        }

    }

    public RangeSlider() {

        // Instantiate Layered Pane
        super();
        super.setLayout(null);

        //Instantiate Sub Components
        track = new Track(DEFAULT_MINALLOWED, DEFAULT_MAXALLOWED, DEFAULT_TRACK_COLOR);
        minThumb = new Thumb(DEFAULT_MIN, DEFAULT_COLOR);
        maxThumb = new Thumb(DEFAULT_MAX, DEFAULT_COLOR);
        range = new Range(DEFAULT_MIN, DEFAULT_MAX, DEFAULT_COLOR);
        model = new RangeSliderModel();

        //Add components to respective layers
        this.add(track, new Integer(0));
        this.add(range, new Integer(1));
        this.add(minThumb, new Integer(2));
        this.add(maxThumb, new Integer(2));

        //Set graphical properties
        this.setPreferredSize(new Dimension(100, 10));
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setAlignmentY(CENTER_ALIGNMENT);

        //Add views property listeners
        minThumb.addPropertyChangeListener(Thumb.PROP_CURRENTVALUE, (PropertyChangeEvent e) -> {
            model.setMinValue((double) e.getNewValue());
        });

        maxThumb.addPropertyChangeListener(Thumb.PROP_CURRENTVALUE, (e) -> {
            model.setMaxValue((double) e.getNewValue());
        });

        range.addPropertyChangeListener(Range.PROP_MINVALUE, (e) -> {
            model.setMinValue((double) e.getNewValue());
        });

        range.addPropertyChangeListener(Range.PROP_MAXVALUE, (e) -> {
            model.setMaxValue((double) e.getNewValue());
        });

        //Add model property listeners
        model.addPropertyChangeListener(RangeSliderModel.PROP_MINVALUE, (e) -> {
            //Configure minthumb
            minThumb.setCurrentValue((double) e.getNewValue());
            paintMinThumb();
        });

        model.addPropertyChangeListener(RangeSliderModel.PROP_RANGE, (e) -> {
            Pair<Double, Double> pair = (Pair) e.getNewValue();
            range.setMinValue(pair.getFirst());
            range.setMaxValue(pair.getSecond());
            paintRange();
        });

        model.addPropertyChangeListener(RangeSliderModel.PROP_MAXVALUE, (e) -> {
            maxThumb.setCurrentValue((double) e.getNewValue());
            paintMaxThumb();
        });

        //Add interaction listeners
        minThumb.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                model.setMinValue(mapToValue(e.getX()) + model.getMinValue());
            }
        });

        maxThumb.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                model.setMaxValue(mapToValue(e.getX()) + model.getMaxValue());
            }
        });

        RangeMouseAdapter rma = new RangeMouseAdapter();

        range.addMouseListener(rma);
        range.addMouseMotionListener(rma);

        track.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int distanceToMin = Math.abs(minThumb.getX() - e.getX());
                int distanceToMax = Math.abs(maxThumb.getX() - e.getX());

                if (distanceToMin <= distanceToMax) {
                    model.setMinValue(mapToValue(e.getX()));
                } else {
                    model.setMaxValue(mapToValue(e.getX()));
                }
            }
        });

    }

    private void paintMinThumb() {
        int minThumbRelativeX = mapToPosition(model.getMinValue());
        minThumb.setBounds(minThumbRelativeX - groupHeight, groupY - groupHeight, groupHeight * 2, groupHeight * 2);
    }

    private void paintMaxThumb() {
        int maxThumbRelativeX = mapToPosition(model.getMaxValue());
        maxThumb.setBounds(maxThumbRelativeX - groupHeight, groupY - groupHeight, groupHeight * 2, groupHeight * 2);
    }

    private void paintRange() {
        int rangeStart = mapToPosition(model.getMinValue());
        int rangeEnd = mapToPosition(model.getMaxValue());
        range.setBounds(rangeStart, groupY - (groupHeight / 2), rangeEnd - rangeStart, groupHeight);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);

        //Configure group
        groupWidth = width;
        groupHeight = 10;
        groupX = 0;
        groupY = height / 2;

        //Configure track
        track.setBounds(groupX, groupY - (groupHeight / 2), groupWidth, groupHeight);

        //Configure thumbs
        paintMinThumb();
        paintMaxThumb();

        //Configure range
        paintRange();
    }

    @Override
    public Dimension getPreferredSize() {
        final Dimension trackPreferredSize = track.getPreferredSize();
        return new Dimension(trackPreferredSize.width, trackPreferredSize.height + 20);
    }

}
