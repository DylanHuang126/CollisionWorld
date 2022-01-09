package edu.rice.comp504.model.interactstrategy;

public class InteractStrategyFac implements InterStrategyFac{

    /**
     * Constructor.
     */
    public InteractStrategyFac() {
    }

    @Override
    public IInteractStrategy make(String type) {
        switch (type) {
            case "cloaking":
                return CloakingStrategy.make();
            case "stop":
                return StopStrategy.make();
            case "change color":
                return ChangeColorStrategy.make();
            case "zoom in":
                return ZoomInStrategy.make();
            case "zoom out":
                return ZoomOutStrategy.make();
            case "concentric":
                return ConcentricStrategy.make();
            default:
                return InterNullStrategy.make();
        }
    }
}