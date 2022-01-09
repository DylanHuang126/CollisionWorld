package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.interactstrategy.IInteractStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;

public class SizeChangeableBall extends Ball{
    private double[] scaleRange;
    private double scale;
    /**
     * Constructor.
     *
     * @param loc      The location of the ball on the canvas
     * @param radius   The ball radius
     * @param vel      The ball velocity
     * @param behavior The ball strategy
     * @param interaction   The ball interaction
     * @param color         The ball color
     */
    protected SizeChangeableBall(Point loc, int radius, Point vel, IUpdateStrategy behavior, IInteractStrategy interaction, String color) {
        super(loc, radius, vel, behavior, interaction, color, "size changeable ball");
        this.scaleRange = new double[]{0.2, 2};
        this.scale = 1;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public double getScale() {
        return scale;
    }

    public double[] getScaleRange() {
        return scaleRange;
    }
}
