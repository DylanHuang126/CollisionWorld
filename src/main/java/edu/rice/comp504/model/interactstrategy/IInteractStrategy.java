package edu.rice.comp504.model.interactstrategy;

import edu.rice.comp504.model.paintobj.APaintObject;

public interface IInteractStrategy {
    /**
     * The name of the strategy.
     * @return strategy name
     */
    String getName();

    /**
     * Update the state of the ball.
     * @param context The ball.
     */
    void updateState(APaintObject context);
}
