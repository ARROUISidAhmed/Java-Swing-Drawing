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
public enum DrawingEvent {
    /**
     * Identity an event that represents the beginning of a drawing action.
     */
    BEGIN_DRAW,
    /**
     * Identity an event that represents a drawing action.
     */
    DRAW,
    /**
     * Identity an event that represents the end of a drawing action.
     */
    END_DRAW,
    /**
     * Identity an event that represents the cancellation of a drawing action.
     */
    CANCEL_DRAW
}
