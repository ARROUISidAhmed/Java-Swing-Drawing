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

    private final DrawingModel drawingModel;
    private final Shape shape;

    public RemoveShapeCommand(DrawingModel drawingModel, Shape shape) {
        this.drawingModel = drawingModel;
        this.shape = shape;
    }

    @Override
    public void execute() {
        drawingModel.reallyRemoveShape(shape);
    }

    @Override
    public void undo() {
        drawingModel.reallyAddShape(shape);
    }

}
