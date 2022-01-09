package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.interactstrategy.IInteractStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.beans.PropertyChangeListener;

/**
 * A object that the view can draw on the user's canvas.
 */
public abstract class APaintObject implements PropertyChangeListener {
    Point loc;
    Point vel;
    IUpdateStrategy behaviorStrategy;
    IInteractStrategy interactStrategy;
    String type;
    String color;
    double theta;
    double radius;

    /**
     * Constructor.
     * @param loc The location of the paintable object on the canvas
     * @param vel The object velocity
     * @param type The object type
     * @param behaviorStrategy The object update strategy
     * @param interactStrategy The object interaction strategy
     * @param color The object color
     * @param radius The object radius
     */
    public APaintObject(Point loc, Point vel, String type, IUpdateStrategy behaviorStrategy, IInteractStrategy interactStrategy, String color, double radius) {
        this.loc = loc;
        this.vel = vel;
        this.behaviorStrategy = behaviorStrategy;
        this.interactStrategy = interactStrategy;
        this.type = type;
        this.color = color;
        this.theta = 0;
        this.radius = radius;
    }

    /**
     * Detects collision between a paint and a boundary wall.  Change direction if ball collides with boundary.
     */
    public abstract boolean detectCollisionBoundary();

    /**
     * Detects collision between a paint and another paint.  Change direction if two objects collides with each other.
     */
    public abstract boolean detectCollisionObject(APaintObject obj);

    /**
     * Get the paintable object type.
     * @return The paintable object type
     */
    public String getType() {
        return type;
    }

    /**
     * Get the paint location in the paint world.
     * @return The paint location.
     */
    public Point getLocation() {
        return loc;
    }


    /**
     * Set the paint location in the canvas.  The origin (0,0) is the top left corner of the canvas.
     * @param loc  The paint coordinate.
     */
    public void setLocation(Point loc) {
        this.loc = loc;
    }

    /**
     * Check if the paint object is colorable.  For example, images are not colorable and would return false.
     */
    public boolean isColorable() {
        if (!type.equals("ball")) {
            return false;
        }
        return true;
    }

    /**
     * Get the paintable object color.
     * @return The paintable object color
     */
    public String getColor() {
        return color;
    }

    /**
     * Set the paintable object color.
     * @param color The new color
     */
    public void setColor(String color) {
        if (isColorable()) {
            this.color = color;
        }
    }

    /**
     * Compute the next location of the paint in the paint world given the velocity.
     * @param velX The horizontal velocity
     * @param velY The vertical velocity
     */
    public void nextLocation(int velX, int velY) {
        loc.x += velX;
        loc.y += velY;
    }

    /**
     * Get the velocity of the paint.
     * @return The paint velocity
     */
    public  Point getVelocity() {
        return vel;
    }

    /**
     * Set the velocity of the paint.
     * @param vel The new paint velocity
     */
    public void setVelocity(Point vel) {
        this.vel = vel;
    }

    /**
     * Get the paint object strategy.
     * @return The paint object strategy
     */
    public IUpdateStrategy getBehaviorStrategy() {
        return behaviorStrategy;
    }

    /**
     * Get the paint object interaction strategy.
     * @return The paint object interaction strategy
     */
    public IInteractStrategy getInteractStrategy() {
        return interactStrategy;
    }

    public void setBehaviorStrategy(IUpdateStrategy behaviorStrategy) {
        this.behaviorStrategy = behaviorStrategy;
    }

    public void setInteractStrategy(IInteractStrategy interactStrategy) {
        this.interactStrategy = interactStrategy;
    }

    /**
     * Get the theta of the object.
     * @return The object rotation angle
     */
    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    /**
     * Get the radius of the object.
     * @return The object radius.
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Set the radius of the object.
     * @param r The object radius.
     */
    public void setRadius(double r) {
        this.radius = r;
    }

}
