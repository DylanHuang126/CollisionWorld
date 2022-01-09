package edu.rice.comp504.model.interactstrategy;

import edu.rice.comp504.model.paintobj.APaintObject;

import java.util.Set;

public class ZoomInStrategy implements IInteractStrategy{
    private static IInteractStrategy Only;

    /**
     * Constructor.
     */
    private ZoomInStrategy() {
    }

    /**
     * Make a straight strategy.  There is only one (singleton).
     * @return The singleton straight strategy
     */
    public static IInteractStrategy make() {
        if (Only == null) {
            Only = new ZoomInStrategy();
        }
        return Only;
    }

    @Override
    public String getName() {
        return "zoom in";
    }

    @Override
    public void updateState(APaintObject context) {
        Set<String> balls = Set.of("size changeable ball", "concentric ball", "ball");
        if (balls.contains(context.getType())) {
            final double param = 1.2;
            final double maxR = 30;
            if (context.getRadius() < maxR) {
                context.setRadius(param * context.getRadius());
            }
        }
    }
}
