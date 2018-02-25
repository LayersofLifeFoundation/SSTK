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

import FileSystem.MapRetrevial;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.imageio.ImageIO;

import FileSystem.MapRetrevial;
import Maps.Link;
import Maps.MovementMap;
import NPC.NPC;
import Player.Player;
import Sounds.Music;
import TextMap.TextMap;
import java.io.*;
import java.util.ArrayList;
public class Enemy {
		
		//enemy hp
		public static BufferedImage hpImg;
		public static int barX = 229;
		public static int barY = 19;
	
		public static double hpLev = 70;
		public static double hpPercent;
		
		public static int enemyNum = 0;
		
		
		
		//read stuff for enemy/ player
		public static Font font = new Font("Gill Sans Ultra Bold", Font.PLAIN, 30);
		public static BufferedImage enemyPic;
		public static String imgPath;
		public static String name = "L";
		public static double hpMax;
		public static String m1;
		public static int   m1d;
		public static int   m1a;
		public static String m2;
		public static int   m2d;
		public static int   m2a;
		public static String m3;
		public static int   m3d;
		public static int   m3a;
		public static String m4;
		public static int   m4d;
		public static int   m4a;
		
		
		
		

		
		
		public Enemy() {
			//hpLev = hpMax;
			try {
				eb.hpImg = ImageIO.read(getClass().getResource("/HPBar.png"));
				
			
				System.out.println(imgPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		public static HpBar eb = new HpBar(hpImg, Game.WIDTH - 326, 50);
		
		public void tick() {
			hpPercent = hpLev / hpMax;
			if(hpLev > hpMax) {
				hpLev = hpMax;
			}	
			
			//System.out.println(Moves.moves.get(0));
		}
		
		public static void render(Graphics g) {
			g.drawImage(eb.hpImg, eb.x, eb.y, 294, 24, null);
		//	g.drawImage(enemyPic, 700, 100, 350, 350, null);
			
			if(hpLev <= hpMax / 4) {
				g.setColor(Color.RED);
			}else if(hpLev <= hpMax / 2) {
				g.setColor(Color.YELLOW);	
			}else{
				g.setColor(Color.GREEN);
			}
			g.fillRect(49+eb.x, eb.y, (int) (230 * hpPercent), 19);
			
			g.setColor(Color.GREEN);
			g.setFont(font);
			g.drawString(name, eb.x, eb.y - 7);
	}
		
		public void setImage(String s) {
			imgPath = s;
		}
		public void setName(String s) {
			name = s;
		}
		public void setHP(double i) {
			hpMax = i;
		}
		public void setM1(String s) {
			m1 = s;
		}
		public void setM1d(int i) {
			m1d = i;
		}
		public void setM1a(int i) {
			m1a = i;
		}
		public void setM2(String s) {
			m2 = s;
		}
		public void setM2d(int i) {
			m2d = i;
		}
		public void setM2a(int i) {
			m2a = i;
		}
		public void setM3(String s) {
			m3 = s;
		}
		public void setM3d(int i) {
			m3d = i;
		}
		public void setM3a(int i) {
			m3a = i;
		}
		public void setM4(String s) {
			m4 = s;
		}
		public void setM4d(int i) {
			m4d = i;
		}
		public void setM4a(int i) {
			m4a = i;
		}
}



	