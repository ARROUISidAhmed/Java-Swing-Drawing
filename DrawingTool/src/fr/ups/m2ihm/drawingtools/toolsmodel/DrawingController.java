/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.toolsmodel;

/**
 *
 * @author David
 */
public interface DrawingController {

    /**
     * Callback method to listen to enabling changes.
     *
     * @param event the event.
     * @param theEnabling the new enabling.
     */
    void eventEnablingChanged(DrawingEvent event, Boolean theEnabling);

}
