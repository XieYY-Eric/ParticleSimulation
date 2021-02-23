package ParticleSimulation;

import javafx.animation.FillTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Particle extends Circle {
	private double x = 10.1,y = 10.1;
	private double radius = 10;
	private FillTransition energyAnimation;
	private double dx = 0.1,dy= 0.1;
	
	
	Particle(){
		this.setRadius(radius);
		this.setCenterX(x);
		this.setCenterY(y);
		this.setFill(Color.WHITE);
		this.setStroke(Color.BLACK);
		this.energyAnimation = new FillTransition(javafx.util.Duration.millis(500), this,Color.RED,Color.WHITE);
		this.energyAnimation.setCycleCount(0);
	}
	
	
	public double getDx() {
		return dx;
	}
	
	public double getDy() {
		return dy;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setDx(double ddx) {
		this.dx = ddx;
	}
	public void setDy(double ddy) {
		this.dy = ddy;
	}
	
	public void setX(double newX) {
		this.x = newX;
	}
	
	public void setY(double newY) {
		this.y = newY;
	}
	
	public boolean contacts(Particle that) {
		double xDiff = Math.abs(x - that.getX());
		double yDiff = Math.abs(y - that.getY());
		double squrDis = Math.sqrt(xDiff*xDiff + yDiff*yDiff);
		return squrDis <= this.getRadius()*2;
	}
	
	public double getSpeed() {
		return Math.sqrt(getDx()*getDx() + getDy()*getDy());
	}
	public void energyDisplay() {
		energyAnimation.play();
	}
}
