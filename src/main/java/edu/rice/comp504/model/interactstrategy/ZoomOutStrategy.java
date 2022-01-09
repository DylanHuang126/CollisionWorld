package edu.rice.comp504.model.interactstrategy;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.util.Set;

public class ZoomOutStrategy implements IInteractStrategy{
    private static IInteractStrategy Only;

    /**
     * Constructor.
     */
    private ZoomOutStrategy() {
    }


    /**
     * Make a straight strategy.  There is only one (singleton).
     * @return The singleton straight strategy
     */
    public static IInteractStrategy make() {
        if (Only == null) {
            Only = new ZoomOutStrategy();
        }
        return Only;
    }

    @Override
    public String getName() {
        return "zoom out";
    }

    @Override
    public void updateState(APaintObject context) {
        Set<String> balls = Set.of("size changeable ball", "concentric ball", "ball");
        if (balls.contains(context.getType())) {
            final double param = 0.8;
            final double minR = 5;
            if (context.getRadius() > minR) {
                context.setRadius(param * context.getRadius());
            }
        }

    }
}
