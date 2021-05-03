package unittests.renderer;

import org.junit.Test;
import primitives.Color;
import renderer.ImageWriter;

import static org.junit.Assert.*;

public class ImageWriterTest {
    @Test
    public void testImageWriter(){
        ImageWriter imageWriter = new ImageWriter("initialTest",800,500);
        Color gridColor = new Color(0,255,255);
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 800; j++) {
                if((j!=0 && i!=0) && (j % 50 == 0 || i % 50 == 0))
                    imageWriter.writePixel(j,i,gridColor);
                else
                    imageWriter.writePixel(j,i, new Color(255,192,203));
            }
        }
        imageWriter.writeToImage();
    }
}