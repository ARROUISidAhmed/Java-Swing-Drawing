/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.drawingmodel.visitor;

import fr.ups.m2ihm.drawingtools.drawingmodel.Line;
import fr.ups.m2ihm.drawingtools.drawingmodel.Oval;

/**
 *
 * @author David
 */
public interface ShapeVisitor {

    /**
     * Provides the visitor to the shape. The then calls back the visitor.
     *
     * @param line the line to visit.
     */
    void visit(final Line line);

    /**
     * Provides the visitor to the shape. The then calls back the visitor.
     *
     * @param oval the oval to visit.
     */
    void visit(final Oval oval);
}
