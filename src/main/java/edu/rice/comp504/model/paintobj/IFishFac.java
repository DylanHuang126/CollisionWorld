package edu.rice.comp504.model.paintobj;

/**
 * A factory that makes Fish.
 */
public interface IFishFac {
    /**
     * Makes a fish.
     * @return A Fish
     */
    Fish makeFish(String type);
}
