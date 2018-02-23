package Battle;

import GameStateManager.Game;
import GameStateManager.GameState;
import GameStateManager.GameStateManager;
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
	
	public BattleState() {
		
	}

	public static void startBattleMusic() {
		Music.stopSound();
		Music.startSound("Music\\ONEPUNCH.wav", true);
	}
	
	
	public void tick() {
		shrek.tick();
		enemy.tick();
	}

	
	
	public void render(Graphics g) {
	shrek.render(g);
	enemy.render(g);
//g.drawRect(0, 0, 20, 20);
	}
	
	//g.drawRect(49, 0, 229, 19);
	
}
