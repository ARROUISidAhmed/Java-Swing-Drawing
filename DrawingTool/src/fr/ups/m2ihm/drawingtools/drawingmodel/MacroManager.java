/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.drawingmodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 *
 * @author arrouisa
 */
public class MacroManager extends AbstractListModel {

    private List<MacroCommand> macros;

    private List<ListDataListener> listeners;

    public MacroManager(List<MacroCommand> macros, List<ListDataListener> listeners) {
        this.macros = macros;
        this.listeners = listeners;
    }

    public MacroManager() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    public void registerMacro(MacroCommand macro) {
        macros.add(macro);
        int index = macros.indexOf(macro);
        ListDataEvent e = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, index, index);
        listeners.forEach(l -> l.intervalAdded(e));
    }

    public void unregisterMacro(MacroCommand macro) {
        int index = macros.indexOf(macro);
        macros.remove(macro);
        ListDataEvent e = new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, index, index);
        listeners.forEach(l -> l.intervalRemoved(e));
    }

    @Override
    public int getSize() {
        return macros.size();
    }

    @Override
    public MacroCommand getElementAt(int index) {
        return macros.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
    }

}
