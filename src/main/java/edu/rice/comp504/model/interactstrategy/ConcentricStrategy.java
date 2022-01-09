package edu.rice.comp504.model.interactstrategy;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.ConcentricBall;

public class ConcentricStrategy implements IInteractStrategy{
    private static IInteractStrategy Only;

    /**
     * Constructor.
     */
    private ConcentricStrategy() {
    }

    /**
     * Make a straight strategy.  There is only one (singleton).
     * @return The singleton straight strategy
     */
    public static IInteractStrategy make() {
        if (Only == null) {
            Only = new ConcentricStrategy();
        }
        return Only;
    }

    @Override
    public String getName() {
        return "concentric";
    }

    @Override
    public void updateState(APaintObject context) {
        if (context.getType().equals("concentric ball")) {
            ConcentricBall ball = (ConcentricBall) context;
            ball.setConcentric(!ball.isConcentric());
        }
    }
}
