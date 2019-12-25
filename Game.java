package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	public static int WIDTH = 160;
	public static int HEIGHT = 120;
	private static int SCALE = 3;
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	private TableDivision tableDivision;
	private Points points;
	private Thread thread;
	private boolean isRunning = true;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		this.addKeyListener(this);
		player = new Player(100, HEIGHT-10);
		enemy = new Enemy(100, 5);
		ball = new Ball(100, HEIGHT/2-1);
		tableDivision = new TableDivision();
		points = new Points();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		Game game = new Game();
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = layer.getGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		player.render(g);
		enemy.render(g);
		ball.render(g);
		tableDivision.render(g);
		points.render(g);
		
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		bs.show();
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
			}
		}
		
		stop();
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		boolean rightIsPressed = (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D);
		boolean leftIsPressed = (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A);
		if(rightIsPressed) {
			player.setRightToTrue();
		}else if(leftIsPressed) {
			player.setLeftToTrue();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}
