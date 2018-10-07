package raytracing;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class RayCollision {
	public ArrayList<Barrier> barriers = new ArrayList<Barrier>();
	public ArrayList<Ray> rays = new ArrayList<Ray>();
	public ArrayList<Ray> raysToBeAdded = new ArrayList<Ray>();
	public ArrayList<Ray> raysToBeRemoved = new ArrayList<Ray>();
	
	public void tick() {
		for(Ray r : rays) {
			if(!r.isExpired() && r.getT() < r.getTExpiration()) r.tick();
			else if(!r.isExpired()){
				r.setExpired(true);
				raysToBeRemoved.add(r);
				raysToBeAdded.add(new Ray((r.getTExpiration() - .00001) * Math.cos(r.getAngle()) + r.getPx(), (r.getTExpiration() - .00001) * Math.sin(r.getAngle()) + r.getPy(), r.getReflectionAngle(), this));
			}
		}
		for(Ray r : raysToBeAdded) {
			rays.add(r);
		}
		raysToBeAdded = new ArrayList<Ray>();
		for(Ray r : raysToBeRemoved) {
			rays.remove(r);
		}
		raysToBeRemoved = new ArrayList<Ray>();
		
	}
	
	public void render(Graphics2D g2d) {
		for(Barrier b : barriers) {
			b.render(g2d);
		}
		
		for(int i = 0; i < rays.size(); i++) {
			rays.get(i).render(g2d);
		}
	}
}
