package edu.rice.comp504.model.paintobj;

/**
 * A factory that makes Ball.
 */
public interface IBallFac {
    /**
     * Makes a ball.
     * @return A Ball
     */
    Ball makeBall(String type);


}
