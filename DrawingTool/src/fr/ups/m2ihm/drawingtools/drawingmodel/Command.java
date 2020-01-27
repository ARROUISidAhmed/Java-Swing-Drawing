package fr.ups.m2ihm.drawingtools.drawingmodel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arroui Sid Ahmed
 */
public interface Command {
    
    /**
     * Execute the command.
     */
    void execute();
    
    
    /**
     * Undo the command.
     */
    void undo();
}
