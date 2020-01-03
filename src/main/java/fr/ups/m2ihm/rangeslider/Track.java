/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.rangeslider;

import fr.ups.m2ihm.javabeans.Shape;
import java.awt.Color;
import java.awt.Dimension;

/**
 *
 * @author arrouisa
 */
public class Track extends Shape {
    
    private double maxAllowedValue;

    public static final String PROP_MAXALLOWEDVALUE = "maxAllowedValue";

    /**
     * Get the value of maxAllowedValue
     *
     * @return the value of maxAllowedValue
     */
    public double getMaxAllowedValue() {
        return maxAllowedValue;
    }

    /**
     * Set the value of maxAllowedValue
     *
     * @param maxAllowedValue new value of maxAllowedValue
     */
    public void setMaxAllowedValue(double maxAllowedValue) {
        double oldMaxAllowedValue = this.maxAllowedValue;
        this.maxAllowedValue = maxAllowedValue;
        firePropertyChange(PROP_MAXALLOWEDVALUE, oldMaxAllowedValue, maxAllowedValue);
    }

    private double minAllowedValue;

    public static final String PROP_MINALLOWEDVALUE = "minAllowedValue";

    /**
     * Get the value of minAllowedValue
     *
     * @return the value of minAllowedValue
     */
    public double getMinAllowedValue() {
        return minAllowedValue;
    }

    /**
     * Set the value of minAllowedValue
     *
     * @param minAllowedValue new value of minAllowedValue
     */
    public void setMinAllowedValue(double minAllowedValue) {
        double oldMinAllowedValue = this.minAllowedValue;
        this.minAllowedValue = minAllowedValue;
        firePropertyChange(PROP_MINALLOWEDVALUE, oldMinAllowedValue, minAllowedValue);
    }

    

    public Track( double minAllowedValue,double maxAllowedValue,Color color, int type) {
        super(color, type);
        this.minAllowedValue = minAllowedValue;
        this.maxAllowedValue = maxAllowedValue;
        this.setPreferredSize(new Dimension(200, 50));
    }

    public Track(double minAllowedValue,double maxAllowedValue,Color color){
        this(minAllowedValue,maxAllowedValue,color,RECT);
    }
    
}
