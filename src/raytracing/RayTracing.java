package raytracing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import graphics.DGraphics;
import graphics.GraphicsInterface;

public class RayTracing {
	public static DGraphics g;
	public static int a = 0;
	public static ArrayList<GraphicsInterface> gis = new ArrayList<GraphicsInterface>();
	public static RayCollision rc = new RayCollision();
	public static void main(String[] args) {
		rc.barriers.add(new Barrier(440, 1080 - 100, 1920 - 440, 1080 - 100));
		rc.barriers.add(new Barrier(440, 100, 1920 - 440, 100));
		rc.barriers.add(new Barrier(440, 1080 - 100, 440, 100));
		rc.barriers.add(new Barrier(1920 - 440, 1080 - 100, 1920 - 440, 100));
		
		for(int i = 0; i < 12; i++)
		rc.rays.add(new Ray(960, 540, i * Math.PI/6d, rc));
		
		gis.add(new GraphicsInterface() {
			@Override
			public void render(Graphics2D g2d) {
				g2d.setColor(new Color(0, 0, 0));
				g2d.fillRect(0, 0, 1920, 1080);

				rc.render(g2d);
			}

			@Override
			public void tick() {
				rc.tick();
				
			}
			
		});
		
		g = new DGraphics(gis);
	}
}
