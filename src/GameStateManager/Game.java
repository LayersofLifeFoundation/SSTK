package GameStateManager;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import FileSystem.FileOut;
import Input.InputHandler;



public class Game extends Canvas implements Runnable {
	
	//author Nick Hellerud, Jordan Haas, Ryan Cox
	/*
	 * This is a self project for our cs250 class
	 */

	JFrame frame;

	public final static int WIDTH = 1080;
	public final static int HEIGHT = WIDTH / 16 * 9;
	public final static int PIXSIZE = 49;
	public final static String Title = "PokeDAB";
	public final Dimension gameSize = new Dimension(WIDTH, HEIGHT);

	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	InputHandler IH;
	public static GameStateManager gameStateManager = new GameStateManager();
	
	FileOut test = new FileOut();
	public static Thread thread;

	static boolean gameRunning = false;

	public void run() {
		while (gameRunning) {
			
			tick();
			render();

			try {
				thread.sleep(17);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public synchronized void start() {
		gameRunning = true;
		thread = new Thread(this);
		thread.start();

	}

	public synchronized static void stop() {
		gameRunning = false;
		System.exit(0);
	}

	public Game() throws IOException {

		setMinimumSize(gameSize);
		setMaximumSize(gameSize);
		setPreferredSize(gameSize);

		frame = new JFrame();

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle(Title);
		frame.setLocationRelativeTo(null);

		IH = new InputHandler();
		frame.addKeyListener(IH);
	
		test.print();

		gameRunning = true;
		
	}

	/*
	 * function that should run any non-graphical elements that need to be updated
	 */
	public void tick() {
	
		gameStateManager.tick();

	}
	
	/*
	 *function that updates the jframe 
	 */
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		gameStateManager.render(g);

			g.dispose();
		bs.show();
	}

	public static void main(String[] args) throws IOException {
		Game game = new Game();
		game.start();

	}

}
