/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.javabeans;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;

/**
 *
 * @author arrouisa
 */
public class Shape extends JComponent {


    public static final int OVAL = 1;
    public static final int RECT = 2;
    public static final int RECTR = 3;
    public static final int STAR = 4;
    public static final int DEFAULT_SHAPE= OVAL;
    public static final Color DEFAULT_COLOR = Color.BLUE;
    
    private int type;
    public static final String PROP_TYPE = "type";
    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public int getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(int type) {
        int oldType = this.type;
        this.type = type;
        propertyChangeSupport.firePropertyChange(PROP_TYPE, oldType, type);
    }

        private Color color;

    public static final String PROP_COLOR = "color";

    /**
     * Get the value of color
     *
     * @return the value of color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Set the value of color
     *
     * @param color new value of color
     */
    public void setColor(Color color) {
        Color oldColor = this.color;
        this.color = color;
        propertyChangeSupport.firePropertyChange(PROP_COLOR, oldColor, color);
        repaint();
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

    
    public Shape(final Color color,final  int type) {
        super();
        this.color = color;
        this.type = type;
    }

    
    public Shape() {
        this(DEFAULT_COLOR,DEFAULT_SHAPE);
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(
             RenderingHints.KEY_ANTIALIASING,
             RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);
        g.setColor(color);
        switch (type) {
            case OVAL:
                g.fillOval(0, 0, getWidth(), getHeight());
                break;
            case RECT:
                g.fillRect(0, 0, getWidth(), getHeight());
                break;
            case RECTR:
                g.fillRoundRect(0, 0, getWidth(), getHeight(), getWidth() / 2, getHeight() / 2);
               break;
        }

    }

    @Override
    public boolean contains(int x, int y) {
        boolean contains = false;
        switch (type) {
            case OVAL:
                contains = OvalContains(x, y,getWidth(),getHeight()); 
                break;
            case RECT:
                contains = super.contains(x,y);
                break;
            case RECTR:
                contains = super.contains(x,y) && OvalContains(x, y,getWidth()/2,getHeight()/2);
               break;
        } 
        return contains;
    }

    private boolean OvalContains(int x, int y,int width, int height) {
        boolean contains;
        //Compute ratio
        float a = ((float) x / (float) width);
        float b = ((float) y / (float) height);
        // Square the ratio
        a *= a;
        b *= b;
        //Contains
        contains = ((a + b) <= 1);
        return contains;
    }
    
    

}
