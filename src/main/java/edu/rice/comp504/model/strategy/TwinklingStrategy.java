package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;
import edu.rice.comp504.util.RandUtil;

public class TwinklingStrategy implements IUpdateStrategy {
    private static IUpdateStrategy Only;
    private String[] availColors = {"red", "blue", "yellow", "green", "black", "purple", "orange", "gray", "brown"};

    /**
     * Constructor.
     */
    private TwinklingStrategy() {}

    /**
     * Get the singleton instance of the class.
     * @return the singleton instance
     */
    public static IUpdateStrategy make() {
        if (Only == null) {
            Only = new TwinklingStrategy();
        }
        return Only;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "twinkling";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {
        // TODO: update the moving line
        context.setColor(availColors[RandUtil.getRnd(0, availColors.length - 1)]);
    }
}
