package primitives;

/**
 * Material class representing different kinds of materials.
 */
public class Material {

    /**
     * attenuation factors: diffusion and specular
     */
    public double kD,kS;

    /**
     * attenuation of shininess
     */
    public int nShininess;

    /**
     * default constructor
     */
    public Material() {
        kD = kS = nShininess = 0;
    }

    /**
     * builder pattern setter
     * @param kD diffusive attenuation
     * @return this
     */
    public Material setKd(double kD) {
        this.kD = kD;
        return this;
    }

    /**
     * builder pattern setter
     * @param kS specular attenuation
     * @return this
     */
    public Material setKs(double kS) {
        this.kS = kS;
        return this;
    }

    /**
     * builder pattern setter
     * @param nShininess shininess attenuation
     * @return this
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    public double getKd() {
        return kD;
    }

    public double getKs() {
        return kS;
    }

    public int getShininess() {
        return nShininess;
    }
}
