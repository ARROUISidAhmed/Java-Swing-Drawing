/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.toolsmodel;

import fr.ups.m2ihm.drawingtools.drawingmodel.Shape;

/**
 *
 * @author David
 */
public interface DrawingToolView {

    /**
     * Triggered when a ghost is created.
     *
     * @param shape the shape coordinates.
     */
    void ghostCreated(Shape shape);

    /**
     * Triggered when a ghost is updated.
     *
     * @param shape the shape coordinates.
     */
    void ghostChanged(Shape shape);

    /**
     * Triggered when a ghost must be removed from the drawing scene.
     *
     * @param shape the shape coordinates.
     */
    void ghostRemoved(Shape shape);
}
