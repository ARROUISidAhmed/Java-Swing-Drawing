/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.drawingmodel;

/**
 *
 * @author arrouisa
 */
public interface ShapeCommand extends Command {

    /**
     * @return the shape related to the command.
     */
    public Shape getShape();
}
