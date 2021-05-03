package elements;

import primitives.Color;

/**
 * A light element representing ambient light -
 * affects everything equally.
 */
public class AmbientLight {
    /**
     * intensity and color of light
     */
    private Color intensity;

    /**
     * constructor
     * @param color light's color
     * @param kA reducing factor
     */
    public AmbientLight(Color color, double kA) {
        intensity = color.reduce(kA);
    }

    /**
     * @return intensity of ambient light
     */
    public Color getIntensity() {
        return intensity;
    }
}
