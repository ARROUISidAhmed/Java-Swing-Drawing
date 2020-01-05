/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.ui.drawingshapeimpl;

import fr.ups.m2ihm.drawingtools.drawingmodel.Line;
import fr.ups.m2ihm.drawingtools.drawingmodel.Oval;
import fr.ups.m2ihm.drawingtools.drawingmodel.Shape;
import fr.ups.m2ihm.drawingtools.drawingmodel.visitor.ShapeVisitor;
import fr.ups.m2ihm.drawingtools.ui.DrawingShape;
import java.awt.Color;
import java.awt.Stroke;

/**
 *
 * @author David
 */
public final class DrawingShapeBuilder implements ShapeVisitor {

    /**
     * Instance of class DrawingShapeBuilder used as a singleton.
     */
    private static final DrawingShapeBuilder DEFAULT_BUILDER;

    static {
        DEFAULT_BUILDER = new DrawingShapeBuilder();
    }

    /**
     * Private constructor for utility classes.
     */
    private DrawingShapeBuilder() {
    }

    /**
     * Provides the singleton of the class DrawingShapeBuilder.
     *
     * @return the default instance of the class DrawingShapeBuilder.
     */
    public static DrawingShapeBuilder getInstance() {
        return DEFAULT_BUILDER;
    }

    /**
     * A DrawingShape built while visiting a Shape. Used temporary between the
     * beginning of the visit until the Drawing is returned to the invoker.
     */
    private static DrawingShape temporaryCreatedDrawingShape;

    /**
     * A Color used to build a DrawingShape while visiting a Shape. Used
     * temporary between the beginning of the visit until the Drawing is
     * returned to the invoker.
     */
    private static Color temporaryCreatedDrawingShapeColor;

    /**
     * A Stroke used to build a DrawingShape while visiting a Shape. Used
     * temporary between the beginning of the visit until the Drawing is
     * returned to the invoker.
     */
    private static Stroke temporaryCreatedDrawingShapeStroke;

    /**
     * Build a DrawingShape.
     *
     * @param shape the shape data to be added.
     * @param aColor the expected color of the drawing.
     * @param aStroke the expected stroke of the drawing.
     *
     * @return the corresponding DrawingShape.
     */
    public static DrawingShape getDrawingShape(
            final Shape shape,
            final Color aColor,
            final Stroke aStroke) {
        temporaryCreatedDrawingShapeColor = aColor;
        temporaryCreatedDrawingShapeStroke = aStroke;
        shape.acceptVisitor(getInstance());
        return temporaryCreatedDrawingShape;

    }

    @Override
    public void visit(final Line line) {
        temporaryCreatedDrawingShape = new DrawingLine(
                line,
                temporaryCreatedDrawingShapeColor,
                temporaryCreatedDrawingShapeStroke);
    }

    @Override
    public void visit(final Oval oval) {
        temporaryCreatedDrawingShape = new DrawingOval(
                oval,
                temporaryCreatedDrawingShapeColor,
                temporaryCreatedDrawingShapeStroke);
    }
}
