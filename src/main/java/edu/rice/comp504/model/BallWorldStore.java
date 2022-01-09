package edu.rice.comp504.model;

import edu.rice.comp504.model.cmd.SwitchStrategyCmd;
import edu.rice.comp504.model.cmd.UpdateStateCmd;
import edu.rice.comp504.model.paintobj.*;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A store containing current Ball World.
 */
public class BallWorldStore {
    private PropertyChangeSupport pcs;
    private static Point dims;
    private static BallFac ballFac;
    private static FishFac fishFac;

    /**
     * Constructor.
     */
    public BallWorldStore() {
        pcs = new PropertyChangeSupport(this);
    }

    /**
     * Get the singleton Ball factory.
     * @return The Ball factory
     */
    public static BallFac getOnlyBallFac() {
        if (ballFac == null) {
            ballFac = new BallFac();
        }
        return ballFac;
    }

    /**
     * Get the singleton Fish factory.
     * @return The Fish factory
     */
    public static FishFac getOnlyFishFac() {
        if (fishFac == null) {
            fishFac = new FishFac();
        }
        return fishFac;
    }

    /**
     * Get the canvas dimensions.
     * @return The canvas dimensions
     */
    public static Point getCanvasDims() {
        return dims;
    }

    /**
     * Set the canvas dimensions.
     * @param dims The canvas width (x) and height (y).
     */
    public static void setCanvasDims(String dims) {
        String[] xy = dims.split("&");
        int x = Integer.parseInt(xy[1].replace("width=", ""));
        int y = Integer.parseInt(xy[0].replace("height=", ""));
        BallWorldStore.dims = new Point(x, y);
    }

    /**
     * Call the update method on all the ball observers to update their position in the ball world.
     */
    public PropertyChangeListener[] updateBallWorld() {
        // TODO: fill in
        pcs.firePropertyChange("time", null, new UpdateStateCmd(pcs.getPropertyChangeListeners("time")));
        return pcs.getPropertyChangeListeners();
    }


    /**
     * @param behavior The object behavior.
     * @param interaction The object interaction
     * @param canSwitchStrat If the object can switch strategy
     * @param type The object type
     * @return object
     */
    public APaintObject loadPaintObj(String behavior, String interaction, Boolean canSwitchStrat, String type) {
        APaintObject po;
        if (type.equals("ball")) {
            po = getOnlyBallFac().make(behavior, interaction);
        } else if (type.equals("fish")) {
            po = getOnlyFishFac().make(behavior, interaction);
        } else {
            po = NullObject.make();
        }
        addListener(po);
        if (canSwitchStrat) {
            addSwitchListener(po);
        }
        return po;
    }

    /**
     * Switch the strategy of switcher balls
     * @param body  The REST request strategy.
     */
    public PropertyChangeListener[] switchStrategy(String body) {
        IUpdateStrategy strat = ballFac.getOnlyBallStrategyFac().make(body);
        pcs.firePropertyChange("stratChange", null, new SwitchStrategyCmd(strat));
        return pcs.getPropertyChangeListeners();
    }



    /**
     * Add a ball or inner wall to the property change listener list.
     * @param pcl  The ball or inner wall
     */
    private void addListener(PropertyChangeListener pcl) {
        this.pcs.addPropertyChangeListener("time", pcl);
    }

    /**
     * Add a ball or inner wall to the property change listener list.
     * @param pcl  The ball or inner wall
     */
    private void addSwitchListener(PropertyChangeListener pcl) {
        this.pcs.addPropertyChangeListener("stratChange", pcl);
    }

    /**
     * Remove all property change listeners.
     */
    public PropertyChangeListener[] removeListeners() {
        PropertyChangeListener[] pclArr = pcs.getPropertyChangeListeners();
        for (PropertyChangeListener pcl: pclArr) {
            pcs.removePropertyChangeListener(pcl);
        }
        return pcs.getPropertyChangeListeners();
    }

    /**
     * @param strat The strategy to remove.
     * @return listeners
     */
    public PropertyChangeListener[] removeSomeListeners(String strat) {
        PropertyChangeListener[] pclArr = pcs.getPropertyChangeListeners("time");
        for (PropertyChangeListener pcl: pclArr) {
            if (((APaintObject) pcl).getBehaviorStrategy().getName().equals(strat)) {
                pcs.removePropertyChangeListener("time", pcl);
            }
        }
        return pcs.getPropertyChangeListeners();
    }
}
