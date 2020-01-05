/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.drawingmodel;

import java.util.Stack;

/**
 *
 * @author niko3
 */
public class CommandManager {

    private final Stack<Command> availableUndo;
    private final Stack<Command> availableRedo;

    private enum State {

        Idle, UndoOnly, RedoOnly, UndoRedoable
    };

    private State state;

    public CommandManager() {
        availableUndo = new Stack();
        availableRedo = new Stack();
        state = State.Idle;

    }

    public void registerCommand(Command c) {
        switch (state) {
            case Idle:
                availableUndo.push(c);
                state = State.UndoOnly;
                break;

            case UndoOnly:
                availableUndo.push(c);
                state = State.UndoOnly;
                break;

            case RedoOnly:
                availableRedo.clear();
                availableUndo.push(c);
                state = State.UndoOnly;
                break;

            case UndoRedoable:
                availableRedo.clear();
                availableUndo.push(c);
                state = State.UndoOnly;
                break;
        }
    }

    public void undo() {
        switch (state) {
            case Idle:
                //interdit
                break;

            case UndoOnly:

                if (availableUndo.size() == 1) {
                    state = State.RedoOnly;
                    Command c = availableUndo.pop();
                    c.undo();
                    availableRedo.push(c);
                } else if (availableUndo.size() > 1) {

                    state = State.UndoRedoable;
                    Command c = availableUndo.pop();
                    c.undo();
                    availableRedo.push(c);
                }
                break;

            case RedoOnly:
                //interdit
                break;

            case UndoRedoable:
                if (availableUndo.size() == 1) {
                    state = State.RedoOnly;
                    Command command = availableUndo.pop();
                    command.undo();
                    availableRedo.push(command);
                } else if (availableUndo.size() > 1) {
                    state = State.UndoRedoable;
                    Command command = availableUndo.pop();
                    command.undo();
                    availableRedo.push(command);
                }

                break;
        }
    }

    public void redo() {
        switch (state) {
            case Idle:
                //interdit
                break;

            case UndoOnly:
                //interdit
                break;

            case RedoOnly:
                if (availableRedo.size() == 1) {
                    state = State.UndoOnly;
                    Command command = availableRedo.pop();
                    command.execute();
                    availableUndo.push(command);
                } else if (availableRedo.size() > 1) {
                    state = State.UndoRedoable;
                    Command command = availableRedo.pop();
                    command.execute();
                    availableUndo.push(command);
                }

                break;

            case UndoRedoable:
                if (availableRedo.size() == 1) {
                    state = State.UndoOnly;
                    Command command2 = availableRedo.pop();
                    command2.execute();
                    availableUndo.push(command2);
                }
                if (availableRedo.size() > 1) {
                    state = State.UndoRedoable;
                    Command command2 = availableRedo.pop();
                    command2.execute();
                    availableUndo.push(command2);
                }

                break;
        }
    }
}
