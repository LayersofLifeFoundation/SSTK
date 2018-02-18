package Intro;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import GameStateManager.Game;

public class Button {
	
	Font font = new Font("Courier New", Font.PLAIN, 30);
	int actionNum;
	int x, y;
	String text;
	boolean selected = false;
	
	public Button(int i, int x, int y, String text) {
		actionNum = i;
		this.x = x;
		this.y = y;
		this.text = text;
	}
	
	public void select() {
		selected = true;
	}
	
	public void deselect() {
		selected = false;
	}
	
	public void press(){
		if(actionNum == 0) {
			Game.gameStateManager.changeState(2);
			IntroductionState.startIntroState();
		}else if(actionNum == 1) {
			Game.stop();
		}	
	}
	
	public void render(Graphics g) {
		g.setFont(font);
		g.setColor(Color.RED);
		if(selected) {
			g.setColor(Color.WHITE);
		}
		
		g.drawString(text, x, y);
	}

}
