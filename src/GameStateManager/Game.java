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

import Battle.BattleState;
import FileSystem.FileOut;
import Input.InputHandler;
import Player.Player;
import Sounds.Music;



public class Game extends Canvas implements Runnable {
	
	//author Nick Hellerud, Jordan Haas, Ryan Cox
	/*
	 * This is a self project for our cs250 class
	 */

	JFrame frame;
	
	//Dimensions of the JFrame
	public final static int WIDTH = 1080;
	public final static int HEIGHT = WIDTH / 16 * 9;
	public final static String Title = "Shrek Saves the Kids";
	public final Dimension gameSize = new Dimension(WIDTH, HEIGHT);
	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	//Pixel Size of the tiles used for placing the character and other computations
	public final static int PIXSIZE = 49;
	
	
	
	InputHandler IH;
	public static GameStateManager gameStateManager = new GameStateManager();
	public static Thread thread;

	static boolean gameRunning = false;

	public static void restart() {
		gameStateManager.overworld = new OverworldState();
		BattleState.shrek.rip = false;
		OverworldState.player.currentSprite = OverworldState.player.grabImage(Player.spriteSheet, 3, 1);
		Music.stopSound();
		Music.startSound("Music\\TitleScreen.wav", true);
	}
	
	//Main loop that runs the program
	public void run() {
		while (gameRunning) {
			
			tick();
			render();

			try {
				//Sets the refresh speed in milliseconds (about 60 fps)
				thread.sleep(17);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	/*
	 * Starts the game
	 */
	public synchronized void start() {
		gameRunning = true;
		thread = new Thread(this);
		thread.start();

	}

	/*
	 * Stops the game
	 */
	public synchronized static void stop() {
		gameRunning = false;
		System.exit(0);
	}

	
	
	public Game() throws IOException {

		/*
		 * A lot of JFrame preferences and initialization
		 */
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

		
		//black background
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
