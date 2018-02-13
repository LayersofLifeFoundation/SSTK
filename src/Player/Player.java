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
	
	
	static int x = 12;
	static int y = 5;
	public static BufferedImage spriteSheet;
	public static BufferedImage currentSprite;
	int noAnime = 0;
	public static boolean isMoving = false;
	boolean moveUp = false;
	int upAnime = 0;
	int downAnime = 0;
	boolean moveDown = false;
	int rightAnime = 0;
	boolean moveRight = false;
	int leftAnime = 0;
	boolean moveLeft = false;
	
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
			//e.printStackTrace();
		}
		currentSprite = grabImage(spriteSheet, 8, 1);
	}	
	/*
	 * Functions for the four directions that the player can move
	 */
	public void moveUp() throws InterruptedException {
		if(!moveUp && !isMoving) {
			if(OverworldState.movMap.canMoveTo(x, y - 1)) {
				y -= 1;
				System.out.println("X: " + x + " Y : " + y);
				moveUp = true;
			}
		}
	}
	public void moveDown() throws InterruptedException {
		if(!moveDown && !isMoving) {
			if(OverworldState.movMap.canMoveTo(x, y + 1)) {
				y += 1;
				System.out.println("X: " + x + " Y : " + y);
				moveDown = true;
			}
		}
	}
	public void moveLeft() throws InterruptedException {
		if(!moveLeft && !isMoving) {
			if(OverworldState.movMap.canMoveTo(x - 1, y)) {
				x -= 1;
				System.out.println("X: " + x + " Y : " + y);
				moveLeft = true;
			}
		}
	}
	public void moveRight() throws InterruptedException {
		if(!moveRight && !isMoving) {
			if(OverworldState.movMap.canMoveTo(x + 1,  y)) {
				x += 1;
				System.out.println("X: " + x + " Y : " + y);
				moveRight = true;
			}
		}
	}
	
	public void animateRight(int i) throws InterruptedException{
		
		if(i >= 0 && i < 2) {
			isMoving = true;
			currentSprite = grabImage(spriteSheet, 1, 1);
		}else if(i >= 2 && i < 4) {
			currentSprite = grabImage(spriteSheet, 1, 2);
		}else if(i >= 4 && i < 6){
			currentSprite = grabImage(spriteSheet, 1, 3);
		}else if(i >= 6 && i < 8){
			currentSprite = grabImage(spriteSheet, 1, 4);
		}else if(i >= 8 && i < 10){
			currentSprite = grabImage(spriteSheet, 1, 5);
		}else if(i >= 10 && i < 12){
			currentSprite = grabImage(spriteSheet, 1, 6);
		}else if(i >= 12 && i < 14){
			currentSprite = grabImage(spriteSheet, 1, 7);
		}else {
			moveRight = false;
			rightAnime = 0;
			isMoving = false;
		
		}	
	}

	public void  animateLeft(int i) throws InterruptedException{
		if(i >= 0 && i < 2) {
			isMoving = true;
			currentSprite = grabImage(spriteSheet, 2, 1);
		}else if(i >= 2 && i < 4){
			currentSprite = grabImage(spriteSheet, 2, 2);
		}else if(i >= 4 && i < 6){
			currentSprite = grabImage(spriteSheet, 2, 3);
		}else if(i >= 6 && i < 8){
			currentSprite = grabImage(spriteSheet, 2, 4);
		}else if(i >= 8 && i < 10){
			currentSprite = grabImage(spriteSheet, 2, 5);
		}else if(i >= 10 && i < 12){
			currentSprite = grabImage(spriteSheet, 2, 6);
		}else if(i >= 12 && i < 14){
			currentSprite = grabImage(spriteSheet, 2, 7);
		}else {
			moveLeft = false;
			leftAnime = 0;
			isMoving = false;
		
		}		
	}
	
	public void  animateDown(int i) throws InterruptedException{
		if(i >= 0 && i < 9) {
			isMoving = true;
			currentSprite = grabImage(spriteSheet, 3, 1);
		}else if(i >= 9 && i < 18){
			currentSprite = grabImage(spriteSheet, 3, 2);
		}else {
			//currentSprite = grabImage(spriteSheet, 3, 1);
			moveDown = false;
			downAnime = 0;
			isMoving = false;
			
		}
	}
	
	public void animateUp(int i) throws InterruptedException{
		if(i >= 0 && i < 9) {
			isMoving = true;
			currentSprite = grabImage(spriteSheet, 4, 1);
		}else if(i >= 9 && i < 18){
			currentSprite = grabImage(spriteSheet, 4, 2);
		}else {
			//currentSprite = grabImage(spriteSheet, 3, 1);
			moveUp = false;
			upAnime = 0;
			isMoving = false;
		}
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
		if(moveUp) {
			System.out.println("Hi");
			try {
				animateUp(upAnime);
				//isMoving = true;
				System.out.println(upAnime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			upAnime++;
		}
		
		if(moveDown) {
			try {
				animateDown(downAnime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			downAnime++;
		}
		
		if(moveLeft) {
			try {
				animateLeft(leftAnime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			leftAnime++;
		}
		
		if(moveRight) {
			try {
				animateRight(rightAnime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rightAnime++;
		}
		
		//If player is idle, _____________
		if(!isMoving) {
			if(noAnime > 8) {
			currentSprite = grabImage(spriteSheet, 3, 1);
			noAnime = 0;
		}else 
			noAnime++;
		}
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
