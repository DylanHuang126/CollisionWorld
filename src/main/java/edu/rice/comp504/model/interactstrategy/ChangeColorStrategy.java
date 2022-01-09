package edu.rice.comp504.model.interactstrategy;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.util.RandUtil;

public class ChangeColorStrategy implements IInteractStrategy{
    private static IInteractStrategy Only;
    private String[] availColors = {"red", "blue", "yellow", "green", "black", "purple", "orange", "gray", "brown"};

    /**
     * Constructor.
     */
    private ChangeColorStrategy() {
    }

    /**
     * Make a straight strategy.  There is only one (singleton).
     * @return The singleton straight strategy
     */
    public static IInteractStrategy make() {
        if (Only == null) {
            Only = new ChangeColorStrategy();
        }
        return Only;
    }

    @Override
    public String getName() {
        return "change color";
    }

    @Override
    public void updateState(APaintObject context) {
        String color = availColors[RandUtil.getRnd(0, availColors.length - 1)];
        while (color.equals(context.getColor())) {
            color = availColors[RandUtil.getRnd(0, availColors.length - 1)];
        }
        context.setColor(color);
    }
}
