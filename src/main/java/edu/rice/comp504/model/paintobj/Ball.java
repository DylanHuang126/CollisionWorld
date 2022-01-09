package edu.rice.comp504.model.paintobj;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.cmd.SwitchStrategyCmd;
import edu.rice.comp504.model.cmd.UpdateStateCmd;
import edu.rice.comp504.model.interactstrategy.IInteractStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.beans.PropertyChangeEvent;

/**
 * The balls that will be drawn in the ball world.
 */
public class Ball extends APaintObject{
    private boolean isCloaking;

    /**
     * Constructor.
     * @param loc  The location of the ball on the canvas
     * @param radius The ball radius
     * @param vel  The ball velocity
     * @param behaviorStrategy The ball behavior strategy
     * @param interactStrategy The ball interact strategy
     */
    protected Ball(Point loc, double radius, Point vel, IUpdateStrategy behaviorStrategy, IInteractStrategy interactStrategy, String color, String type) {
        super(loc, vel, type, behaviorStrategy, interactStrategy, color, radius);
        this.isCloaking = false;
    }

    @Override
    public boolean detectCollisionBoundary() {
        Point dims = BallWorldStore.getCanvasDims();
        Point locc = getLocation();
        Point vel = getVelocity();
        double r = getRadius();
        double xLoc;
        double yLoc;

        if (behaviorStrategy.getName().equals("rotate")) {
            double rads = theta + Math.PI / 5;
            double vx = vel.getX() * Math.cos(rads) - vel.getY() * Math.sin(rads);
            double vy = vel.getY() * Math.cos(rads) + vel.getX() * Math.sin(rads);
            xLoc = locc.getX() + vx;
            yLoc = locc.getY() + vy;
        } else {
            xLoc = locc.getX() + vel.getX();
            yLoc = locc.getY() + vel.getY();
        }

        if (xLoc - r < 0 || xLoc + r > dims.getX()) {
            setVelocity(new Point((int) (-1 * vel.getX()), (int) (vel.getY())));
            return true;
        } else if (yLoc - r < 0 || yLoc + r > dims.getY()) {
            setVelocity(new Point((int) (vel.getX()), (int) (-1 * vel.getY())));
            return true;
        }
        return false;
    }

    // ref: https://github.com/gtiwari333/java-collision-detection-source-code
    @Override
    public boolean detectCollisionObject(APaintObject ball) {
        Point tBallLoc = getLocation();
        Point oBallLoc = ball.getLocation();
        Point tBallVel = getVelocity();
        Point oBallVel = ball.getVelocity();
        double tBallRadius = getRadius();
        double oBallRadius = ball.getRadius();

        double tBallMass = Math.pow(tBallRadius, 2);
        double oBallMass = Math.pow(oBallRadius, 2);
        double totalMass = tBallMass + oBallMass;

        double disX = tBallLoc.x - oBallLoc.x;
        double disY = tBallLoc.y - oBallLoc.y;
        double dis = tBallLoc.distance(oBallLoc);
        double radiusSum = tBallRadius + oBallRadius;

        if (dis * dis <= radiusSum * radiusSum) {
            double velX = oBallVel.x - tBallVel.x;
            double velY = oBallVel.y - tBallVel.y;
            double innerProd = disX * velX + disY * velY;

            double colX = disX * innerProd / (dis * dis);
            double colY = disY * innerProd / (dis * dis);

            double tBallRatio = tBallMass * 2 / totalMass;
            double oBallRatio = oBallMass * 2 / totalMass;

            double disRatio = (dis - radiusSum) / dis;
            double oBallVelNorm = Math.sqrt(Math.pow(oBallVel.x, 2) + Math.pow(oBallVel.y, 2));
            double tBallVelNorm = Math.sqrt(Math.pow(tBallVel.x, 2) + Math.pow(tBallVel.y, 2));
            double oBallMoveRatio = oBallVelNorm / (tBallVelNorm + oBallVelNorm);
            double tBallMoveRatio = tBallVelNorm / (tBallVelNorm + oBallVelNorm);

            int newLocX;
            int newLocY;
            newLocX = (int) (tBallLoc.x + (oBallLoc.x - tBallLoc.x) * tBallMoveRatio * disRatio);
            newLocY = (int) (tBallLoc.y + (oBallLoc.y - tBallLoc.y) * tBallMoveRatio * disRatio);
            setLocation(new Point(newLocX, newLocY));

            newLocX = (int) (oBallLoc.x + (tBallLoc.x - oBallLoc.x) * oBallMoveRatio * disRatio);
            newLocY = (int) (oBallLoc.y + (tBallLoc.y - oBallLoc.y) * oBallMoveRatio * disRatio);
            ball.setLocation(new Point(newLocX, newLocY));

            int newVelX;
            int newVelY;

            newVelX = (int) (tBallVel.x + tBallRatio * colX);
            newVelY = (int) (tBallVel.y + tBallRatio * colY);
            setVelocity(new Point(newVelX, newVelY));

            newVelX = (int) (oBallVel.x - oBallRatio * colX);
            newVelY = (int) (oBallVel.y - oBallRatio * colY);
            ball.setVelocity(new Point(newVelX, newVelY));

            return true;
        }
        return false;
    }

    /**
     * Get the ball field isCloaking.
     * @return The boolean of if the ball is cloaking
     */
    public boolean isCloaking() {
        return isCloaking;
    }

    /**
     * Set the ball field isCloaking.
     * @param cloaking The new boolean of if the ball is cloaking
     */
    public void setCloaking(boolean cloaking) {
        isCloaking = cloaking;
    }

    /**
     * Update state of the ball when the property change event occurs by executing the command.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("time")) {
            ((UpdateStateCmd) evt.getNewValue()).execute(this);
        }
        if (evt.getPropertyName().equals("stratChange")) {
            ((SwitchStrategyCmd) evt.getNewValue()).execute(this);
        }
    }

}
