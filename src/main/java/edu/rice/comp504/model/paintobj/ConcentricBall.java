package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.interactstrategy.IInteractStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;

public class ConcentricBall extends Ball{
    private final double childRadius;
    private final String innerShape;
    private boolean isConcentric;
    /**
     * Constructor.
     *
     * @param loc              The location of the ball on the canvas
     * @param radius           The ball radius
     * @param vel              The ball velocity
     * @param behaviorStrategy The ball behavior strategy
     * @param interactStrategy The ball interact strategy
     * @param color            The ball color
     * @param innerShape       The inner shape of the ball
     */
    protected ConcentricBall(Point loc, double radius, Point vel, IUpdateStrategy behaviorStrategy, IInteractStrategy interactStrategy, String color, String innerShape) {
        super(loc, radius, vel, behaviorStrategy, interactStrategy, color, "concentric ball");
        this.childRadius = radius * 0.5;
        this.innerShape = innerShape;
        this.isConcentric = false;
    }

    public boolean isConcentric() {
        return isConcentric;
    }

    public void setConcentric(boolean shown) {
        isConcentric = shown;
    }
}
