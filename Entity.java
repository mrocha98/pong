package pong;

import java.awt.Graphics;

public abstract class Entity {

	public double x, y; 
	public int width, height;
	
	public Entity(double x, double y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public int getX() {
		return (int) x;
	}
	
	public int getY() {
		return (int) y;
	}
}
