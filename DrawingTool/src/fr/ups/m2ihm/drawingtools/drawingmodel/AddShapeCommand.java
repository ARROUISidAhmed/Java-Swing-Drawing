/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.drawingmodel;

/**
 *
 * @author niko3
 */
public class AddShapeCommand implements Command {

    private DrawingModel model;
    private Shape shape;

    public AddShapeCommand(DrawingModel aThis, Shape shape) {
        this.model = aThis;
        this.shape = shape;
    }

    @Override
    public void execute() {
        model.reallyAddShape(shape);
    }

    @Override
    public void undo() {
        model.reallyRemoveShape(shape);
    }

}
