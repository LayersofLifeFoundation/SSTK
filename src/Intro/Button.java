package Intro;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;

import Battle.BattleState;
import FileSystem.Save;
import GameStateManager.Game;
import Sounds.Music;

public class Button {
	
	public static Font font = new Font("Courier New", Font.PLAIN, 30);
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
			Music.stopSound();
			Game.gameStateManager.changeState(2);
			IntroductionState.startIntroState();
			try {
				Save.load();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(actionNum == 1) {
			Game.stop();
		}else if(actionNum == 2) {
			Game.gameStateManager.changeState(Game.gameStateManager.overworldStateNumber);
		}else if(actionNum == 3) {
			Save.save();
			Game.gameStateManager.changeState(Game.gameStateManager.overworldStateNumber);
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

	public void setFont(Font f) {
		font = f;
	}
}
