package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paintobj.APaintObject;

public class TwinklingRotateStrategy implements IUpdateStrategy{
    private static IUpdateStrategy Only;
    private IUpdateStrategy[] children;

    /**
     * Constructor.
     */
    private TwinklingRotateStrategy(IUpdateStrategy[] children) {
        this.children = children;
    }

    /**
     * Get the singleton composite strategy.
     * @return The singleton composite strategy
     */
    public static IUpdateStrategy make() {
        if (Only == null) {
            IUpdateStrategy[] children = new IUpdateStrategy[2];
            children[0] = TwinklingStrategy.make();
            children[1] = RotateStrategy.make();
            Only = new TwinklingRotateStrategy(children);
        }
        return Only;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    public String getName() {
        return "twinkling rotate";
    }

    /**
     * Update the ball state in the ball world.
     * @param context The ball to update
     */
    public void updateState(APaintObject context) {
        for (IUpdateStrategy s: children) {
            s.updateState(context);
        }
    }
}
