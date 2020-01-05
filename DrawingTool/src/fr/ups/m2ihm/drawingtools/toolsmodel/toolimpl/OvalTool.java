/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.toolsmodel.toolimpl;

import fr.ups.m2ihm.drawingtools.drawingmodel.DrawingModel;
import fr.ups.m2ihm.drawingtools.drawingmodel.Oval;
import fr.ups.m2ihm.drawingtools.drawingmodel.Shape;

/**
 *
 * @author David
 */
public class OvalTool extends LineTool {

    /**
     * Builds a OvalTool with a drawing model.
     * <p>
     * Initializes the listener lists.
     * <p>
     * Put the object in its initial state.
     *
     * @param aModel the model controlled by this drawing tool.
     */
    public OvalTool(final DrawingModel aModel) {
        super(aModel);
    }

    @Override
    protected Shape getShape() {
        return new Oval(getFirstPoint(), getSecondPoint());
    }

}
