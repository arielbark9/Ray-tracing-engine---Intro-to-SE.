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
    Geometries geometries;

    /**
     * constructor initiates geometries model.
     * @param name name of scene
     */
    public Scene(String name) {
        this.name = name;
        geometries = new Geometries();
    }

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(Color color, double kA) {
        this.ambientLight = new AmbientLight(color,kA);
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
