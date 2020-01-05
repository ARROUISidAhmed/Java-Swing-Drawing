/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.drawingmodel;

import fr.ups.m2ihm.drawingtools.drawingmodel.visitor.ShapeVisitor;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author David
 */
public class Oval extends Line {

    /**
     * The bounding box of the oval.
     */
    private final Rectangle boundingBox;

    /**
     * Build an oval that fits a rectangle defined by two points.
     *
     * @param firstPoint the first point.
     * @param secondPoint the second point.
     */
    public Oval(final Point firstPoint, final Point secondPoint) {
        super(firstPoint, secondPoint);

        int x0 = Math.min(getP1().x, getP2().x);
        int y0 = Math.min(getP1().y, getP2().y);
        int w = Math.abs(getP1().x - getP2().x);
        int h = Math.abs(getP1().y - getP2().y);

        boundingBox = new Rectangle(x0, y0, w, h);
    }

    /**
     * Provide read only access to the bounding box of the oval.
     *
     * @return the rectangle that contains the oval.
     */
    public final Rectangle getBoundingBox() {
        return boundingBox;
    }

    @Override
    /**
     * Must be rewritten for each inheritance to allow the visitor to identify
     * the correct implementing class.
     */
    public final void acceptVisitor(final ShapeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public final String toString() {
        return "Oval{" + "p1=" + getP1() + ", p2=" + getP2() + '}';
    }

}
