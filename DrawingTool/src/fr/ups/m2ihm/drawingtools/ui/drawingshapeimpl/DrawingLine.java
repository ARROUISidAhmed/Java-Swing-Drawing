/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.ui.drawingshapeimpl;

import fr.ups.m2ihm.drawingtools.drawingmodel.Line;
import fr.ups.m2ihm.drawingtools.ui.DrawingShape;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 *
 * @author David
 */
public class DrawingLine extends DrawingShape {

    /**
     * Build one line from a line date and some graphical attributes.
     *
     * @param aLine a line data.
     * @param aColor the expected color of the drawing.
     * @param aStroke the expected stroke of the drawing.
     */
    public DrawingLine(
            final Line aLine,
            final Color aColor,
            final Stroke aStroke) {
        super(aLine, aColor, aStroke);
    }

    @Override
    public final void paint(final Graphics2D g) {
        final Color oldColor = g.getColor();
        final Stroke oldStroke = g.getStroke();
        g.setColor(getColorAttribute());
        g.setStroke(getStrokeAttribute());
        final Line line = (Line) getShape();
        final int x0 = line.getP1().x;
        final int y0 = line.getP1().y;
        final int x1 = line.getP2().x;
        final int y1 = line.getP2().y;
        g.drawLine(x0, y0, x1, y1);
        g.setColor(oldColor);
        g.setStroke(oldStroke);
    }

}
