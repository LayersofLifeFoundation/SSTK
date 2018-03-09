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
	public BufferedImage hpImg;
	public HpBar pb = new HpBar(hpImg, 40, 50);
	public HpBar eb = new HpBar(hpImg, 700, 50);
	public Font font = new Font("Gill Sans Ultra Bold", Font.PLAIN, 50);
	public static Enemy enemy = new Enemy();
	public static Enemy shrek = new Enemy();
	public static ArrayList<Button> buttons = new ArrayList<Button>();
	public static int selectedState = 0;
	public static Random RNG = new Random();
	public static String message;

	public BattleState() {

		try {
			bkg = ImageIO.read(getClass().getResource("/W1BKG.jpg"));
			//load hp bar outlines
			pb.hpImg = ImageIO.read(getClass().getResource("/HPBar.png"));
			eb.hpImg = ImageIO.read(getClass().getResource("/HPBar.png"));
			//initialize shrek
			loadEnemy(shrek);

			for(int i = 0; i < 4; i++) {
				buttons.add(new Button(i, 262 * (i + 1) - 208, 540, shrek.moves.get(i).name));
			}
			
			buttons.get(selectedState).select();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String bs = "omae.wav";
	//plays battle-start sound and start battle state when the track finishes
	//The check for finishing the sound is in OverworldState's tick() for now
	public static void startSwampBattleMusic() {
		Music.stopSound();
		Music.startSound("SFX\\" + bs, false);
	}

	//update new enemy info
	 // currently preloads enemy in overworldstate
	public void loadEnemy(Enemy e) {
		try {
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
			Enemy offense = e;
			Enemy defense = null;
			//who is attacking who
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
		//check for a kill on recieving side
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

	public void render(Graphics g) {
		g.drawImage(bkg, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		g.setColor(Color.BLACK);
		g.fillRect(40, 480, 1010, 100); // Attack Outline
		enemy.render(g);
		shrek.render(g);

		// draw buttons
		g.setFont(font);
		for (Button b : buttons) {
			Button.font = new Font("Franklin Gothic Demi Cond", Font.PLAIN, 35);
			b.render(g);
		}

		// test stuff
		
		 //g.drawRect(40, 100, 350, 350); //Shrek's square
		 //g.drawRect(700, 100, 350, 350); //Enemy's square
		

		//Redundant player/enemy rendering. will improve later 
		// player stuff
		g.drawImage(shrek.enemyPic, 40, 100, 350, 350, null);
		g.setColor(Color.GREEN);
		g.setFont(shrek.font);
		g.drawString(shrek.name, pb.x, pb.y - 7);
		g.drawImage(pb.hpImg, pb.x, pb.y, 294, 24, null);
		hpColor(shrek, g);
		g.fillRect(49 + pb.x, pb.y, (int) (230 * shrek.hpPercent), 19);

		// enemy stuff
		g.drawImage(enemy.enemyPic, 700, 100, 350, 350, null);
		g.setColor(Color.GREEN);
		g.setFont(enemy.font);
		g.drawString(enemy.name, eb.x, eb.y - 7);
		g.drawImage(eb.hpImg, eb.x, eb.y, 294, 24, null);
		hpColor(enemy, g);
		g.fillRect(49 + eb.x, eb.y, (int) (230 * enemy.hpPercent), 19);
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