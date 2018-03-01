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
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;
import Player.Player;

import javax.imageio.ImageIO;
import Battle.*;

import FileSystem.MapRetrevial;

public class BattleState extends GameState {
	static boolean dead;
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

			pb.hpImg = ImageIO.read(getClass().getResource("/HPBar.png"));
			eb.hpImg = ImageIO.read(getClass().getResource("/HPBar.png"));

			MapRetrevial.loadEnemy(shrek, "World1");

			shrek.enemyPic = ImageIO.read(getClass().getResource(shrek.imgPath));
			shrek.hpLev = shrek.hpMax;
			shrek.hpPercent = shrek.hpLev / shrek.hpMax;

			for(int i = 0; i < 4; i++) {
				buttons.add(new Button(i, 54 * (i + 1), 540, shrek.moves.get(i).name));
			}
			
			buttons .get(selectedState).select();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void startBattleMusic() {
		Music.stopSound();
		Music.startSound("Music\\SwampBattle.wav", true);
	}

	public void loadEnemy() {
		try {
			MapRetrevial.loadEnemy(enemy, "World1");
			enemy.enemyPic = ImageIO.read(getClass().getResource(enemy.imgPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		enemy.hpLev = enemy.hpMax;
		enemy.hpPercent = enemy.hpLev / enemy.hpMax;
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

	//Executes the attack 
	public static void attack() {
		hpBound();
		useMove(shrek);
		hpBound();
		useMove(enemy);
		hpBound();
		

		System.out.println("Shrek: " + shrek.hpLev);
		System.out.println("Enemy: " + enemy.hpLev);
	}

	// uses enemy e's attack on other party
	public static void useMove(Enemy e) {
		int eChoice = Math.abs(RNG.nextInt() % 4);
		int accurate = Math.abs(RNG.nextInt() % 100);
		Moves current = null;
		Enemy offense = e;
		Enemy defense = null;
		if(e.equals(enemy)) {
			current = enemy.moves.get(eChoice);
			System.out.println("E" + enemy.hpMax);
			System.out.println(enemy.hpMax);
			defense = shrek;
		}
		else if(e.equals(shrek)) {
			current = shrek.moves.get(selectedState);
			System.out.println("S" + shrek.hpMax);
			System.out.println(shrek.hpMax);
			defense = enemy;
		}		
			message = e.name + " Used " + current.name;
			if (accurate <= current.accuracy) {
				defense.hpLev -= current.damage;
				Music.startSound2(current.sound, false);
			} else
				message += " But Missed!";
	
		System.out.println(message);
		message = "";
		//selectedState = tmp;
		
	}

	public void hpColor(Enemy e, Graphics g) {
		if (e.hpLev <= e.hpMax / 4) {
			g.setColor(Color.RED);
		} else if (e.hpLev <= e.hpMax / 2) {
			g.setColor(Color.YELLOW);
		} else {
			g.setColor(Color.GREEN);
		}
	}

	public static void hpBound() {
		shrek.hpPercent = shrek.hpLev / shrek.hpMax;
		if (shrek.hpLev > shrek.hpMax) {
			shrek.hpLev = shrek.hpMax;
		}
		if (shrek.hpLev < 0) {
			shrek.hpLev = 0;
		}
		enemy.hpPercent = enemy.hpLev / enemy.hpMax;
		if (enemy.hpLev > enemy.hpMax) {
			enemy.hpLev = enemy.hpMax;
		}
		if (enemy.hpLev <= 0) {
			enemy.hpLev = 0;
			System.out.println(enemy.name + " is a dead meme!");
			dead = true;
			enemy.hpLev = enemy.hpMax;
			Music.stopSound();
			Game.gameStateManager.changeState(Game.gameStateManager.overworldStateNumber);
			OverworldState.stateOverworldState();
		}
		if (shrek.hpLev <= 0) {
			shrek.hpLev = 0;
			System.out.println(shrek.name + " is a dead meme!");
			Player.die = true;
			Music.stopSound();
			Music.startSound("Music\\GOW1.wav", true);
			Music.startSound2("SFX\\omae.wav", false);
			Game.gameStateManager.changeState(Game.gameStateManager.overworldStateNumber);

		}
	}

	public void tick() {
		enemy.tick();
		shrek.tick();
		
	}

	public void render(Graphics g) {
		enemy.render(g);
		shrek.render(g);

		// draw buttons
		g.setFont(font);
		for (Button b : buttons) {
			Button.font = new Font("Franklin Gothic Demi Cond", Font.PLAIN, 35);
			b.render(g);
		}

		// test stuff
		g.setColor(Color.MAGENTA);
		// g.drawRect(40, 100, 350, 350); //Shrek's square
		// g.drawRect(700, 100, 350, 350); //Enema's square
		g.drawRect(40, 480, 1010, 100); // Attack Options

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
}