/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.rangeslider;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author arrouisa
 */
public class RangeSliderModel {

    private static final int DEFAULTMIN = 20;
    private static final int DEFAULTMAX = 80;
    private static final int DEFAULTMINALLOWED = 0;
    private static final int DEFAULTMAXALLOWED = 100;
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
        if (minValue >= minAllowed && minValue < maxValue) {

            double oldMinValue = this.minValue;
            this.minValue = minValue;
            propertyChangeSupport.firePropertyChange(PROP_MINVALUE, oldMinValue, minValue);
        }
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
        if (maxValue > minValue && maxValue <= maxAllowed) {

            double oldMaxValue = this.maxValue;
            this.maxValue = maxValue;
            propertyChangeSupport.firePropertyChange(PROP_MAXVALUE, oldMaxValue, maxValue);
        }
    }

    private double minAllowed;

    public static final String PROP_MINALLOWED = "minAllowed";

    /**
     * Get the value of minAllowed
     *
     * @return the value of minAllowed
     */
    public double getMinAllowed() {
        return minAllowed;
    }

    /**
     * Set the value of minAllowed
     *
     * @param minAllowed new value of minAllowed
     */
    public void setMinAllowed(double minAllowed) {
        double oldMinAllowed = this.minAllowed;
        this.minAllowed = minAllowed;
        propertyChangeSupport.firePropertyChange(PROP_MINALLOWED, oldMinAllowed, minAllowed);
    }

    private double maxAllowed;

    public static final String PROP_MAXALLOWED = "maxAllowed";

    /**
     * Get the value of maxAllowed
     *
     * @return the value of maxAllowed
     */
    public double getMaxAllowed() {
        return maxAllowed;
    }

    /**
     * Set the value of maxAllowed
     *
     * @param maxAllowed new value of maxAllowed
     */
    public void setMaxAllowed(double maxAllowed) {

        double oldMaxAllowed = this.maxAllowed;
        this.maxAllowed = maxAllowed;
        propertyChangeSupport.firePropertyChange(PROP_MAXALLOWED, oldMaxAllowed, maxAllowed);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param propertyName
     * @param listener
     */
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param propertyName
     * @param listener
     */
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public RangeSliderModel(double minValue, double maxValue, double minAllowed, double maxAllowed) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.minAllowed = minAllowed;
        this.maxAllowed = maxAllowed;
    }

    public RangeSliderModel() {
        this(DEFAULTMIN, DEFAULTMAX, DEFAULTMINALLOWED, DEFAULTMAXALLOWED);
    }

    void setRangeValues(double min, double max) {
        if (min >= minAllowed && min < maxValue) {
            if (max > minValue && max <= maxAllowed) {

                double oldMinValue = this.minValue;
                this.minValue = min;
                double oldMaxValue = this.maxValue;
                this.maxValue = max;
                propertyChangeSupport.firePropertyChange(PROP_MINVALUE, oldMinValue, minValue);
                propertyChangeSupport.firePropertyChange(PROP_MAXVALUE, oldMaxValue, maxValue);
            }
        }
    }

}
