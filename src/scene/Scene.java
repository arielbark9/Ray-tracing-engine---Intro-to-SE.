package scene;

import elements.AmbientLight;
import geometries.Geometries;
import geometries.Geometry;
import primitives.Color;

/**
 * Passive Data Structure representing a scene of geometries and lights.
 */
public class Scene {
    /**
     * name of scene
     */
    public String name;

    /**
     * Scene background color
     */
    public Color background = Color.BLACK;

    /**
     * Scene ambient light
     */
    public AmbientLight ambientLight = new AmbientLight(Color.BLACK,0);

    /**
     * geometries in scene (composite model)
     */
    public Geometries geometries;

    /**
     * constructor initiates geometries model.
     * @param name name of scene
     */
    public Scene(String name) {
        this.name = name;
        geometries = new Geometries();
    }

    /**
     * builder pattern setter
     * @param background background color
     * @return this object
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * builder pattern setter
     * @param ambientLight ambient light
     * @return this object
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * @param geometries list of geometries to add to scene model
     */
    public Scene addGeometries(Geometry... geometries) {
        this.geometries.add(geometries);
        return this;
    }
}
