/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.ui;

import fr.ups.m2ihm.drawingtools.drawingmodel.Shape;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.Objects;

/**
 *
 * @author David
 */
public abstract class DrawingShape {

    /**
     * The shape from the functional core.
     */
    private final Shape shape;
    /**
     * The color of the drawing.
     */
    private final Color colorAttribute;
    /**
     * The stroke of the drawing.
     */
    private final Stroke strokeAttribute;

    /**
     * Build one shape from a shape date and some graphical attributes.
     *
     * @param aShape a shape data.
     * @param aColor the expected color of the drawing.
     * @param aStroke the expected stroke of the drawing.
     */
    public DrawingShape(
            final Shape aShape,
            final Color aColor,
            final Stroke aStroke) {
        assert (Objects.nonNull(aColor));
        assert (Objects.nonNull(aStroke));
        this.shape = aShape;
        this.colorAttribute = aColor;
        this.strokeAttribute = aStroke;
    }

    @Override
    public final int hashCode() {
        int hash = Objects.hashCode(this.shape);
        return hash;
    }

    /**
     * Provide the shape data.
     *
     * @return the shape data.
     */
    public final Shape getShape() {
        return shape;
    }

    /**
     * Provide the color attribute of the drawing.
     *
     * @return the color attribute of the drawing.
     */
    public final Color getColorAttribute() {
        return colorAttribute;
    }

    /**
     * Provide the stroke attribute of the drawing.
     *
     * @return the stroke attribute of the drawing.
     */
    public final Stroke getStrokeAttribute() {
        return strokeAttribute;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrawingShape other = (DrawingShape) obj;
        if (!Objects.equals(this.shape, other.shape)) {
            return false;
        }
        if (!Objects.equals(this.colorAttribute, other.colorAttribute)) {
            return false;
        }
        return Objects.equals(this.strokeAttribute, other.strokeAttribute);
    }

    /**
     * Paint the drawing using its drawing attributes.
     *
     * @param g the graphics object used to paint.
     */
    public abstract void paint(final Graphics2D g);

}
