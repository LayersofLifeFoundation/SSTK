package Intro;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;

import Battle.BattleState;
import FileSystem.Save;
import GameStateManager.Game;
import GameStateManager.OptionState;
import Sounds.Music;

public class Button {
	
	/*
	 * class to create buttons to get user input
	 */
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
	
	/*
	 * Set if player has highlighted this button
	 */
	public void select() {
		selected = true;
	}
	
	/*
	 * deselect the button
	 */
	public void deselect() {
		selected = false;
	}
	
	/*
	 * Executes the given code for the function
	 */
	public void press(){
		if(actionNum == 0) {
			//loads a save
			Music.stopSound();
			Game.gameStateManager.changeState(2);
			IntroductionState.startIntroState();
			try {
				//System.out.println(Game.gameStateManager.overworld.encounter.get(0).direc);
				Save.load();
				System.out.println(Game.gameStateManager.overworld.encounter.get(0).direc);

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(actionNum == 1) {
			//closes the game
			Game.stop();
		}else if(actionNum == 2) {
			//changes to overworld state
			Game.gameStateManager.changeState(Game.gameStateManager.overworldStateNumber);
		}else if(actionNum == 3) {
			//saves the game
			Save.save();
			Game.gameStateManager.changeState(Game.gameStateManager.overworldStateNumber);
		}else if(actionNum == 4) {
			//selects the inventory menu in options
			OptionState.inInv = true;
			OptionState.itemM.inMenu = true;
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
