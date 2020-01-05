/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools.toolsmodel;

import fr.ups.m2ihm.drawingtools.drawingmodel.DrawingModel;
import fr.ups.m2ihm.drawingtools.drawingmodel.Shape;
import fr.ups.m2ihm.drawingtools.toolsmodel.toolimpl.LineTool;
import fr.ups.m2ihm.drawingtools.toolsmodel.toolimpl.OvalTool;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author David
 */
public class ToolManager extends AbstractTool {

    /**
     * Build a tool manager as if it was an abstract tool.
     *
     * @param aModel the related model
     */
    public ToolManager(final DrawingModel aModel) {
        super(aModel);

        tools = new HashMap<>(Tool.values().length);
        tools.put(Tool.LINE, new LineTool(aModel));
        tools.put(Tool.CIRCLE, new OvalTool(aModel));
        goToInitialState();
    }

    @Override
    public final Boolean isEventEnabled(final DrawingEvent event) {
        return getCurrentTool().isEventEnabled(event);
    }

    @Override
    public final void goToInitialState() {
        selectTool(Tool.LINE);
    }

    @Override
    public final void acceptEvent(final DrawingEvent theEvent,
            final Point aPoint) {
        getCurrentTool().acceptEvent(theEvent, aPoint);
    }

    @Override
    protected final Shape getGhost() {
        throw new UnsupportedOperationException(
                "This method is not used by class " + ToolManager.class);
    }

    @Override
    protected final Shape getShape() {
        throw new UnsupportedOperationException(
                "This method is not used by class " + ToolManager.class);
    }

    /**
     * An enumeration used to qualify the expected tool when asking for change.
     */
    public enum Tool {
        /**
         * Targeted tool is LineTool.
         */
        LINE,
        /**
         * Targeted tool is LineTool.
         */
        CIRCLE
    }

    /**
     * Maps a tool implementation to a tool enum value.
     */
    private final Map<Tool, AbstractTool> tools;

    /**
     * Defines the current tool.
     */
    private Tool currentTool;

    /**
     * Provides the tool currently used.
     *
     * @return the AbstractTool instance.
     */
    private AbstractTool getCurrentTool() {
        return tools.get(currentTool);
    }

    /**
     * Provides the tool currently used for drawing.
     *
     * @return the current tool
     */
    public Tool getCurrentToolName() {
        return currentTool;
    }

    /**
     * Forces switching between tools to select the required tool.
     *
     * @param aTool the selected tool.
     */
    public final void selectTool(final Tool aTool) {
        assert (Objects.nonNull(aTool));
        currentTool = aTool;
        getCurrentTool().goToInitialState();
    }

    @Override
    public final void removeDrawingToolView(
            final DrawingToolView view) {
        for (Tool tool : Tool.values()) {
            tools.get(tool).removeDrawingToolView(view);
        }
    }

    @Override
    public final void addDrawingToolView(
            final DrawingToolView view) {
        for (Tool tool : Tool.values()) {
            tools.get(tool).addDrawingToolView(view);
        }
    }

    @Override
    public final void removeDrawingController(
            final DrawingController controller) {
        for (Tool tool : Tool.values()) {
            tools.get(tool).removeDrawingController(controller);
        }
    }

    @Override
    public final void addDrawingController(
            final DrawingController controller) {
        for (Tool tool : Tool.values()) {
            tools.get(tool).addDrawingController(controller);
        }
    }

}
