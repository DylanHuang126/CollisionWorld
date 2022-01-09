package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.interactstrategy.IInteractStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;

public class Fish extends Ball{
    private int direction;
    /**
     * Constructor.
     *
     * @param loc      The location of the paintable object on the canvas
     * @param vel      The object velocity
     * @param behaviorStrategy The object behavior strategy
     * @param interactStrategy The object interact strategy
     */
    public Fish(Point loc, int radius, Point vel, IUpdateStrategy behaviorStrategy, IInteractStrategy interactStrategy) {
        super(loc, radius, vel, behaviorStrategy, interactStrategy, "white", "fish");
        int direction = -1;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
