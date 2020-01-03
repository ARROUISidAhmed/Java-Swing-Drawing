/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.rangeslider;

import fr.ups.m2ihm.javabeans.Shape;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author arrouisa
 */
public class Thumb extends Shape {
    
   

    private double currentValue;

    public static final String PROP_CURRENTVALUE = "currentValue";

    /**
     * Get the value of currentValue
     *
     * @return the value of currentValue
     */
    public double getCurrentValue() {
        return currentValue;
    }

    /**
     * Set the value of currentValue
     *
     * @param currentValue new value of currentValue
     * 
     */
    public void setCurrentValue(double currentValue) {
        double oldCurrentValue = this.currentValue;
        this.currentValue = currentValue;
        firePropertyChange(PROP_CURRENTVALUE, oldCurrentValue, currentValue);
    }

   public Thumb(double currentValue, Color color, int type) {
        super(color, type);
        this.currentValue = currentValue;
        
    }

    
    public Thumb(double currentValue,Color color){
        this(currentValue, color, DEFAULT_SHAPE);
    }

    
    
}
