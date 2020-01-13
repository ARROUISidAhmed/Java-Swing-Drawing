/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.drawingmodel;

import java.awt.Point;

/**
 *
 * @author arrouisa
 */
public abstract class LocalizedCommand implements Command {

    protected Point anchorPoint;
    
    @Override
    public abstract void execute();

    @Override
    public abstract void undo();

    public Point getAnchorPoint() {
        return anchorPoint;
    }

    public void setAnchorPoint(Point anchorPoint) {
        this.anchorPoint = anchorPoint;
    }
    
    
}
