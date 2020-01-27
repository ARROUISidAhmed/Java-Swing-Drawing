/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.drawingmodel;

/**
 *
 * @author Arroui Sid Ahmed
 */
public class AddShapeCommand implements ShapeCommand {

    private final DrawingModel drawingModel;
    private final Shape shape;

    public AddShapeCommand(DrawingModel drawingModel, Shape shape) {
        this.drawingModel = drawingModel;
        this.shape = shape;
    }

    @Override
    public void execute() {
        drawingModel.reallyAddShape(shape);
    }

    @Override
    public void undo() {
        drawingModel.reallyRemoveShape(shape);
    }

    @Override
    public Shape getShape() {
        return shape;
    }

}
