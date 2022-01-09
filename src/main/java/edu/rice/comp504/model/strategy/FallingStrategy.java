package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;

import java.awt.*;

public class FallingStrategy implements IUpdateStrategy{
    private static Point zero = new Point(0,0);
    private static IUpdateStrategy Only;

    /**
     * Constructor.
     */
    private FallingStrategy() {}

    /**
     * Get the singleton instance of the class.
     * @return the singleton instance
     */
    public static IUpdateStrategy make() {
        if (Only == null) {
            Only = new FallingStrategy();
        }
        return Only;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    @Override
    public String getName() {
        return "falling";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    @Override
    public void updateState(APaintObject context) {
        Point vel = context.getVelocity();
        Point loc = context.getLocation();
        Point dims = BallWorldStore.getCanvasDims();
        if (loc.getY() + context.getRadius() + vel.getY() > dims.getY()) {
            context.setLocation(new Point((int) loc.getX(), (int) (dims.getY() - context.getRadius())));
            context.setVelocity(zero);
        } else {
            if (vel.getY() < 0) {
                context.setVelocity(new Point(vel.x, -1 * vel.y));
            }
            context.nextLocation(0, (int)vel.getY());
        }
    }
}
