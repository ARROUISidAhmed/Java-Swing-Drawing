/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.drawingmodel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arrouisa
 */
public class Macro implements Command {

    private List<Command> commands;

    public Macro(List<Command> commands) {
        this.commands = commands;
    }

    public Macro() {
        this(new ArrayList<Command>());
    }

    @Override
    public void execute() {
        commands.forEach(command -> command.execute());
    }

    @Override
    public void undo() {
        commands.forEach(command -> command.undo());
    }

}
