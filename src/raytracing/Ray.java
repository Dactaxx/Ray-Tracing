package raytracing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Ray {
	private double angle, px, py, x, y, t, tExpiration, reflectionAngle;
	private boolean expired = false;
	
	public Ray(double px, double py, double angle, RayCollision rc) {
		this.angle = angle;
		this.px = px;
		this.py = py;
		this.x = px;
		this.y = py;
		this.t = 0;
		this.reflectionAngle = 0;
		
		tExpiration = 69420;
		double bCollisionAngle = 0;
		for(Barrier b : rc.barriers) {
			double bAngle = Math.atan2(b.getBy() - b.getAy(), b.getBx() - b.getAx());
//			double tx = (b.getAx() - px) / (Math.cos(angle) - Math.cos(bAngle));
//			double ty = (b.getAy() - py) / (Math.sin(angle) - Math.sin(bAngle));
			double tCollision = (Math.sin(bAngle) * (px - b.getAx()) + Math.cos(bAngle) * (b.getAy() - py)) / (Math.sin(angle) * Math.cos(bAngle) - Math.cos(angle) * Math.sin(bAngle));
			boolean inXBounds = Math.abs(((b.getAx() + b.getBx()) / 2 - (tCollision * Math.cos(angle) + px))) <= Math.abs(b.getAx() - (b.getAx() + b.getBx()) / 2 + 1);
			boolean inYBounds = Math.abs(((b.getAy() + b.getBy()) / 2 - (tCollision * Math.sin(angle) + py))) <= Math.abs(b.getAy() - (b.getAy() + b.getBy()) / 2 + 1);
//			System.out.println(Math.abs(((b.getAx() + b.getBx()) / 2 - tx * Math.cos(angle))));
//			System.out.println(Math.abs(b.getAx() - (b.getAx() + b.getBx()) / 2 + 1));
			if(tCollision > 0 && inXBounds && inYBounds) {
				tExpiration = tCollision;
				bCollisionAngle = bAngle;
			}
		}
//		reflectionAngle = Math.PI / 2d + (-this.angle - bCollisionAngle + Math.PI / 2d) + bCollisionAngle;
		reflectionAngle = 2 * (Math.PI + bCollisionAngle - angle) + angle;
		System.out.println(reflectionAngle);
	}
	
	public void tick() {
		t+=2;
		x = t * Math.cos(angle) + px;
		y = t * Math.sin(angle) + py;
		
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(new Color(255, 0, 0));
		g2d.setStroke(new BasicStroke(2));
		g2d.drawLine((int)px, 1080 - (int)py, (int)x, 1080 - (int)y);
	}
	
	public double getAngle() {
		return angle;
	}

	public double getPx() {
		return px;
	}

	public double getPy() {
		return py;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getT() {
		return t;
	}
	
	public void setT(double t) {
		this.t = t;
	}
	
	public double getTExpiration() {
		return tExpiration;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	
	public double getReflectionAngle() {
		return reflectionAngle;
	}


}
