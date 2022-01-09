package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paintobj.APaintObject;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

public class SwitchStrategyCmd implements IBallCmd{
    private IUpdateStrategy strat;
    /**
     * The constructor.
     * @param strat The new strategy
     *
     */
    public SwitchStrategyCmd(IUpdateStrategy strat) {
        this.strat = strat;
    }

    /**
     * Update the state of the paint object
     * @param context  The paint object.
     */
    @Override
    public void execute(APaintObject context) {
        context.setBehaviorStrategy(strat);
    }
}
