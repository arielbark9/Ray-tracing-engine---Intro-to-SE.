package elements;

import primitives.Color;

/**
 * abstract class representing a light source
 */
abstract class Light {
    /**
     * intensity of light
     */
    private Color intensity;

    /**
     * constructor
     * @param intensity intensity of light
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * @return intensity color
     */
    public Color getIntensity() {
        return intensity;
    }
}
