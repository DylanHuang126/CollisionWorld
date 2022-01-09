package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.interactstrategy.IInteractStrategy;
import edu.rice.comp504.model.interactstrategy.InteractStrategyFac;
import edu.rice.comp504.model.strategy.BallStrategyFac;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.util.RandUtil;

import java.awt.*;

public class FishFac implements IObjFac{
    private static BallStrategyFac ballStrategyFac;
    private static InteractStrategyFac interactStrategyFac;

    /**
     * Get the singleton Ball strategy factory.
     * @return The Ball strategy factory
     */
    public static BallStrategyFac getOnlyBallStrategyFac() {
        if (ballStrategyFac == null) {
            ballStrategyFac = new BallStrategyFac();
        }
        return ballStrategyFac;
    }

    /**
     * Get the singleton Ball interaction strategy factory.
     * @return The Ball interaction strategy factory
     */
    public static InteractStrategyFac getOnlyInteractStrategyFac() {
        if (interactStrategyFac == null) {
            interactStrategyFac = new InteractStrategyFac();
        }
        return interactStrategyFac;
    }

    @Override
    public APaintObject make(String behavior, String interaction) {
        final int base = 10;
        final int limit = 15;
        Point vel = new Point(RandUtil.getRnd(base, limit), RandUtil.getRnd(base, limit));

        Point dims = BallWorldStore.getCanvasDims();
        final int locBase = 0;
        final int radius = RandUtil.getRnd(30, 50);

        Point loc = new Point(RandUtil.getRnd(locBase, dims.x - radius), RandUtil.getRnd(locBase, dims.y - radius));
        IUpdateStrategy behavStrat = getOnlyBallStrategyFac().make(behavior);
        IInteractStrategy interStrat = getOnlyInteractStrategyFac().make(interaction);
        return new Fish(loc, radius, vel, behavStrat, interStrat);
    }
}
