package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends Entity implements Pontuable {

	public static int POINTS = 0;
	
	public Enemy(double x, double y) {
		super(x, y, 40, 5);
	}

	@Override
	public void tick() {
		x += (Game.ball.x - x - 6) * 0.08;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(this.getX(), this.getY(), this.width, this.height);
	}

	@Override
	public void makeAPoint() {
		Enemy.POINTS++;
	}
}
