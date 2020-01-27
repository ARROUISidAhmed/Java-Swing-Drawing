/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.drawingmodel;

import fr.ups.m2ihm.drawingtools.drawingmodel.visitor.ShapeVisitor;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

/**
 *
 * @author arrouisa
 */
public class MacroShape extends Line {

    /**
     * The bounding box of the Macro.
     */
    private final Rectangle boundingBox;
    private final List<Shape> shapes;

    public MacroShape(Point firstPoint, Point secondPoint, List<Shape> shapes) {
        super(firstPoint, secondPoint);

        int x0 = Math.min(getP1().x, getP2().x);
        int y0 = Math.min(getP1().y, getP2().y);
        int w = Math.abs(getP1().x - getP2().x);
        int h = Math.abs(getP1().y - getP2().y);

        this.shapes = shapes;
        boundingBox = new Rectangle(x0, y0, w, h);
    }

    /**
     * Provide read only access to the bounding box of the macroShape.
     *
     * @return the rectangle that contains the oval.
     */
    public final Rectangle getBoundingBox() {
        return boundingBox;
    }

    public final List<Shape> getShapes() {
        return shapes;
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
    public String toString() {
        return "MacroShape{" + "boundingBox=" + boundingBox + '}';
    }

}
