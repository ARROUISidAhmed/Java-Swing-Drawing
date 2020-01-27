/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.toolsmodel.toolimpl;

import fr.ups.m2ihm.drawingtools.drawingmodel.DrawingModel;
import fr.ups.m2ihm.drawingtools.drawingmodel.MacroShape;
import fr.ups.m2ihm.drawingtools.drawingmodel.Shape;
import java.util.List;

/**
 *
 * @author arrouisa
 */
public class MacroTool extends LineTool {

    private List<Shape> shapes;

    /**
     * Builds a MacroTool with a drawing model.
     * <p>
     * Initializes the listener lists.
     * <p>
     * Put the object in its initial state.
     *
     * @param aModel the model controlled by this drawing tool.
     * @param shapes
     */
    public MacroTool(final DrawingModel aModel, final List<Shape> shapes) {
        super(aModel);
        this.shapes = shapes;
    }

    @Override
    protected Shape getShape() {
        return new MacroShape(getFirstPoint(), getSecondPoint(), shapes);
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

}
