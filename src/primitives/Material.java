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
     * attenuation factors: transparency and refraction
     */
    public double kT,kR;

    /**
     * attenuation of shininess
     */
    public int nShininess;

    /**
     * default constructor
     */
    public Material() {
        kD = kS = kT = kR = nShininess = 0;
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
     * @param kT transparency attenuation
     * @return this
     */
    public Material setKt(double kT) {
        this.kT = kT;
        return this;
    }

    /**
     * builder pattern setter
     * @param kR refraction attenuation
     * @return this
     */
    public Material setKr(double kR) {
        this.kR = kR;
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

    public double getKr() {
        return kR;
    }

    public double getKt() {
        return kT;
    }

    public int getShininess() {
        return nShininess;
    }
}
