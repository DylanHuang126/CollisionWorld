package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.interactstrategy.IInteractStrategy;
import edu.rice.comp504.model.interactstrategy.InteractStrategyFac;
import edu.rice.comp504.model.strategy.*;
import edu.rice.comp504.util.RandUtil;

import java.awt.*;
import java.util.Random;
import java.util.Set;

public class BallFac implements IObjFac{
    private static BallStrategyFac ballStrategyFac;
    private static InteractStrategyFac interactStrategyFac;
    private String[] availColors = {"red", "blue", "yellow", "green", "black", "purple", "orange", "gray", "brown"};
    private String[] innerShapes = {"circle", "triangle", "square", "pentagon"};
    private Random rand = new Random();

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

        final int radBase = 15;
        final int radLimit = 25;
        int radius = RandUtil.getRnd(radBase, radLimit);

        Point dims = BallWorldStore.getCanvasDims();
        final int locBase = 0;
        final int xDimLimit = dims.x;
        final int yDimLimit = dims.y;
        Point loc = new Point(RandUtil.getRnd(locBase + radius, xDimLimit - 2 * radius), RandUtil.getRnd(locBase + radius, yDimLimit - 2 * radius));
        IUpdateStrategy behavStrat = getOnlyBallStrategyFac().make(behavior);
        IInteractStrategy interStrat = getOnlyInteractStrategyFac().make(interaction);


        String color = availColors[rand.nextInt(availColors.length)];
        String innerShape = innerShapes[rand.nextInt(innerShapes.length)];

        Set<String> sizeChangeStrat = Set.of("zoom in", "zoom out");
        if (sizeChangeStrat.contains(behavior)) {
            return new SizeChangeableBall(loc, radius, vel, behavStrat, interStrat, color);
        } else if (interaction.equals("concentric")) {
            return new ConcentricBall(loc, radius, vel, behavStrat, interStrat, color, innerShape);
        } else {
            return new Ball(loc, radius, vel, behavStrat, interStrat, color, "ball");
        }

    }
}
