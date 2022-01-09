package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.SizeChangeableBall;

public class ZoomOutStrategy implements IUpdateStrategy{
    private static IUpdateStrategy Only;

    /**
     * Constructor.
     */
    private ZoomOutStrategy() {}

    /**
     * Get the singleton instance of the class.
     * @return the singleton instance
     */
    public static IUpdateStrategy make() {
        if (Only == null) {
            Only = new ZoomOutStrategy();
        }
        return Only;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    @Override
    public String getName() {
        return "zoom out";
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
            final double param = 0.9;
            if (range[0] < scale && scale < range[1]) {
                ball.setScale(scale * param);
                ball.setRadius(ball.getRadius() * param);
            }
        }
    }
}
