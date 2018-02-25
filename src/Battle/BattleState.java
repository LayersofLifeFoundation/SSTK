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
import Battle.*;

import FileSystem.MapRetrevial;

public class BattleState extends GameState{
	public BufferedImage hpImg;
	public HpBar pb = new HpBar(hpImg, 40, 50);
	public HpBar eb = new HpBar(hpImg, Game.WIDTH - 326, 50);
	
	//public Shrek shrek = new Shrek();
	public Enemy enemy = new Enemy();
	public Enemy shrek = new Enemy();
	public Moves moves = new Moves();
	public ArrayList<Enemy> nmy = new ArrayList<Enemy>();
	public ArrayList<Enemy> plr = new ArrayList<Enemy>();
	int selectedState = 0;
	
	
	public BattleState() {
		
		try {
			pb.hpImg = ImageIO.read(getClass().getResource("/HPBar.png"));
			eb.hpImg = ImageIO.read(getClass().getResource("/HPBar.png"));

			//tmp
			MapRetrevial.readEnemy(shrek, "World1", "Shrek.txt");
			MapRetrevial.readEnemy(enemy, "World1", "Fat_Yoshi.txt");
			shrek.enemyPic = ImageIO.read(getClass().getResource("/BattleShrek.png"));
			enemy.enemyPic =   ImageIO.read(getClass().getResource("/Fat_Yoshi.jpg"));
			
			System.out.println(shrek.imgPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void startBattleMusic() {
		Music.stopSound();
		Music.startSound("Music\\XffX.wav", true);
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
	
	public void tick() {
		enemy.tick();
		shrek.tick();
	}

	
	
	public void render(Graphics g) {
	enemy.render(g);
	shrek.render(g);
	//draw buttons
	
	//test stuff
	g.setColor(Color.RED);
	g.setColor(Color.MAGENTA);
	g.drawRect(40, 100, 350, 350); //Shrek's square
	g.drawRect(700, 100, 350, 350); //Enema's square
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