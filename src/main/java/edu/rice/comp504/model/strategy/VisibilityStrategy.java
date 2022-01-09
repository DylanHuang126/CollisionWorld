package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;

import java.util.Set;

public class VisibilityStrategy implements IUpdateStrategy{
    private static IUpdateStrategy Only;

    /**
     * Constructor.
     */
    private VisibilityStrategy() {}

    /**
     * Get the singleton instance of the class.
     * @return the singleton instance
     */
    public static IUpdateStrategy make() {
        if (Only == null) {
            Only = new VisibilityStrategy();
        }
        return Only;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    @Override
    public String getName() {
        return "visibility";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    @Override
    public void updateState(APaintObject context) {
        Set<String> balls = Set.of("ball", "concentric ball", "size changeable ball");
        if (balls.contains(context.getType())) {
            Ball b = (Ball) context;
            b.setCloaking(!b.isCloaking());
        }
    }
}
