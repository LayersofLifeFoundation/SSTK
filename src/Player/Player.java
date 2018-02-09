package Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import GameStateManager.Game;
import GameStateManager.OverworldState;


public class Player {
	
	
	static int x = 1;
	static int y = 1;
	static Font font  = new Font("Courier New", Font.PLAIN, Game.PIXSIZE);
	public static BufferedImage spriteSheet;
	public static BufferedImage currentSprite;

	public Player()
	{
		try {
			spriteSheet = ImageIO.read(getClass().getResource("/Sprite_Sheet.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static BufferedImage grabImage(BufferedImage img, int col, int row) 
	{
		BufferedImage image = img.getSubimage( (col - 1) * Game.PIXSIZE, (row - 1) * Game.PIXSIZE, Game.PIXSIZE, Game.PIXSIZE);
		return image;
	}
		

	/*
	 * Functions for the four directions that the player can move
	 */
	public void moveUp() {
		if(OverworldState.movMap.canMoveTo(x, y - 1)) {
			y -= 1;
			System.out.println("X: " + x + " Y : " + y);
			currentSprite = grabImage(spriteSheet, 1, 4);
		}
	
	}
	public void moveDown() {
		if(OverworldState.movMap.canMoveTo(x, y + 1)) {
			y += 1;
			System.out.println("X: " + x + " Y : " + y);
			currentSprite = grabImage(spriteSheet, 1, 3);
		}
	
	}
	public void moveLeft() {
		if(OverworldState.movMap.canMoveTo(x - 1, y)) {
			x -= 1;
			System.out.println("X: " + x + " Y : " + y);
			currentSprite = grabImage(spriteSheet, 1, 2);
		}
	}
	public void moveRight() {
		if(OverworldState.movMap.canMoveTo(x + 1,  y)) {
			x += 1;
			System.out.println("X: " + x + " Y : " + y);
			currentSprite = grabImage(spriteSheet, 1, 1);
		}
	}
	
	/*
	 * draws player
	 */
	public static void render(Graphics g) {
		g.setFont(font);
		g.setColor(Color.white);
		g.drawImage(currentSprite, x * Game.PIXSIZE, y * Game.PIXSIZE,null);
	}
	
	
	/*
	 * getter and setter functions for the variables x and y
	 */
	public int returnX() {
		return x;
	}
	public int returnY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
