package pong;

import java.awt.Color;
import java.awt.Graphics;

public final class TableDivision {
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		for(int i = 0, x = 2; i < 11; i++, x+=15) {
			g.fillRect(x, (Game.HEIGHT/2), 7, 1);	
		}
	}
}
