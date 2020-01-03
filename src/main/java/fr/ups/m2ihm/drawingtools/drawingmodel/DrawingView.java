/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.drawingmodel;

/**
 *
 * @author David
 * @see DrawingModel
 */
public interface DrawingView {

    /**
     * Called each time the related model changes.
     *
     * @param model Ze model.
     */
    void modelChanged(DrawingModel model);
}
