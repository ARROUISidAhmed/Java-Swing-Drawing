/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.ui.drawingshapeimpl;

import fr.ups.m2ihm.drawingtools.drawingmodel.Line;
import fr.ups.m2ihm.drawingtools.drawingmodel.MacroShape;
import fr.ups.m2ihm.drawingtools.drawingmodel.Oval;
import fr.ups.m2ihm.drawingtools.drawingmodel.Shape;
import fr.ups.m2ihm.drawingtools.ui.DrawingShape;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;

/**
 *
 * @author arrouisa
 */
public class DrawingMacroShape extends DrawingShape {

    /**
     * Build one macroShape from a macroShape date and some graphical
     * attributes.
     *
     * @param aShape a macroShape data.
     * @param aColor the expected color of the drawing.
     * @param aStroke the expected stroke of the drawing.
     */
    public DrawingMacroShape(
            final MacroShape aShape,
            final Color aColor,
            final Stroke aStroke) {
        super(aShape, aColor, aStroke);
    }

    @Override
    public final void paint(final Graphics2D g) {
        final Color oldColor = g.getColor();
        final Stroke oldStroke = g.getStroke();
        g.setColor(getColorAttribute());
        g.setStroke(getStrokeAttribute());
        final MacroShape macroShape = (MacroShape) getShape();
        final Rectangle r = macroShape.getBoundingBox();

        macroShape.getShapes().forEach((Shape _item) -> {
            if (_item.getClass().equals(Oval.class)) {
                Oval oval = (Oval) _item;
                int x = oval.getBoundingBox().x;
                int y = oval.getBoundingBox().y;

                g.drawOval(r.x + x, r.y + y, oval.getBoundingBox().width, oval.getBoundingBox().height);
            }
            if (_item.getClass().equals(Line.class)) {
                Line line = (Line) _item;
                final int x0 = line.getP1().x;
                final int y0 = line.getP1().y;
                final int x1 = line.getP2().x;
                final int y1 = line.getP2().y;
                g.drawLine(r.x + x0, r.y + y0, r.x + x1, r.y + y1);
            }
        });

        g.setColor(oldColor);
        g.setStroke(oldStroke);
    }
}
