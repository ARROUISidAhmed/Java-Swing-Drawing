/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.ui.drawingshapeimpl;

import fr.ups.m2ihm.drawingtools.drawingmodel.Oval;
import fr.ups.m2ihm.drawingtools.ui.DrawingShape;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;

/**
 *
 * @author David
 */
public class DrawingOval extends DrawingShape {

    /**
     * Build one oval from a oval date and some graphical attributes.
     *
     * @param anOval an oval data.
     * @param aColor the expected color of the drawing.
     * @param aStroke the expected stroke of the drawing.
     */
    public DrawingOval(
            final Oval anOval,
            final Color aColor,
            final Stroke aStroke) {
        super(anOval, aColor, aStroke);
    }

    @Override
    public final void paint(final Graphics2D g) {
        final Color oldColor = g.getColor();
        final Stroke oldStroke = g.getStroke();
        g.setColor(getColorAttribute());
        g.setStroke(getStrokeAttribute());
        final Oval oval = (Oval) getShape();
        final Rectangle r = oval.getBoundingBox();
        g.drawOval(r.x, r.y, r.width, r.height);
        g.setColor(oldColor);
        g.setStroke(oldStroke);
    }

}
