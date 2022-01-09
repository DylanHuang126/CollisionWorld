package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;

import java.awt.*;

public class RotateStrategy implements IUpdateStrategy {
    private static IUpdateStrategy Only;

    /**
     * Constructor.
     */
    private RotateStrategy() {}

    /**
     * Get the singleton instance of the class.
     * @return the singleton instance
     */
    public static IUpdateStrategy make() {
        if (Only == null) {
            Only = new RotateStrategy();
        }
        return Only;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "rotate";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {
        // TODO: update the moving line
        context.detectCollisionBoundary();
        Point vel = context.getVelocity();
        double theta = context.getTheta() + Math.PI / 5;
        context.setTheta(theta);
        double vx = vel.getX() * Math.cos(theta) - vel.getY() * Math.sin(theta);
        double vy = vel.getY() * Math.cos(theta) + vel.getX() * Math.sin(theta);
        context.nextLocation((int)vx, (int)vy);
    }

}
