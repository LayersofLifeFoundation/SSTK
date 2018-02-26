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

public class BattleState extends GameState{
	static boolean dead;
	public BufferedImage hpImg;
	public HpBar pb = new HpBar(hpImg, 40, 50);
	public HpBar eb = new HpBar(hpImg, 700, 50);
	public Font font = new Font("Gill Sans Ultra Bold", Font.PLAIN, 50);
	public static Enemy enemy = new Enemy();
	public static Enemy shrek = new Enemy();
	public ArrayList<Enemy> nmy = new ArrayList<Enemy>();
	public ArrayList<Enemy> plr = new ArrayList<Enemy>();
	public static ArrayList<Button> moves = new ArrayList<Button>();
	public static int selectedState = 0;
	public static Random RNG = new Random();
	
	
	
	public BattleState() {
	
		try {
			pb.hpImg = ImageIO.read(getClass().getResource("/HPBar.png"));
			eb.hpImg = ImageIO.read(getClass().getResource("/HPBar.png"));

			//tmp
			MapRetrevial.readEnemy(shrek, "World1", true);
		
			shrek.enemyPic = ImageIO.read(getClass().getResource(shrek.imgPath));
			shrek.hpLev = shrek.hpMax;
		
			
			moves.add(new Button(0,  262   - 208, 540, shrek.m1));
			moves.add(new Button(1,  262*2 - 208, 540, shrek.m2));
			moves.add(new Button(2,  262*3 - 208, 540, shrek.m3));
			moves.add(new Button(3,  262*4 - 208, 540, shrek.m4));
			moves.get(selectedState).select();
			
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
			MapRetrevial.readEnemy(enemy, "World1" , false);
			enemy.enemyPic = ImageIO.read(getClass().getResource(enemy.imgPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		enemy.hpLev = enemy.hpMax;
	}
	
	//move button select
	public static void moveRight() {
		moves.get(selectedState).deselect();
		if(selectedState == 3) {
			selectedState = 0;
		}else {
			selectedState++;
		}
		moves.get(selectedState).select();
	}
	public static void moveLeft() {
		moves.get(selectedState).deselect();
		if(selectedState == 0) {
			selectedState = 3;
		}else {
			selectedState--;
		}
		moves.get(selectedState).select();
	}
	
	public static void attack() {
		int choice = RNG.nextInt() % 4;
		Music.startSound2("SFX\\Hitmarker.wav", false);
		playerAttack();
		enemyAttack();
		
		hpBound();
		System.out.println("Shrek: " + shrek.hpLev);
		System.out.println("Enemy: " + enemy.hpLev);
	}
	public static void playerAttack() {
		if(selectedState == 0) {
			System.out.println(shrek.name + " Used " + shrek.m1);
			enemy.hpLev -= shrek.m1d;
			//Music.startSound2(shrek.m1s, false);
		}
		if(selectedState == 1) {
			System.out.println(shrek.name + " Used " + shrek.m2);
			enemy.hpLev -= shrek.m2d;
			//Music.startSound2(shrek.m2s, false);
		}
		if(selectedState == 2) {
			System.out.println(shrek.name + " Used " + shrek.m3);
			enemy.hpLev -= shrek.m3d;
			//Music.startSound2(shrek.m2s, false);
		}
		if(selectedState == 3) {
			System.out.println(shrek.name + " Used " + shrek.m4);
			enemy.hpLev -= shrek.m4d;
			//Music.startSound2(shrek.m2s, false);
		}
	}
	public static void enemyAttack() {
		int choice = Math.abs(RNG.nextInt() % 4);
		int accurate = Math.abs(RNG.nextInt() % 100);	
		if(choice == 0) {
			System.out.println(enemy.name + " Used " + enemy.m1);
			if(accurate <= enemy.m1a) {
			shrek.hpLev -= enemy.m1d;
			Music.startSound2(enemy.m1s, false);
			}else 
				System.out.println(enemy.name + " missed!");
		}
		if(choice == 1) {
			System.out.println(enemy.name + " Used " + enemy.m2);
			if(accurate <= enemy.m2a) {
			shrek.hpLev -= enemy.m2d;
			Music.startSound2(enemy.m2s, false);
		}else 
			System.out.println(enemy.name + " missed!");
		}
		if(choice == 2) {
			System.out.println(enemy.name + " Used " + enemy.m3);
			if(accurate <= enemy.m3a) {
			shrek.hpLev -= enemy.m3d;
			Music.startSound2(enemy.m3s, false);
			}else 
				System.out.println(enemy.name + " missed!");
		}
		if(choice == 3) {
			System.out.println(enemy.name + " Used " + enemy.m4);
			if(accurate <= enemy.m3a) {
			shrek.hpLev -= enemy.m4d;
			Music.startSound2(enemy.m4s, false);
			}else 
				System.out.println(enemy.name + " missed!");
		}
	}
	
	public void hpColor(Enemy e, Graphics g) {
		if(e.hpLev <= e.hpMax / 4) {
			g.setColor(Color.RED);
		}else if(e.hpLev <= e.hpMax / 2) {
			g.setColor(Color.YELLOW);	
		}else{
			g.setColor(Color.GREEN);
		}
	}
	public static void hpBound() {
		shrek.hpPercent = shrek.hpLev / shrek.hpMax;
		if(shrek.hpLev > shrek.hpMax) {
			shrek.hpLev = shrek.hpMax;
		}
		if(shrek.hpLev < 0) {
			shrek.hpLev = 0;
		}
		enemy.hpPercent = enemy.hpLev / enemy.hpMax;
		if(enemy.hpLev > enemy.hpMax) {
			enemy.hpLev = enemy.hpMax;
		}
		if(enemy.hpLev <= 0) {
			enemy.hpLev = 0;
			System.out.println(enemy.name + " is a dead meme!");
			dead = true;
			enemy.hpLev = enemy.hpMax;
			Music.stopSound();
			Game.gameStateManager.changeState(Game.gameStateManager.overworldStateNumber);
			OverworldState.stateOverworldState();
		}
		if(shrek.hpLev <= 0) {
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
	//draw buttons
	g.setFont(font);
	for(Button b: moves) {
		b.render(g);
	}
	
	//test stuff
	g.setColor(Color.RED);
	g.setColor(Color.MAGENTA);
	//g.drawRect(40, 100, 350, 350); //Shrek's square
	//g.drawRect(700, 100, 350, 350); //Enema's square
	g.drawRect(40, 480, 1010, 100); //Attack Options
	
	//player stuff
	g.drawImage(shrek.enemyPic, 40, 100, 350, 350, null);
	g.setColor(Color.GREEN);
	g.setFont(shrek.font);
	g.drawString(shrek.name, pb.x, pb.y - 7);
	g.drawImage(pb.hpImg, pb.x, pb.y, 294, 24, null);
	hpColor(shrek, g);
	g.fillRect(49 + pb.x, pb.y, (int) (230 * shrek.hpPercent), 19);
	
	//enemy stuff
	g.drawImage(enemy.enemyPic, 700, 100, 350, 350, null);
	g.setColor(Color.GREEN);
	g.setFont(enemy.font);
	g.drawString(enemy.name, eb.x, eb.y - 7);
	g.drawImage(eb.hpImg, eb.x, eb.y, 294, 24, null);
	hpColor(enemy, g);
	
	g.fillRect(49+eb.x, eb.y, (int) (230 * enemy.hpPercent), 19);
}
}