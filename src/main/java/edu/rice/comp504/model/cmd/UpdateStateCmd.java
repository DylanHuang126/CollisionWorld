package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.paintobj.Fish;

import java.beans.PropertyChangeListener;

public class UpdateStateCmd implements IBallCmd{
    private final PropertyChangeListener[] objs;

    /**
     * The constructor.
     * @param objs The canvas inner walls
     *
     */
    public UpdateStateCmd(PropertyChangeListener[] objs) {
        this.objs = objs;
    }

    /**
     * Update the state of the paint object
     * @param context  The paint object.
     */
    @Override
    public void execute(APaintObject context) {
        for (PropertyChangeListener obj: objs) {
            APaintObject p = (APaintObject) obj;
            if (!context.equals(obj)) {
                if (context.detectCollisionObject(p)) {
                    context.getInteractStrategy().updateState(context);
                }
            }
        }

        context.getBehaviorStrategy().updateState(context);

        if (context.getType().equals("fish")) {
            Fish fish = (Fish) context;
            double theta = fish.getTheta();
            if (context.getBehaviorStrategy().getName().equals("rotate")) {
                if ((theta % (Math.PI * 2) < Math.PI / 2 && theta % (Math.PI * 2) > (-1 * Math.PI / 2))) {
                    fish.setDirection(1);
                } else {
                    fish.setDirection(-1);
                }
            } else {
                if (fish.getVelocity().x < 0) {
                    fish.setDirection(1);
                } else {
                    fish.setDirection(-1);
                }
            }
        }
    }
}
