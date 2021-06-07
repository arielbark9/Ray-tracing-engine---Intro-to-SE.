package unittests.renderer;

import geometries.Cylinder;
import geometries.Plane;
import org.junit.Test;

import elements.*;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.*;
import scene.Scene;


/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 */
public class ReflectionRefractionTests {
	private Scene scene = new Scene("Test scene");
	private final int rayCount = 64;
	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(150, 150).setDistance(1000);

		scene.geometries.add( //
				new Sphere(new Point3D(0, 0, -50),50) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
				new Sphere(new Point3D(0, 0, -50),25) //
						.setEmission(new Color(java.awt.Color.RED)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
		scene.lights.add( //
				new SpotLight(new Color(1000, 600, 0), new Point3D(-100, -100, 500), new Vector(-1, -1, -2)) //
						.setKl(0.0004).setKq(0.0000006));

		Render render = new Render() //
				.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene))
				.setSSrays(rayCount)
				.setMultithreading(3)
				.setDebugPrint();
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Camera camera = new Camera(new Point3D(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(2500, 2500).setDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.geometries.add( //
				new Sphere(new Point3D(-950, -900, -1000),400) //
						.setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.5)),
				new Sphere(new Point3D(-950, -900, -1000),200) //
						.setEmission(new Color(100, 20, 20)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
						new Point3D(670, 670, 3000)) //
								.setEmission(new Color(20, 20, 20)) //
								.setMaterial(new Material().setKr(1)),
				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
						new Point3D(-1500, -1500, -2000)) //
								.setEmission(new Color(20, 20, 20)) //
								.setMaterial(new Material().setKr(0.5)));

		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setKl(0.00001).setKq(0.000005));

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene))
				.setSSrays(rayCount)
				.setMultithreading(3)
				.setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void myTest() {
		Camera camera = new Camera(new Point3D(1200, -1200, 1000), new Vector(-1, 1, -1), new Vector(-700, 700, 1400)) //
				.setViewPlaneSize(1000, 1000).setDistance(1000); //
		scene.setBackground(Color.BLACK);
		Color tableColor = new Color(240,128,128);
		Material mirror = new Material().setKd(0.15).setKr(0.75).setKs(0.75).setShininess(2);
		Material wallMat = new Material().setKd(0.5).setKs(0.7);
		Material tableTop = new Material().setKd(0.7).setKs(0.15);
		Material tableLegs = new Material().setKd(0.5).setKs(0.25);
		scene.geometries.add( //
				//ABC
				new Triangle(new Point3D(400,-400,0),new Point3D(400,400,0),new Point3D(-400,400,0))
						.setEmission(tableColor).setMaterial(tableTop),
				//ACD
				new Triangle(new Point3D(400,-400,0),new Point3D(-400,400,0),new Point3D(-400,-400,0))
						.setEmission(tableColor).setMaterial(tableTop),
				//OMN
				new Triangle(new Point3D(-400,400,40),new Point3D(400,-400,40),new Point3D(400,400,40))
						.setEmission(tableColor).setMaterial(tableTop),
				//OMP
				new Triangle(new Point3D(-400,400,40),new Point3D(400,-400,40),new Point3D(-400,-400,40))
						.setEmission(tableColor).setMaterial(tableTop),
				//ANM
				new Triangle(new Point3D(400,-400,0),new Point3D(400,400,40),new Point3D(400,-400,40))
						.setEmission(tableColor).setMaterial(tableTop),
				//ANB
				new Triangle(new Point3D(400,-400,0),new Point3D(400,400,40),new Point3D(400,400,0))
						.setEmission(tableColor).setMaterial(tableTop),
				//APM
				new Triangle(new Point3D(400,-400,0),new Point3D(-400,-400,40),new Point3D(400,-400,40))
						.setEmission(tableColor).setMaterial(tableTop),
				//APD
				new Triangle(new Point3D(400,-400,0),new Point3D(-400,-400,40),new Point3D(-400,-400,0))
						.setEmission(tableColor).setMaterial(tableTop),
				//DPO
				new Triangle(new Point3D(-400,-400,0),new Point3D(-400,-400,40),new Point3D(-400,400,40))
						.setEmission(tableColor).setMaterial(tableTop),
				//DOC
				new Triangle(new Point3D(-400,-400,0),new Point3D(-400,400,40),new Point3D(-400,400,0))
						.setEmission(tableColor).setMaterial(tableTop),
				//K-Cyl
				new Cylinder(new Ray(new Point3D(-300,-300,-300),new Vector(0,0,1)), 40, 300)
						.setEmission(new Color(0,0,128)).setMaterial(tableLegs),
				//E-Cyl
				new Cylinder(new Ray(new Point3D(300,-300,-300),new Vector(0,0,1)), 40, 300)
						.setEmission(new Color(0,0,128)).setMaterial(tableLegs),
				//C-Cyl
				new Cylinder(new Ray(new Point3D(300,300,-300),new Vector(0,0,1)), 40, 300)
						.setEmission(new Color(0,0,128)).setMaterial(tableLegs),
				//L-Cyl
				new Cylinder(new Ray(new Point3D(-300,300,-300),new Vector(0,0,1)), 40, 300)
						.setEmission(new Color(0,0,128)).setMaterial(tableLegs),
				//Floor
				new Plane(new Point3D(0,0,-300), new Vector(0,0,1))
						.setEmission(new Color(102, 102, 153)).setMaterial(wallMat),
				//Wall 1
				new Plane(new Point3D(0,1000,0), new Vector(0,-1,0))
						.setEmission(new Color(Color.BLACK)).setMaterial(wallMat),
				//Wall 2
				new Plane(new Point3D(-1000,0,0), new Vector(1,0,0))
						.setEmission(new Color(Color.BLACK)).setMaterial(wallMat),
				//Mirror 1
				new Triangle(new Point3D(400,998,0),new Point3D(400,998,600),new Point3D(-400,998,0))
						.setEmission(Color.BLACK).setMaterial(mirror),
				//Mirror 2
				new Triangle(new Point3D(-400,998,600),new Point3D(400,998,600),new Point3D(-400,998,0))
						.setEmission(Color.BLACK).setMaterial(mirror),
				//back of Mirror 1
				new Triangle(new Point3D(410,999,-10),new Point3D(410,999,610),new Point3D(-410,999,-10))
						.setEmission(new Color(255, 255, 204)).setMaterial(wallMat),
				//back of Mirror 2
				new Triangle(new Point3D(-410,999,610),new Point3D(410,999,610),new Point3D(-410,999,-10))
						.setEmission(new Color(255, 255, 204)).setMaterial(wallMat),
				// Bulb sphere
				new Sphere(new Point3D(0,0,700),100).setEmission(new Color(153, 255, 102)).setMaterial(new Material().setKt(0.9))
		);


		Color color;
		for (int i = -200; i < 200; i+=50) {
			for (int j = -200; j < 200; j+=50) {
				if(((i+200)/50)%2 == 0)
					color = (((j+200)/50)%2 == 0)? Color.BLACK : new Color(255,255,255);
				else
					color = (((j+200)/50)%2 != 0)? Color.BLACK : new Color(255,255,255);

				scene.geometries.add(new Triangle(new Point3D(j,i,45),new Point3D(j+50,i,45),new Point3D(j,i+50,45))
						.setEmission(color),
						new Triangle(new Point3D(j+50,i+50,45),new Point3D(j+50,i,45),new Point3D(j,i+50,45))
								.setEmission(color));

				if(color.equals(Color.BLACK) && (i<-50 || i>=50))
				{
					if(i<-50)
						color = new Color(153, 102, 0);
					else
						color = new Color(123, 104, 255);

					scene.geometries.add(new Cylinder(new Ray(new Point3D(j+25,i+25,45),new Vector(0,0,1)),15,7)
							.setEmission(color));
				}
			}
		}

		scene.lights.add(new DirectionalLight(new Color(255, 255, 153),new Vector(1,0,0)));
		scene.lights.add(new PointLight(new Color(153, 255, 102),new Point3D(0,0,700))
				.setKl(0.00001).setKq(0.000001));

		ImageWriter imageWriter = new ImageWriter("myTest", 1000, 1000);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene))
				.setSSrays(rayCount)
				.setMultithreading(3)
				.setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200).setDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.geometries.add( //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
				new Sphere(new Point3D(60, 50, -50),30) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1)) //
				.setKl(4E-5).setKq(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene))
				.setSSrays(rayCount)
				.setMultithreading(3)
				.setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}
}
