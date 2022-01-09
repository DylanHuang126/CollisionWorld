package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;

public class StaticStrategy implements IUpdateStrategy{
    private static IUpdateStrategy Only;

    /**
     * Constructor.
     */
    private StaticStrategy() {}

    /**
     * Get the singleton instance of the class.
     * @return the singleton instance
     */
    public static IUpdateStrategy make() {
        if (Only == null) {
            Only = new StaticStrategy();
        }
        return Only;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "static";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {
        // TODO: update the ball
    }
}
