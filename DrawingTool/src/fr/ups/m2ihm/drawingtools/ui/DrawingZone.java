/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.ui;

import fr.ups.m2ihm.drawingtools.drawingmodel.Shape;
import fr.ups.m2ihm.drawingtools.ui.drawingshapeimpl.DrawingShapeBuilder;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author David
 */
public class DrawingZone extends JPanel {

    /**
     * Used for memory allocation.
     */
    private static final int DEFAULT_SHAPE_CAPACITY = 200;
    /**
     * The set of lines to draw.
     */
    private final List<DrawingShape> drawingShapes;

    /**
     * Build a drawing zone panel.
     */
    public DrawingZone() {
        drawingShapes = new ArrayList<>(DEFAULT_SHAPE_CAPACITY);
    }

    /**
     * Add one shape to the set of shapes and force repaint the scene.
     *
     * @param shape the shape data to be added.
     * @param aColor the expected color of the drawing.
     * @param aStroke the expected stroke of the drawing.
     */
    public final void addShape(
            final Shape shape,
            final Color aColor,
            final Stroke aStroke) {
        drawingShapes.add(DrawingShapeBuilder
                .getDrawingShape(shape, aColor, aStroke));
        repaint();
    }

    /**
     * Remove one shape from the set of shapes and force repaint the scene.
     *
     * @param shape the shape data to be added.
     * @param aColor the expected color of the drawing.
     * @param aStroke the expected stroke of the drawing.
     */
    public final void removeShape(
            final Shape shape,
            final Color aColor,
            final Stroke aStroke) {
        drawingShapes.remove(DrawingShapeBuilder
                .getDrawingShape(shape, aColor, aStroke));
        repaint();
    }

    @Override
    public final void paint(final Graphics g) {
        super.paint(g);
        drawingShapes.forEach((drawingShape) -> {
            drawingShape.paint((Graphics2D) g);
        });
    }

    /**
     * Clears the set of shapes.
     */
    public final void clearAll() {
        drawingShapes.clear();
        repaint();
    }

}
