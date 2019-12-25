package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Entity implements Pontuable {
	
	public boolean right, left;
	public static int POINTS = 0;
	
	public Player(double x, double y) {
		super(x, y, 40, 5);
	}
	
	
	public void setRightToTrue() {
		this.right = true;
		this.left = false;
	}
	
	public void setLeftToTrue() {
		this.left = true;
		this.right = false;
	}
	
	private void move() {
		if(right)
			x++;
		else if(left)
			x--;
	}
	
	private void anallyzeCollision() {
		if(x+width > Game.WIDTH)
			x = Game.WIDTH - width;
		else if(x < 0)
			x = 0;
	}
	
	@Override
	public void tick() {
		move();
		anallyzeCollision();
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(this.getX(), this.getY(), this.width, this.height);
	}


	@Override
	public void makeAPoint() {
		Player.POINTS++;
	}
}
