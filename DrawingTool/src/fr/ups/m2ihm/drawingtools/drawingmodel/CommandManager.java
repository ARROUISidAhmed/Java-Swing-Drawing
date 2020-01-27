/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.drawingmodel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Stack;

/**
 *
 * @author Arroui Sid Ahmed
 */
public class CommandManager {

    private final Stack<Command> availableUndo;
    private final Stack<Command> availableRedo;

    public static final String PROP_AVAILABLEUNDO = "availableUndo";

    /**
     * Get the value of availableUndo
     *
     * @return the value of availableUndo
     */
    public Stack<Command> getAvailableUndo() {
        return availableUndo;
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /**
     * Add PropertyChangeListener.
     *
     * @param propertyName
     * @param listener
     */
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param propertyName
     * @param listener
     */
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

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
        int oldSize = availableUndo.size();
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

        propertyChangeSupport.firePropertyChange(PROP_AVAILABLEUNDO, oldSize, availableUndo.size());

    }

    public void undoRange(int lowerIndex, int upperIndex) {
        int oldSize = availableUndo.size();
        switch (state) {
            case Idle:
                //interdit
                break;

            case UndoOnly:

                if (availableUndo.size() == (upperIndex - lowerIndex + 1)) {
                    state = State.RedoOnly;
                    for (int i = lowerIndex; i <= upperIndex; i++) {
                        Command c = availableUndo.remove(lowerIndex);
                        c.undo();
                        availableRedo.push(c);
                    }

                } else if (availableUndo.size() > (upperIndex - lowerIndex + 1)) {

                    state = State.UndoRedoable;
                    for (int i = lowerIndex; i <= upperIndex; i++) {
                        Command c = availableUndo.remove(lowerIndex);
                        c.undo();
                        availableRedo.push(c);
                    }
                }
                break;

            case RedoOnly:
                //interdit
                break;

            case UndoRedoable:
                if (availableUndo.size() == (upperIndex - lowerIndex + 1)) {
                    state = State.RedoOnly;
                    for (int i = lowerIndex; i <= upperIndex; i++) {
                        Command c = availableUndo.remove(lowerIndex);
                        c.undo();
                        availableRedo.push(c);
                    }
                } else if (availableUndo.size() > (upperIndex - lowerIndex + 1)) {
                    state = State.UndoRedoable;
                    for (int i = lowerIndex; i <= upperIndex; i++) {
                        Command c = availableUndo.remove(lowerIndex);
                        c.undo();
                        availableRedo.push(c);
                    }

                }

                break;
        }
        propertyChangeSupport.firePropertyChange(PROP_AVAILABLEUNDO, oldSize, availableUndo.size());
    }

    public void undo() {
        int oldSize = availableUndo.size();
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
        }

        propertyChangeSupport.firePropertyChange(PROP_AVAILABLEUNDO, oldSize, availableUndo.size());
    }

    public void redo() {
        int oldSize = availableUndo.size();
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
                    Command command = availableRedo.pop();
                    command.execute();
                    availableUndo.push(command);
                }
                if (availableRedo.size() > 1) {
                    state = State.UndoRedoable;
                    Command command = availableRedo.pop();
                    command.execute();
                    availableUndo.push(command);
                }

                break;
        }

        propertyChangeSupport.firePropertyChange(PROP_AVAILABLEUNDO, oldSize, availableUndo.size());
    }

}
