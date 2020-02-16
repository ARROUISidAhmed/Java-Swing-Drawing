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
         int x0 = getP2().x;
        int y0 = getP2().y;
        int w = getP2().x;
        int h = getP2().y;

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

    public Point getMin() {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for (Shape s : this.shapes) {
            if (s.getClass().equals(Oval.class)) {
                Oval oval = (Oval) s;
                int x = oval.getBoundingBox().x;
                int y = oval.getBoundingBox().y;
                minX = Math.min(minX, x);
                minY = Math.min(minY, y);

            }
            if (s.getClass().equals(Line.class)) {
                Line line = (Line) s;
                final int x0 = line.getP1().x;
                final int y0 = line.getP1().y;
                final int x1 = line.getP2().x;
                final int y1 = line.getP2().y;
                minX = Math.min(minX, x0);
                minY = Math.min(minY, y0);

                minX = Math.min(minX, x1);
                minY = Math.min(minY, y1);
            }
        }
        return new Point(minX, minY);
    }

    public Point getMax() {
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Shape s : this.shapes) {
            if (s.getClass().equals(Oval.class)) {
                Oval oval = (Oval) s;
                int x = oval.getBoundingBox().x;
                int y = oval.getBoundingBox().y;
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);

            }
            if (s.getClass().equals(Line.class)) {
                Line line = (Line) s;
                final int x0 = line.getP1().x;
                final int y0 = line.getP1().y;
                final int x1 = line.getP2().x;
                final int y1 = line.getP2().y;
                maxX = Math.max(maxX, x0);
                maxY = Math.max(maxY, y0);

                maxX = Math.max(maxX, x1);
                maxY = Math.max(maxY, y1);
            }
        }
        return new Point(maxX, maxY);
    }

}
