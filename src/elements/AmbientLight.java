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

    public AmbientLight() {
        intensity = Color.BLACK;
    }

    /**
     * constructor
     * @param color light's color
     * @param kA reduction factor
     */
    public AmbientLight(Color color, double kA) {
        intensity = color.scale(kA);
    }

    /**
     * @return intensity of ambient light
     */
    public Color getIntensity() {
        return intensity;
    }
}
