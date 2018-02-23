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
public class Shrek {
	

		
		//player hp
		public static BufferedImage hpImg;
		public static int barX = 229;
		public static int barY = 19;
		public static double hpMax = 1003;
		public static double hpLev = 500;
		public static double hpPercent;
		
		//bar.add(new HpBar());
		public Shrek() {
			hpLev = 329;
			try {
				pb.hpImg = ImageIO.read(getClass().getResource("/HPBar.png"));
				//pb2.hpImg = pb.hpImg;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public static HpBar pb = new HpBar(hpImg, 0, Game.HEIGHT - 24);
		

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
			g.drawImage(pb.hpImg, pb.x, pb.y, 294, 24, null);
			g.fillRect(49+pb.x, pb.y, (int) (229 * hpPercent), 19);

			
			
		
	}
}

