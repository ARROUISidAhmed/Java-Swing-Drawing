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
class RemoveShapeCommand implements Command {

    private DrawingModel model;
    private Shape shape;

    public RemoveShapeCommand(DrawingModel aThis, Shape shape) {
        this.model = aThis;
        this.shape = shape;
    }

    @Override
    public void execute() {
        model.reallyRemoveShape(shape);
    }

    @Override
    public void undo() {
        model.reallyAddShape(shape);
    }

}
