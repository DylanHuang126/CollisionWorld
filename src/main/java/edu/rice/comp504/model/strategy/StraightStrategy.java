package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.awt.*;

/**
 * Horizontal strategy moves the line to the right only in the x direction.
 */
public class StraightStrategy implements IUpdateStrategy {
    private static IUpdateStrategy Only;

    /**
     * Constructor.
     */
    private StraightStrategy() {}

    /**
     * Get the singleton instance of the class.
     * @return the singleton instance
     */
    public static IUpdateStrategy make() {
        if (Only == null) {
            Only = new StraightStrategy();
        }
        return Only;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "straight";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {
        // TODO: update the ball
        context.detectCollisionBoundary();
        Point vel = context.getVelocity();
        context.nextLocation((int)vel.getX(), (int)vel.getY());
    }

}
