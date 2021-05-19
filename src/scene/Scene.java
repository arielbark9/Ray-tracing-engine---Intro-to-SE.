package scene;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import geometries.Geometry;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

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
    public AmbientLight ambientLight = new AmbientLight();

    /**
     * geometries in scene (composite model)
     */
    public Geometries geometries;

    public List<LightSource> lights = new LinkedList<>();

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
     * builder pattern setter
     * @param lights lights list
     * @return this object
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
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
