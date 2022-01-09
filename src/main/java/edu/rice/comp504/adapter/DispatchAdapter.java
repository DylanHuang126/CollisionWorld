package edu.rice.comp504.adapter;

import edu.rice.comp504.model.BallWorldStore;
import edu.rice.comp504.model.paintobj.APaintObject;

import java.beans.PropertyChangeListener;

/**
 * This adapter interfaces with the view (paint objects) and the controller.
 */
public class DispatchAdapter {
    private final BallWorldStore ballWorldStore;
    /**
     * Constructor call.
     */
    public DispatchAdapter(BallWorldStore bws) {
        this.ballWorldStore = bws;
    }

    /**
     * Set the canvas dimensions.
     * @param dims The canvas width (x) and height (y).
     */
    public void setCanvasDims(String dims) {
        BallWorldStore.setCanvasDims(dims);
    }

    /**
     * Update the ball world.
     * @return Balls in BallWorld
     */
    public PropertyChangeListener[] updateBallWorld() {
        return ballWorldStore.updateBallWorld();
    }

    /**
     * Load a paintobj into the paintobj world.
     * @param type  The paintobj type (line, ball)
     * @return A paintobj
     */
    public APaintObject loadPaintObj(String behavior, String interaction, Boolean canSwitchStrat, String type) {
        return ballWorldStore.loadPaintObj(behavior, interaction, canSwitchStrat, type);
    }


    /**
     * Switch the strategy for switcher balls
     * @param strat  The REST request strategy.
     * @return Balls in BallWorld
     */
    public PropertyChangeListener[] switchStrategy(String strat) {
        return ballWorldStore.switchStrategy(strat);
    }


    /**
     * Remove all balls from listening for property change events for a particular property.
     * @return Balls in BallWorld
     */
    public PropertyChangeListener[] removeAll() {
        return ballWorldStore.removeListeners();
    }

    public PropertyChangeListener[] removeSome(String strat) {
        return ballWorldStore.removeSomeListeners(strat);
    }
}
