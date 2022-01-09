package edu.rice.comp504.model.strategy;

public class BallStrategyFac implements IStrategyFac{

    /**
     * Constructor.
     */
    public BallStrategyFac() {
    }

    @Override
    public IUpdateStrategy make(String type) {
        switch (type) {
            case "straight":
                return StraightStrategy.make();
            case "falling":
                return FallingStrategy.make();
            case "rotate":
                return RotateStrategy.make();
            case "twinkling":
                return TwinklingStrategy.make();
            case "visibility":
                return VisibilityStrategy.make();
            case "static":
                return StaticStrategy.make();
            case "zoom in":
                return ZoomInStrategy.make();
            case "zoom out":
                return ZoomOutStrategy.make();
            case "twinkling rotate":
                return TwinklingRotateStrategy.make();
            case "twinkling falling":
                return TwinklingFallingStrategy.make();
            default:
                return NullStrategy.make();
        }
    }
}
