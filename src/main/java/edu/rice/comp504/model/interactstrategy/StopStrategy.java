package edu.rice.comp504.model.interactstrategy;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.strategy.StaticStrategy;

import java.awt.*;

public class StopStrategy implements IInteractStrategy{
    private static IInteractStrategy Only;

    /**
     * Constructor.
     */
    private StopStrategy() {
    }

    /**
     * Make a straight strategy.  There is only one (singleton).
     * @return The singleton straight strategy
     */
    public static IInteractStrategy make() {
        if (Only == null) {
            Only = new StopStrategy();
        }
        return Only;
    }

    @Override
    public String getName() {
        return "stop";
    }

    @Override
    public void updateState(APaintObject context) {
        context.setBehaviorStrategy(StaticStrategy.make());
    }
}