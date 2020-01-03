/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.drawingmodel;

import fr.ups.m2ihm.drawingtools.drawingmodel.visitor.ShapeVisitor;

/**
 *
 * @author David
 */
public abstract class Shape {

    public abstract void acceptVisitor(ShapeVisitor visitor);
}
