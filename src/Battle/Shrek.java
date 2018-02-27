package Battle;

	import GameStateManager.Game;
	import GameStateManager.GameState;
	import GameStateManager.GameStateManager;
import Intro.Button;
import Sounds.Music;

	import java.awt.Color;
	import java.awt.Font;
	import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusEvent;
	import java.awt.image.BufferedImage;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.ConcurrentModificationException;
	import javax.imageio.ImageIO;
public class Shrek {
	

		
		 //player hp
		static Font font = new Font("Gill Sans Ultra Bold", Font.PLAIN, 30);
		//public static BufferedImage hpImg;
		public static int barX = 230;
		public static int barY = 19;
		public static double hpMax = 200;
		public static double hpLev = 200;
		public static double hpPercent;
	//	public ArrayList<Button> moves = new ArrayList<Button>();
		static Font mFont = new Font("Courier New", Font.PLAIN, 30);
		
		
		public static BufferedImage shrekPic;
		
		
		//bar.add(new HpBar());
		public Shrek() {
			hpLev = 329;
			
		
		}

		//public static HpBar pb = new HpBar(hpImg, 40, 50);
		
		

		public void tick() {
			hpPercent = hpLev / hpMax;
			if(hpLev > hpMax) {
				hpLev = hpMax;
			}

		}
		
		//temp test func
		//true attacks enemy
		
		
		public static void render(Graphics g) {
			if(hpLev <= hpMax / 4) {
				g.setColor(Color.RED);
			}else if(hpLev <= hpMax / 2) {
				g.setColor(Color.YELLOW);	
			}else{
				g.setColor(Color.GREEN);
			}
			//g.drawImage(pb.hpImg, pb.x, pb.y, 294, 24, null);
			//g.fillRect(49+pb.x, pb.y, (int) (230 * hpPercent), 19);
			
			g.setFont(mFont);
			
			g.drawImage(shrekPic, 40, 100, 350, 350, null);
		
	}
		
		public void setImage(BufferedImage b) {
			shrekPic = b;
		}


}

