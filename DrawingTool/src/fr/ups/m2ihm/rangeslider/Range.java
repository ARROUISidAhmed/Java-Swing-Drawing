/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.rangeslider;

import fr.ups.m2ihm.javabeans.Shape;
import java.awt.Color;

/**
 *
 * @author arrouisa
 */
public class Range extends Shape {
    private double minValue;

    public static final String PROP_MINVALUE = "minValue";

    /**
     * Get the value of minValue
     *
     * @return the value of minValue
     */
    public double getMinValue() {
        return minValue;
    }

    /**
     * Set the value of minValue
     *
     * @param minValue new value of minValue
     */
    public void setMinValue(double minValue) {
        double oldMinValue = this.minValue;
        this.minValue = minValue;
        firePropertyChange(PROP_MINVALUE, oldMinValue, minValue);
    }

    private double maxValue;

    public static final String PROP_MAXVALUE = "maxValue";

    /**
     * Get the value of maxValue
     *
     * @return the value of maxValue
     */
    public double getMaxValue() {
        return maxValue;
    }

    /**
     * Set the value of maxValue
     *
     * @param maxValue new value of maxValue
     */
    public void setMaxValue(double maxValue) {
        double oldMaxValue = this.maxValue;
        this.maxValue = maxValue;
        firePropertyChange(PROP_MAXVALUE, oldMaxValue, maxValue);
    }


    public Range(double minValue, double maxValue, Color color, int type) {
        super(color, type);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
    
    public Range(double minValue,double maxValue,Color color){
        this(minValue,maxValue, color, RECT);
    }
}
