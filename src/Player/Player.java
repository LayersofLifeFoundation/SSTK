package Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import GameStateManager.Game;
import GameStateManager.OverworldState;


public class Player {
	
	
	static int x = 1;
	static int y = 1;
	public static BufferedImage spriteSheet;
	public static BufferedImage currentSprite;
	public int tim = 100;
	//returns the sprite at the specified index of the sprite sheet
	public static BufferedImage grabImage(BufferedImage img, int row, int col){
		BufferedImage image = img.getSubimage( (col - 1) * Game.PIXSIZE, (row - 1) * Game.PIXSIZE, Game.PIXSIZE, Game.PIXSIZE);
		return image;
	}
		
	public Player(){
		try {
			spriteSheet = ImageIO.read(getClass().getResource("/Sprite_Sheet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		currentSprite = grabImage(spriteSheet, 8, 2);
	}	
	/*
	 * Functions for the four directions that the player can move
	 */
	public void moveUp() throws InterruptedException {
		if(OverworldState.movMap.canMoveTo(x, y - 1)) {
			y -= 1;
			System.out.println("X: " + x + " Y : " + y);
			animateUp();
		}
	
	}
	public void moveDown() throws InterruptedException {
		if(OverworldState.movMap.canMoveTo(x, y + 1)) {
			y += 1;
			System.out.println("X: " + x + " Y : " + y);
			animateDown();
			animateDie();
		}
	
	}
	public void moveLeft() throws InterruptedException {
		if(OverworldState.movMap.canMoveTo(x - 1, y)) {
			x -= 1;
			System.out.println("X: " + x + " Y : " + y);
			animateLeft();
		}
	}
	public void moveRight() throws InterruptedException {
		if(OverworldState.movMap.canMoveTo(x + 1,  y)) {
			x += 1;
			System.out.println("X: " + x + " Y : " + y);
			animateRight();
		}
	}
	
	public void animateRight() throws InterruptedException{
		for(int i = 1; i <= 7; i++) {
			currentSprite = grabImage(spriteSheet, 1, i);
			TimeUnit.MILLISECONDS.sleep(tim);
		}
		currentSprite = grabImage(spriteSheet, 3, 1);
	}

	public void  animateLeft() throws InterruptedException{
		for(int i = 1; i <= 7; i++) {
			currentSprite = grabImage(spriteSheet, 2, i);
			TimeUnit.MILLISECONDS.sleep(tim);
		}
		currentSprite = grabImage(spriteSheet, 3, 1);
	}
	
	public void  animateDown() throws InterruptedException{
		for(int i = 0; i <= 1; i++) {
			currentSprite = grabImage(spriteSheet, 3, 2);
			TimeUnit.MILLISECONDS.sleep(tim + 50);
			currentSprite = grabImage(spriteSheet, 3, 1);
			TimeUnit.MILLISECONDS.sleep(tim + 50);
		}
	}
	
	public void    animateUp() throws InterruptedException{
		for(int i = 0; i <= 1; i++) {
			currentSprite = grabImage(spriteSheet, 4, 1);
			TimeUnit.MILLISECONDS.sleep(tim + 50);
			currentSprite = grabImage(spriteSheet, 4, 2);
			TimeUnit.MILLISECONDS.sleep(tim + 50);
		}
		TimeUnit.MILLISECONDS.sleep(tim + 50);
		currentSprite = grabImage(spriteSheet, 3, 1);
	}
	
	public void punchRight() throws InterruptedException{
		for(int i = 1; i <= 4; i++) {
			currentSprite = grabImage(spriteSheet, 5, i);
			TimeUnit.MILLISECONDS.sleep(tim);
		}
	}
	
	public void punchLeft() throws InterruptedException{
		for(int i = 1; i <= 4; i++) {
			currentSprite = grabImage(spriteSheet, 6, i);
			TimeUnit.MILLISECONDS.sleep(tim);
		}
	}
	
	public void animateDie() throws InterruptedException{
		for(int i = 1; i <= 10; i++) {
			currentSprite = grabImage(spriteSheet, 7, i);
			TimeUnit.MILLISECONDS.sleep(tim);
		}
		for(int j = 0; j < 15; j++) {
			for(int i = 11; i <= 13; i++) {
				currentSprite = grabImage(spriteSheet, 7, i);
				TimeUnit.MILLISECONDS.sleep(tim);
			}
			
		}
	}
	
	public void Dab() {
		while(true) {
		currentSprite = grabImage(spriteSheet, 8, 2);
		currentSprite = grabImage(spriteSheet, 8, 3);
		}
	}
	public void tick() {
		
	}
	/*
	 * draws player
	 */
	public static void render(Graphics g) {
		g.drawImage(currentSprite, x * Game.PIXSIZE, y * Game.PIXSIZE + 20,null);
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
