/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.drawingmodel;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arrouisa
 */
public class MacroCommand extends LocalizedCommand {
    
    private List<Command> commands;
    private String name;
    
    public MacroCommand(String name, List<Command> commands) {
        this.name = name;
        this.commands = new ArrayList<>(commands);
    }
    
    public MacroCommand() {
        this("Macro", new ArrayList<Command>());
    }
    
    @Override
    public void execute() {
        commands.forEach((command) -> {
            command.execute();
        });
        
    }
    
    @Override
    public void undo() {
        commands.forEach(command -> command.undo());
    }
    
    @Override
    public void setAnchorPoint(Point anchorPoint) {
        super.setAnchorPoint(anchorPoint);
    }
    
    @Override
    public String toString() {
        return name;
    }
    
}
