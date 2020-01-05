/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.drawingmodel;

import fr.ups.m2ihm.drawingtools.drawingmodel.visitor.ShapeVisitor;
import java.awt.Point;
import java.util.Objects;

/**
 *
 * @author David
 */
public class Line extends Shape {

    /**
     * First segment point.
     */
    private final Point p1;
    /**
     * Second segment point.
     */
    private final Point p2;

    /**
     * Build a line segment using two points.
     *
     * @param firstPoint the first point.
     * @param secondPoint the second point.
     */
    public Line(final Point firstPoint, final Point secondPoint) {
        this.p1 = firstPoint;
        this.p2 = secondPoint;
    }

    /**
     * Returns the first point of the segment.
     *
     * @return the first point
     */
    public final Point getP1() {
        return p1;
    }

    /**
     * Returns the second point of the segment.
     *
     * @return the second point
     */
    public final Point getP2() {
        return p2;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (Objects.isNull(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Line other = (Line) obj;
        return Objects.equals(this.p1, other.p1)
                && Objects.equals(this.p2, other.p2);
    }

    @Override
    public final int hashCode() {
        int hash = Objects.hashCode(this.p1) + Objects.hashCode(this.p2);
        return hash;
    }

    @Override
    public String toString() {
        return "Line{" + "p1=" + p1 + ", p2=" + p2 + '}';
    }

    @Override
    public void acceptVisitor(final ShapeVisitor visitor) {
        visitor.visit(this);
    }

}
