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

        int xmin = macroShape.getMin().x;
        int ymin = macroShape.getMin().y;

        int xmax = macroShape.getMax().x;
        int ymax = macroShape.getMax().y;

        Rectangle r = new Rectangle(macroShape.getBoundingBox().x, macroShape.getBoundingBox().y, ymax - ymin, xmax - xmin);

        macroShape.getShapes().forEach((Shape _item) -> {
            if (_item.getClass().equals(Oval.class)) {
                Oval oval = (Oval) _item;
                int x = oval.getBoundingBox().x + r.x - xmin;
                int y = oval.getBoundingBox().y + r.y - ymin;
                g.drawOval(x, y, oval.getBoundingBox().width, oval.getBoundingBox().height);
            }
            if (_item.getClass().equals(Line.class)) {
                Line line = (Line) _item;
                int x0 = line.getP1().x + r.x - xmin;
                int y0 = line.getP1().y + r.y - ymin;
                int x1 = line.getP2().x + r.x - xmin;
                int y1 = line.getP2().y + r.y - ymin;
                g.drawLine(x0, y0, x1, y1);
            }
        });

        g.setColor(oldColor);
        g.setStroke(oldStroke);
    }
}
