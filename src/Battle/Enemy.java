package Battle;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy {
		
		//current hp level
		public double hpLev;
		public double hpMax;
		public double hpPercent;
		public Font font = new Font("Gill Sans Ultra Bold", Font.PLAIN, 30);
		public BufferedImage enemyPic;
		public String imgPath;
		public String name;
		public ArrayList<Moves> moves = new ArrayList<Moves>();
		public boolean rip = false;
		public HpBar bar;
				
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


	