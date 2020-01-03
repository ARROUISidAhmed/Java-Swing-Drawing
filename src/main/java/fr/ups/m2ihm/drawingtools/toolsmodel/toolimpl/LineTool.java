/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.toolsmodel.toolimpl;

import fr.ups.m2ihm.drawingtools.drawingmodel.DrawingModel;
import fr.ups.m2ihm.drawingtools.drawingmodel.Line;
import fr.ups.m2ihm.drawingtools.drawingmodel.Shape;
import fr.ups.m2ihm.drawingtools.toolsmodel.AbstractTool;
import fr.ups.m2ihm.drawingtools.toolsmodel.DrawingEvent;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author David
 */
public class LineTool extends AbstractTool {

    @Override
    public final Boolean isEventEnabled(final DrawingEvent event) {
        if (Objects.isNull(currentState)) {
            throw new IllegalStateException();
        } else {
            return currentState.isEventEnabled(event);
        }
    }

    @Override
    protected Shape getGhost() {
        return getShape();
    }

    @Override
    protected Shape getShape() {
        return new Line(firstPoint, secondPoint);
    }

    @Override
    public final void goToInitialState() {
        goToState(PossibleState.IDLE, null, null);
    }

    /**
     * An enumeration that represents the drawing inner states.
     * <p>
     * For each state, the activation data are stored within the enum value.</p>
     */
    private enum PossibleState {
        /**
         * Represents the initial state, waiting for the drawing to start.
         * <p>
         * The BEGIN_DRAW event must be enabled while the others are
         * disabled.</p>
         */
        IDLE(true, false, false, false),
        /**
         * Represent the state where a first point is defined.
         * <p>
         * The BEGIN_DRAW event must be disabled while the others are
         * enabled.</p>
         */
        FIRST_POINT(false, true, true, true),
        /**
         * Represent the state where a first point is defined.
         * <p>
         * The BEGIN_DRAW event must be disabled while the others are
         * enabled.</p>
         */
        LINE(false, true, true, true);

        /**
         * Defines the availability of each event.
         */
        private final Map<DrawingEvent, Boolean> eventEnablings;

        /**
         * Build an enum value associating it the enabling data.
         *
         * @param theBeginDrawEnabling ...
         * @param theEndDrawEnabling ...
         * @param theDrawEnabling ...
         * @param theCancelDrawEnabling ...
         */
        PossibleState(
                final boolean theBeginDrawEnabling,
                final boolean theEndDrawEnabling,
                final boolean theDrawEnabling,
                final boolean theCancelDrawEnabling) {
            this.eventEnablings = new HashMap<>(DrawingEvent.values().length);

            this.eventEnablings.put(DrawingEvent.BEGIN_DRAW,
                    theBeginDrawEnabling);
            this.eventEnablings.put(DrawingEvent.END_DRAW,
                    theEndDrawEnabling);
            this.eventEnablings.put(DrawingEvent.DRAW,
                    theDrawEnabling);
            this.eventEnablings.put(DrawingEvent.CANCEL_DRAW,
                    theCancelDrawEnabling);
        }

        /**
         * Provides read access to the enabling of any event.
         *
         * @param event the event for which he enabling is asked.
         * @return the enabling of this event.
         */
        public boolean isEventEnabled(final DrawingEvent event) {
            assert (Objects.nonNull(event));
            return this.eventEnablings.get(event);
        }
    }

    /**
     * A state variable that stores the current state of the interaction
     * technique.
     */
    private PossibleState currentState;
    /**
     * A state variable that stores the first point of the drawing.
     */
    private Point firstPoint;
    /**
     * A state variable that stores the last point of the drawing.
     */
    private Point secondPoint;
    /**
     * Represents the minimum distance between the two points to create a line.
     */
    private static final int MINIMUM_LINE_SIZE = 10;

    /**
     * Switch to the next state, enabling the correct events and setting up the
     * state variables.
     *
     * @param state the new state
     * @param theFirstPoint the new value for the first point (possibly null)
     * @param theSecondPoint the new value for the last point (possibly null)
     */
    private void goToState(
            final PossibleState state,
            final Point theFirstPoint,
            final Point theSecondPoint) {
        currentState = state;
        firstPoint = theFirstPoint;
        secondPoint = theSecondPoint;
        for (DrawingEvent event : DrawingEvent.values()) {
            fireEventEnablingChanged(
                    event,
                    state.isEventEnabled(event));
        }
    }

    /**
     * Provides the first point of the Line.
     *
     * @return the first point.
     */
    public final Point getFirstPoint() {
        return firstPoint;
    }

    /**
     * Provides the last point of the Line.
     *
     * @return the second point.
     */
    public final Point getSecondPoint() {
        return secondPoint;
    }

    /**
     * Builds a LineTool with a drawing model.
     * <p>
     * Initializes the listener lists.
     * <p>
     * Put the object in its initial state.
     *
     * @param aModel the model controlled by this drawing tool.
     */
    public LineTool(final DrawingModel aModel) {
        super(aModel);

        goToInitialState();
    }

    @Override
    public final void acceptEvent(
            final DrawingEvent theEvent,
            final Point aPoint) {
        switch (theEvent) {
            case BEGIN_DRAW:
                beginDraw(aPoint);
                break;
            case DRAW:
                draw(aPoint);
                break;
            case END_DRAW:
                endDraw();
                break;
            case CANCEL_DRAW:
                cancelDraw();
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Handles the BEGIN_DRAW event.
     *
     * @param point the first point of the drawing.
     */
    private void beginDraw(final Point point) {
        switch (currentState) {
            case IDLE:
                goToState(PossibleState.FIRST_POINT, point, null);
                break;
            case FIRST_POINT:
                throw new IllegalStateException();
            case LINE:
                throw new IllegalStateException();
            default:
                throw new IllegalStateException();
        }
    }

    /**
     * Handles the DRAW event.
     *
     * @param point the current point of the drawing.
     */
    private void draw(final Point point) {
        switch (currentState) {
            case IDLE:
                throw new IllegalStateException();
            case FIRST_POINT:
                if (firstPoint.distance(point) < MINIMUM_LINE_SIZE) {
                    goToState(PossibleState.FIRST_POINT, firstPoint, null);
                } else {
                    goToState(PossibleState.LINE, firstPoint, point);
                    fireGhostAdded(getGhost());
                }
                break;
            case LINE:
                if (firstPoint.distance(point) < MINIMUM_LINE_SIZE) {
                    fireGhostRemoved(getGhost());
                    goToState(PossibleState.FIRST_POINT, firstPoint, null);
                } else {
                    goToState(PossibleState.LINE, firstPoint, point);
                    fireGhostChanged(getGhost());
                }
                break;
            default:
                throw new IllegalStateException();
        }
    }

    /**
     * Handles the END_DRAW event.
     */
    private void endDraw() {

        switch (currentState) {
            case IDLE:
                throw new IllegalStateException();
            case FIRST_POINT:
                goToState(PossibleState.IDLE, null, null);
                break;
            case LINE:
                fireGhostRemoved(getGhost());
                getModel().addShape(getShape());
                goToState(PossibleState.IDLE, null, null);
                break;
            default:
                throw new IllegalStateException();
        }
    }

    /**
     * Handles the CANCEL_DRAW event.
     */
    private void cancelDraw() {
        switch (currentState) {
            case IDLE:
                throw new IllegalStateException();
            case FIRST_POINT:
                goToState(PossibleState.IDLE, null, null);
                break;
            case LINE:
                fireGhostRemoved(getGhost());
                goToState(PossibleState.IDLE, null, null);
                break;
            default:
                throw new IllegalStateException();
        }
    }

}
