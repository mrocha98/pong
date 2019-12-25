package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball extends Entity {
	public double dx, dy;
	public double speed = 1.7;
	
	public Ball(double x, double y) {
		super(x, y, 4, 4);
		
		int angle = new Random().nextInt(120 - 45) + 45 + 1;
		this.dx = Math.cos(Math.toRadians(angle));
		this.dy = Math.sin(Math.toRadians(angle));
	}
	
	@Override
	public void tick() {
		if(x+(dx*speed) + width >= Game.WIDTH) {
			dx*=-1;
		}else if(x+(dx*speed) < 0) {
			dx*=-1;
		}
		
		if(y >= Game.HEIGHT) {
			Game.enemy.makeAPoint();
			new Game();
			return;
		}else if(y < 0) {
			Game.player.makeAPoint();
			new Game();
			return;
		}
		
		Rectangle bounds = new Rectangle((int)(x+(dx*speed)), (int)(y+(dy*speed)), width, height);
		Rectangle boundsPlayer = new Rectangle(Game.player.getX(), Game.player.getY(), Game.player.width, Game.player.height);
		Rectangle boundsEnemy = new Rectangle(Game.enemy.getX(), Game.enemy.getY(), Game.enemy.width, Game.enemy.height);
		
		if(bounds.intersects(boundsPlayer)) {
			int angle = new Random().nextInt(120 - 45) + 45 + 1;
			this.dx = Math.cos(Math.toRadians(angle));
			this.dy = Math.sin(Math.toRadians(angle));
			if(dy > 0)
				dy *= -1;
		}else if(bounds.intersects(boundsEnemy)) {
			int angle = new Random().nextInt(120 - 45) + 45 + 1;
			this.dx = Math.cos(Math.toRadians(angle));
			this.dy = Math.sin(Math.toRadians(angle));
			if(dy < 0)
				dy *= -1;
		}
		
		x += dx * speed;
		y += dy * speed;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(this.getX(), this.getY(), width, height);
	}
}
