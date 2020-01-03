/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.toolsmodel;

import fr.ups.m2ihm.drawingtools.drawingmodel.DrawingModel;
import fr.ups.m2ihm.drawingtools.drawingmodel.Shape;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David
 */
public abstract class AbstractTool {

    /**
     * Used for memory allocation.
     */
    protected static final int DEFAULT_LISTENER_LISTE_SIZE = 10;
    /**
     * The model controlled by this drawing tool.
     */
    private final DrawingModel model;
    /**
     * List of views.
     */
    private final List<DrawingToolView> drawingToolViews;
    /**
     * List of controllers that are interested in state changes.
     */
    private final List<DrawingController> drawingControllers;

    /**
     * Builds a Tool with a drawing model.
     * <p>
     * Initializes the listener lists.
     * <p>
     * Put the object in its initial state.
     *
     * @param aModel the model controlled by this drawing tool.
     */
    public AbstractTool(final DrawingModel aModel) {
        this.model = aModel;
        this.drawingControllers = new ArrayList<>(DEFAULT_LISTENER_LISTE_SIZE);
        this.drawingToolViews = new ArrayList<>(DEFAULT_LISTENER_LISTE_SIZE);
    }

    /**
     * Return the expected enabling of an event for the current state.
     *
     * @param event the event for which the enabling is asked.
     * @return the enabling of this event.
     */
    public abstract Boolean isEventEnabled(final DrawingEvent event);

    /**
     * Forces the tool to go back to its initial state.
     */
    public abstract void goToInitialState();

    /**
     * Triggers an event to notify a Ghost was created.
     *
     * @param ghost the shape used as a ghost.
     */
    protected final void fireGhostAdded(final Shape ghost) {
        drawingToolViews.forEach((drawingToolView) -> {
            drawingToolView.ghostCreated(ghost);
        });
    }

    /**
     * Triggers an event to notify the Ghost has changed.
     *
     * @param ghost the shape used as a ghost.
     */
    protected final void fireGhostChanged(final Shape ghost) {
        drawingToolViews.forEach((drawingToolView) -> {
            drawingToolView.ghostChanged(ghost);
        });
    }

    /**
     * Triggers an event to notify the Ghost no longer exists.
     *
     * @param ghost the shape used as a ghost.
     */
    protected final void fireGhostRemoved(final Shape ghost) {
        drawingToolViews.forEach((drawingToolView) -> {
            drawingToolView.ghostRemoved(ghost);
        });
    }

    /**
     * Triggers an event to notify an event activation has changed.
     *
     * @param event the related event
     * @param theEnabling the new enabling state
     */
    protected final void fireEventEnablingChanged(
            final DrawingEvent event,
            final Boolean theEnabling) {
        drawingControllers.forEach((drawingController) -> {
            drawingController.eventEnablingChanged(event, theEnabling);
        });
    }

    /**
     * Provides the model controlled by this drawing tool.
     *
     * @return the model controlled by this drawing tool.
     */
    public final DrawingModel getModel() {
        return model;
    }

    /**
     * Entry point for event handling.
     *
     * @param theEvent the event
     * @param aPoint a point (possibly null) related to the event
     */
    public abstract void acceptEvent(
            final DrawingEvent theEvent,
            final Point aPoint);

    /**
     * Add a controller to the list of controllers.
     *
     * @param controller the controller to be added.
     */
    public void addDrawingController(final DrawingController controller) {
        drawingControllers.add(controller);
    }

    /**
     * Remove a controller from the list of controllers.
     *
     * @param controller the controller to be removed.
     */
    public void removeDrawingController(
            final DrawingController controller) {
        drawingControllers.remove(controller);
    }

    /**
     * Add a view to the list of views.
     *
     * @param view the view to be added.
     */
    public void addDrawingToolView(final DrawingToolView view) {
        drawingToolViews.add(view);
    }

    /**
     * Remove a view to the list of views.
     *
     * @param view the view to be removed.
     */
    public void removeDrawingToolView(final DrawingToolView view) {
        drawingToolViews.remove(view);
    }

    /**
     * Provides a ghost shape used to show a temporary drawing.
     *
     * @return the ghost shape
     */
    protected abstract Shape getGhost();

    /**
     * Provides the final shape, can be the same shape as the ghost.
     *
     * @return the shape
     */
    protected abstract Shape getShape();
}
