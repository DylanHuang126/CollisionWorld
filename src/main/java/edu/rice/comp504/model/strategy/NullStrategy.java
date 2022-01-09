package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;

public class NullStrategy implements IUpdateStrategy {
    private static IUpdateStrategy Only;

    /**
     * Constructor.
     */
    private NullStrategy() {}

    /**
     * Get the singleton instance of the class.
     * @return the singleton instance
     */
    public static IUpdateStrategy make() {
        if (Only == null) {
            Only = new NullStrategy();
        }
        return Only;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "null";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {

    }

}
