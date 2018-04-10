package Battle;

import GameStateManager.Game;

import GameStateManager.GameState;
import GameStateManager.GameStateManager;
import GameStateManager.OverworldState;
import Intro.Button;
import Sounds.Music;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import Player.Player;
import javax.imageio.ImageIO;

import FileSystem.MapRetrevial;

public class BattleState extends GameState {
	public BufferedImage bkg;	
	public Font font = new Font("Gill Sans Ultra Bold", Font.PLAIN, 50);
	public static Enemy enemy = new Enemy();
	public static Enemy shrek = new Enemy();
	public static ArrayList<Button> buttons = new ArrayList<Button>();
	public static int selectedState = 0;
	public static Random RNG = new Random();
	public static String message = "";

	public BattleState() {

		try {
			bkg = ImageIO.read(getClass().getResource("/W1BKG.jpg"));
			//static hp bar shells
			shrek.bar = new HpBar(ImageIO.read(getClass().getResource("/HPBar.png")), 40, 50);
			enemy.bar = new HpBar(ImageIO.read(getClass().getResource("/HPBar.png")), 700, 50);
			//initialize shrek
			nextEnemy(shrek);
			for(int i = 0; i < 4; i++) {
				buttons.add(new Button(i, 262 * (i + 1) - 208, 540, shrek.moves.get(i).name));
			}
			buttons.get(selectedState).select();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//plays battle-start sound and starts BattleState when the track finishes
	//The check for finishing the sound is in OverworldState's tick() for now
	public static String bs = "Exquisite.wav";
	public static void startSwampBattle() {
		Music.stopSound();
		Music.startSound("SFX\\" + bs, false);
		message = "A Wild " + enemy.name + " Appeared!";
		System.out.println(message);
		
	}

	//update new enemy info
	//currently called in overworldstate
	//later they may be stored in arrays for more efficiency 
	public void nextEnemy(Enemy e) {
		try {
			//"World1" is tmp this will be current map name
			MapRetrevial.loadEnemy(e, "World1");
			e.enemyPic = ImageIO.read(getClass().getResource(e.imgPath));
		} catch (IOException i) {
			i.printStackTrace();
		}
		e.hpLev = e.hpMax;
		e.hpPercent = e.hpLev / e.hpMax;
		e.rip = false;
	}
		
	//Executes the attack when button is pushed
	public static void attack() {
		//speed stat can easily be added here
		useMove(enemy);
		useMove(shrek);
	}

	// uses enemy e's attack on other party
	public static void useMove(Enemy e) {
		if(!e.rip) {
			message = "";
			int eChoice = Math.abs(RNG.nextInt() % 4);
			int accurate = Math.abs(RNG.nextInt() % 100);
			Moves current = null;
			Enemy defense = null;
			if(e.equals(enemy)) {
				current = enemy.moves.get(eChoice);
				defense = shrek;
			}
			else if(e.equals(shrek)) {
				current = shrek.moves.get(selectedState);
				defense = enemy;
			}		
			//update dialogue and use move
			message = e.name + " Used " + current.name;
			if (accurate <= current.accuracy) {
				defense.hpLev -= current.damage;
				Music.startSound2(current.sound, false);
			} else
				message += " But Missed!";
		System.out.println(message);
		//check for a kill on receiving side
		hpBound(defense);
		}
	}

	//keeps hp within appropriate range and checks for a kill
	public static void hpBound(Enemy e) {
		e.hpPercent = e.hpLev / e.hpMax;
		if (e.hpLev > e.hpMax) {
			e.hpLev = e.hpMax;
		}
		if (e.hpLev <= 0) {
			e.hpLev = 0;
			System.out.println(e.name + " is a dead meme!");
			e.rip = true;
			Music.stopSound();
			Game.gameStateManager.changeState(Game.gameStateManager.overworldStateNumber);
			OverworldState.stateOverworldState();
			if(e.equals(shrek)) {
				OverworldState.gameOver();
			}
		}	
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(bkg, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		g.setColor(Color.BLACK);
		g.fillRect(40, 480, 1010, 100); // textbox Outline
		g.setFont(font);
		for (Button b : buttons) {
			Button.font = new Font("Franklin Gothic Demi Cond", Font.PLAIN, 35);
			b.render(g);
		}	
		g.drawImage(shrek.enemyPic, 40, 100, 350, 350, null);
		g.drawImage(enemy.enemyPic, 700, 100, 350, 350, null);
		drawHp(shrek, g);
		drawHp(enemy, g);
	}
	
	public void drawHp(Enemy e, Graphics g) {
		g.setColor(Color.GREEN);
		g.setFont(e.font);
		g.drawString(e.name, e.bar.x, e.bar.y - 7);
		g.drawImage(e.bar.hpImg, e.bar.x, e.bar.y, 294, 24, null);
		hpColor(e, g);
		g.fillRect(49 + e.bar.x, e.bar.y, (int) (230 * e.hpPercent), 19);
	}
	
	//changes the color of the hp Bar according to health level 
		public void hpColor(Enemy e, Graphics g) {
			if (e.hpLev <= e.hpMax / 4) {
				g.setColor(Color.RED);
			} else if (e.hpLev <= e.hpMax / 2) {
				g.setColor(Color.YELLOW);
			} else {
				g.setColor(Color.GREEN);
			}
		}
	
    // move button select
	public static void moveRight() {
		buttons.get(selectedState).deselect();
		Music.startSound2("SFX\\Hitmarker.wav", false);
		if (selectedState == 3) {
			selectedState = 0;
		} else {
			selectedState++;
		}
		buttons.get(selectedState).select();
	}
	public static void moveLeft() {
		buttons.get(selectedState).deselect();
		Music.startSound2("SFX\\Hitmarker.wav", false);
		if (selectedState == 0) {
			selectedState = 3;
		} else {
			selectedState--;
		}
		buttons.get(selectedState).select();
	}
}