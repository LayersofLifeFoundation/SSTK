package Intro;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

import GameStateManager.GameState;
import Sounds.Music;

public class InputState extends GameState{
	/*
	 * Game state for the main menu
	 */
	Font font  = new Font("Courier New", Font.PLAIN, 80);
	static int selectedState = 0;
	
	
	static ArrayList<Button> buttons = new ArrayList<Button>();
	public InputState() {
		buttons.add(new Button(0, 500, 400, "Start"));
		buttons.add(new Button(1,500, 440, "Exit"));
		buttons.get(selectedState).select();
		Music.startSound("Music\\TitleScreen.wav", true);
	}
	
	/*
	 * interacts with the selected button
	 */
	public static void pushButton() {
		buttons.get(selectedState).press();
	}
	
	/*
	 * moves the selected button up
	 */
	public static void moveUp() {
		buttons.get(selectedState).deselect();
		if(selectedState == 0) {
			selectedState = 1;
		}else {
			selectedState--;
		}
		buttons.get(selectedState).select();
	}
	
	/*
	 * moves the selected button down
	 */
	public static void moveDown() {
		buttons.get(selectedState).deselect();
		if(selectedState == 1) {
			selectedState = 0;
		}else {
			selectedState++;
		}
		buttons.get(selectedState).select();
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(font);
		g.drawString("Shrek Saves The Kids", 80, 300);
		for(Button b: buttons) {
			b.render(g);
		}
	}
}
