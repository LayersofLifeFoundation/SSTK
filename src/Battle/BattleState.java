package Battle;

import GameStateManager.Game;
import GameStateManager.GameState;
import GameStateManager.GameStateManager;
import Intro.Button;
import Sounds.Music;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import javax.imageio.ImageIO;

public class BattleState extends GameState{
	
	public Shrek shrek = new Shrek();
	public Enemy enemy = new Enemy();
	Font font = new Font("Courier New", Font.PLAIN, 30);
	static int selectedState = 0;
	public ArrayList<Button> moves = new ArrayList<Button>();
	public BattleState() {
		moves.add(new Button(0, 500, 400, "Start"));
		moves.add(new Button(1,500, 440, "Exit"));
	}

	public static void startBattleMusic() {
		Music.stopSound();
		Music.startSound2("Music\\XffX.wav", true);
	}
	
	
	public void tick() {
		shrek.tick();
		enemy.tick();
	}

	
	
	public void render(Graphics g) {
	shrek.render(g);
	enemy.render(g);
	
	//draw buttons
	g.setFont(font);
	g.setColor(Color.RED);
	for(Button b: moves) {
		b.render(g);
	
	}
	
	//g.drawRect(49, 0, 229, 19);
	
}
}