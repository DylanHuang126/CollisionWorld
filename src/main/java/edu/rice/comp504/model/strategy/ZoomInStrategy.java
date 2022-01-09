package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.SizeChangeableBall;

import java.awt.*;

public class ZoomInStrategy implements IUpdateStrategy{
    private static IUpdateStrategy Only;

    /**
     * Constructor.
     */
    private ZoomInStrategy() {}

    /**
     * Get the singleton instance of the class.
     * @return the singleton instance
     */
    public static IUpdateStrategy make() {
        if (Only == null) {
            Only = new ZoomInStrategy();
        }
        return Only;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    @Override
    public String getName() {
        return "zoom in";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    @Override
    public void updateState(APaintObject context) {
        if (context.getType().equals("size changeable ball")) {
            SizeChangeableBall ball = (SizeChangeableBall) context;
            double [] range = ball.getScaleRange();
            double scale = ball.getScale();
            final double param = 1.1;
            double updatedRadius = ball.getRadius() * param;
            Point loc = ball.getLocation();
            Point dims = BallWorldStore.getCanvasDims();

            boolean inBoundary = loc.x - updatedRadius >= 0 && loc.x + updatedRadius <= dims.x && loc.y - updatedRadius >= 0 && loc.y + updatedRadius <= dims.y;
            if (range[0] < scale && scale < range[1] && inBoundary) {
                ball.setScale(scale * param);
                ball.setRadius(ball.getRadius() * param);
            }
        }
    }
}
