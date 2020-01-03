/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.javabeans;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 *
 * @author arrouisa
 */
public class ShapeLabel extends JLayeredPane {
    private Shape shape;
    private JLabel label;
    public static final String DEFAULT_TEXT= "Default text";
    public static final Color DEFAULT_FOREGROUND = Color.BLACK;
    public static final Color DEFAULT_BACKGROUND = Color.PINK;
    
    private final List<ShapeLabelListener> listeners;
    
    public ShapeLabel() {
        this(DEFAULT_TEXT,DEFAULT_FOREGROUND,DEFAULT_BACKGROUND);
    }
    
    public ShapeLabel(final String text,final Color foregroundColor,final Color backgroundColor){
        super();
        super.setLayout(null);
        label = new JLabel(text);
        shape = new Shape();
        listeners = new ArrayList<>();
        configureLabel(foregroundColor);
        configureShape(backgroundColor);
        
        label.addPropertyChangeListener(PROP_TEXT, (e) ->{
           propertyChangeSupport.firePropertyChange(PROP_TEXT,e.getOldValue(),e.getNewValue());
        });
        label.addPropertyChangeListener(PROP_FOREGROUNDCOLOR, (e) ->{
           propertyChangeSupport.firePropertyChange(PROP_FOREGROUNDCOLOR,e.getOldValue(),e.getNewValue());
        });
        shape.addPropertyChangeListener(Shape.PROP_COLOR, (e) ->{
           propertyChangeSupport.firePropertyChange(PROP_BACKGROUNDCOLOR,e.getOldValue(),e.getNewValue());
        });
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                fireShapeLabelEvent(new ShapeLabelEvent(this));
            }
        });
        
        
    }

    private void configureLabel(final Color foregroundColor){
        label.setForeground(foregroundColor);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        this.add(label,JLayeredPane.PALETTE_LAYER);
    }
    
    private void configureShape(final Color backgroundColor){
        shape.setColor(backgroundColor);
        shape.setType(Shape.RECTR);
        this.add(shape,JLayeredPane.DEFAULT_LAYER);
    }
    
    
    private String text;
    public static final String PROP_TEXT = "text";

    /**
     * Get the value of text
     *
     * @return the value of text
     */
    public String getText() {
        return label.getText();
    }

    /**
     * Set the value of text
     *
     * @param text new value of text
     */
    public void setText(String text) {
        label.setText(text);
    }
    
        private Color foregroundColor;

    public static final String PROP_FOREGROUNDCOLOR = "foregroundColor";

    /**
     * Get the value of foregroundColor
     *
     * @return the value of foregroundColor
     */
    public Color getForegroundColor() {
        return label.getForeground();
    }

    /**
     * Set the value of foregroundColor
     *
     * @param foregroundColor new value of foregroundColor
     */
    public void setForegroundColor(Color foregroundColor) {
        label.setForeground(foregroundColor);
    }

    
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private Color backgroundColor;

    public static final String PROP_BACKGROUNDCOLOR = "backgroundColor";

    /**
     * Get the value of backgroundColor
     *
     * @return the value of backgroundColor
     */
    public Color getBackgroundColor() {
        return shape.getColor();
    }

    /**
     * Set the value of backgroundColor
     *
     * @param backgroundColor new value of backgroundColor
     */
    public void setBackgroundColor(Color backgroundColor) {
        shape.setColor(backgroundColor);
    }

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

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height); 
        label.setBounds(0,0,width,height);
        shape.setBounds(0,0,width,height);
    }

    @Override
    public Dimension getPreferredSize() {
        final Dimension labelPreferedSize = label.getPreferredSize();
        return new Dimension(labelPreferedSize.width+50, labelPreferedSize.height+50);
    }

    @Override
    public boolean contains(int x, int y) {
        return shape.contains(x, y); //To change body of generated methods, choose Tools | Templates.
    }

    public void addShapeLabelListener(ShapeLabelListener listener){
        listeners.add(listener);
    }
    
    public void removeShapeLabelListener(ShapeLabelListener listener){
        listeners.remove(listener);
    }
    
    public void fireShapeLabelEvent(ShapeLabelEvent event){
        synchronized(listeners){
            listeners.stream().forEach((listener) -> listener.shapeLabelClicked(event));
        }
    }
    
}
