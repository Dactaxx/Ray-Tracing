package raytracing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Barrier {
	private double ax, ay, bx, by;
	public Barrier(double ax, double ay, double bx, double by) {
		this.ax = ax;
		this.ay = ay;
		this.bx = bx;
		this.by = by;
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(new Color(255, 255, 255));
		g2d.setStroke(new BasicStroke(5));
		g2d.drawLine((int)ax, 1080 - (int)ay, (int)bx, 1080 - (int)by);
	}
	
	public double getAx() {
		return ax;
	}
	
	public double getAy() {
		return ay;
	}
	
	public double getBx() {
		return bx;
	}
	
	public double getBy() {
		return by;
	}
	
}
