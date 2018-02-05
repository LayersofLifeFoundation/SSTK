package Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import GameStateManager.OverworldState;

public class Player {
	
	static int x = 1;
	static int y = 1;
	static Font font  = new Font("Courier New", Font.PLAIN, 20);
	
	public void moveUp() {
		if(OverworldState.movMap.canMoveTo(x, y - 1)) {
			y--;
		}
	
	}
	public void moveDown() {
		if(OverworldState.movMap.canMoveTo(x, y + 1)) {
			y++;
		}
	
	}
	public void moveLeft() {
		if(OverworldState.movMap.canMoveTo(x - 1, y)) {
			x--;
		}
	}
	public void moveRight() {
		if(OverworldState.movMap.canMoveTo(x + 1, y)) {
			x++;
		}
	}
	
	public static void render(Graphics g) {
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("X", (x * 20), (y * 20) + 20);
	}
	
	public int returnX() {
		return x;
	}
	public int returnY() {
		return y;
	}

}
