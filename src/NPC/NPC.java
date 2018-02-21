package NPC;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import GameStateManager.Game;
import GameStateManager.OverworldState;

public class NPC {
	
	private static final int OGPIX = 48;
	public int x;
	public int y;
	int sRow;
	int sCol;
	String dialog;
	BufferedImage sprite;
	BufferedImage spriteSheet;
	
	public NPC() {
		try {
			spriteSheet = ImageIO.read(getClass().getResource("/Sprite_Sheet.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static BufferedImage grabImage(BufferedImage img, int row, int col){
		BufferedImage image = img.getSubimage( (col - 1) * OGPIX, (row - 1) * OGPIX, OGPIX, OGPIX);
		return image;
	}
	
	public void setX(int n) {
		x = n;
	}
	
	public void setY(int n) {
		y = n;
	}
	
	public void setR(int n) {
		sRow = n;
	}
	
	public void setC(int n) {
		sCol = n;
	}
	
	public void setSprite() {
		sprite = grabImage(spriteSheet, sRow, sCol);
	}
	
	public void setText(String s) {
		dialog = s;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, Game.WIDTH / 2  - Game.WIDTH / 2 % 49 + (x - OverworldState.player.returnX()) * Game.PIXSIZE, Game.HEIGHT / 2 - Game.HEIGHT / 2 % 49 + (y - OverworldState.player.returnY()) * Game.PIXSIZE, null);
		
	}
	
	
	
	

}
