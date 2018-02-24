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
	public Moves moves = new Moves();
	
	static int selectedState = 0;
	
	public BattleState() {
		
	}

	public static void startBattleMusic() {
		Music.stopSound();
		Music.startSound2("Music\\XffX.wav", true);
	}
	
	
	public void tick() {
		shrek.tick();
		enemy.tick();
		//moves.tick();
	}

	
	
	public void render(Graphics g) {
	shrek.render(g);
	enemy.render(g);
	//draw buttons
	
	g.setColor(Color.RED);
	g.setColor(Color.MAGENTA);
	g.drawRect(40, 100, 350, 350); //Shrek's square
	g.drawRect(700, 100, 350, 350); //Enema's square
	g.drawRect(40, 480, 1010, 100); //Attack Options
	
	
	//g.drawRect(49, 0, 229, 19);
	
}
}