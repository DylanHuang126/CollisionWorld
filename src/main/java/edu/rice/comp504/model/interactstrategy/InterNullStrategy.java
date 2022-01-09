package edu.rice.comp504.model.interactstrategy;

import edu.rice.comp504.model.paintobj.APaintObject;

public class InterNullStrategy implements IInteractStrategy{
    private static IInteractStrategy Only;

    /**
     * Constructor.
     */
    private InterNullStrategy() {
    }

    /**
     * Make a straight strategy.  There is only one (singleton).
     * @return The singleton straight strategy
     */
    public static IInteractStrategy make() {
        if (Only == null) {
            Only = new InterNullStrategy();
        }
        return Only;
    }

    @Override
    public String getName() {
        return "null";
    }

    @Override
    public void updateState(APaintObject context) {

    }
}