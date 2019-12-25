package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public final class Points {

	private final int X = 5;
	private final int EnemyY = 75;
	private final int PlayerY = 50;
	private final int fontSize = 8;
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		
		StringBuffer strEnemy = new StringBuffer();
		StringBuffer strPlayer = new StringBuffer();
		
		strPlayer.append("E: ").append(Enemy.POINTS);
		strEnemy.append("P: ").append(Player.POINTS);
		
	    g.setFont(new Font(null, Font.PLAIN, fontSize));
		g.drawString(strEnemy.toString(), X, EnemyY);
		g.drawString(strPlayer.toString(), X, PlayerY);
		
	}
}
