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
		public BufferedImage hpImg;
		public int barX = 229;
		public int barY = 19;
	
		public double hpLev;
		public double hpMax;
		public double hpPercent;
				
		//read stuff for enemy/ player
		public Font font = new Font("Gill Sans Ultra Bold", Font.PLAIN, 30);
		public BufferedImage enemyPic;
		public String imgPath;
		public String name;
		public static ArrayList<Moves> moves = new ArrayList<Moves>();
		
		
				
		public void tick() {		

		}
		
		public void render(Graphics g) {
		
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
}
	



	