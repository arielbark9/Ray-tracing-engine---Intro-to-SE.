package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.MissingResourceException;

public class Render {
    /**
     * Image writer used to create the image
     */
    private ImageWriter imageWriter;

    /**
     * Camera taking the picture
     */
    private Camera camera;

    /**
     * some implementation of a ray tracer.
     */
    private RayTracerBase rayTracer;

    /**
     * builder pattern setter
     * @param imageWriter image writer
     * @return this object
     */
    public Render setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * builder pattern setter
     * @param camera camera
     * @return this object
     */
    public Render setCamera(Camera camera) {
        this.camera = camera;
        return this;
    }

    /**
     * builder pattern setter
     * @param rayTracer ray tracer
     * @return this object
     */
    public Render setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * render the image pixel by pixel
     */
    public void renderImage(){
        if(imageWriter == null || camera == null || rayTracer == null){
            throw new MissingResourceException("Error! cannot render image, a resource is missing",null,null);
        }

        int nX = imageWriter.getNx(), nY = imageWriter.getNy();
        for (int i = 0; i < imageWriter.getNy(); i++) {
            for (int j = 0; j < imageWriter.getNx(); j++) {
                Ray rayToPixel = camera.constructRayThroughPixel(nX,nY,j,i);
                Color pixelColor = rayTracer.traceRay(rayToPixel);
                imageWriter.writePixel(j,i,pixelColor);
            }
        }

    }

    /**
     * print a squared grid in picture
     * @param interval interval of grid (both vertically and horizontally)
     * @param gridColor color of the grid
     */
    public void printGrid(int interval, Color gridColor){
        if(imageWriter == null)
            throw new MissingResourceException("Error! cannot render image, a resource is missing",null,null);

        for (int i = 0; i < imageWriter.getNy(); i++) {
            for (int j = 0; j < imageWriter.getNx(); j++) {
                if((j!=0 && i!=0) && (i % interval == 0 || j % interval == 0))
                    imageWriter.writePixel(j,i,gridColor);
            }
        }
    }

    /**
     * write the buffered picture to the image in file.
     */
    public void writeToImage() {
        if(imageWriter == null)
            throw new MissingResourceException("Error! cannot render image, a resource is missing",null,null);
        imageWriter.writeToImage();
    }
}
