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

public class Enemy {
		
		//enemy hp
		public static BufferedImage hpImg;
		public static int barX = 229;
		public static int barY = 19;
		public static double hpMax = 10;
		public static double hpLev = 5;
		public static double hpPercent;
		
		
		public static BufferedImage enemyPic;
		public static Font font = new Font("Gill Sans Ultra Bold", Font.PLAIN, 30);
		public static String name = "Enemy";
		
		public Enemy() {
			
			try {
				eb.hpImg = ImageIO.read(getClass().getResource("/HPBar.png"));
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
		
		public void setImage(BufferedImage b) {
			enemyPic = b;
		}
		public void setName(String s) {
			name = s;
		}
		public void setM1() {
			
		}
		
}



	