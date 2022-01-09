package edu.rice.comp504.model.interactstrategy;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Ball;

import java.util.Set;

public class CloakingStrategy implements IInteractStrategy{
    private static IInteractStrategy Only;

    /**
     * Constructor.
     */
    private CloakingStrategy() {
    }

    /**
     * Make a straight strategy.  There is only one (singleton).
     * @return The singleton straight strategy
     */
    public static IInteractStrategy make() {
        if (Only == null) {
            Only = new CloakingStrategy();
        }
        return Only;
    }

    @Override
    public String getName() {
        return "cloaking";
    }

    @Override
    public void updateState(APaintObject context) {
        Set<String> balls = Set.of("ball", "concentric ball", "size changeable ball");
        if (balls.contains(context.getType())) {
            Ball ball = (Ball) context;
            ball.setCloaking(!ball.isCloaking());
        }
    }
}
