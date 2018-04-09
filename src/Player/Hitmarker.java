package Player;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import GameStateManager.Game;
import Player.Player;
import Sounds.Music;

public class Hitmarker {

	static int x;
	static int y;
	public static Random ran = new Random();
	public BufferedImage hit;

	
	public Hitmarker() {
		
		try {
			hit = ImageIO.read(getClass().getResource("/Hitmarker.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void spam() {
		x = Math.abs(ran.nextInt() % Game.WIDTH);
		y = Math.abs(ran.nextInt() % Game.HEIGHT);
		Music.startSound2("SFX\\Hitmarker.wav", false);
	}

	
	public void render(Graphics g) {
		if(Player.dab)
			g.drawImage(hit, x, y, 48, 48, null);			
	}
}