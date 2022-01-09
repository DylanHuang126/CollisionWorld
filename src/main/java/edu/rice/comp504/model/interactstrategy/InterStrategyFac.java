package edu.rice.comp504.model.interactstrategy;


/**
 * A factory that makes interaction strategies.
 */
public interface InterStrategyFac {

    /**
     * Makes a strategy.
     * @return A strategy
     */
    IInteractStrategy make(String type);
}