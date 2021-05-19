package elements;

import primitives.Color;

/**
 * A light element representing ambient light -
 * affects everything equally.
 */
public class AmbientLight extends Light {

    public AmbientLight() {
        super(Color.BLACK);
    }

    /**
     * constructor
     * @param color light's color
     * @param kA reduction factor
     */
    public AmbientLight(Color color, double kA) {
        super(color.scale(kA));
    }

}
