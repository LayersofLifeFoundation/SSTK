package Intro;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


import GameStateManager.GameState;
import Maps.Link;
import Sounds.Music;
import Sounds.Music;

public class IntroductionState extends GameState{
	BufferedImage shrek;
	
	/*
	 * State the plays the intro
	 */
	
	public IntroductionState() {
		try {
			shrek = ImageIO.read(getClass().getResource("/Shrek.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void stopIntroState() {
		Music.stopSound();
	}
	
	public static void startIntroState() {
		Music.startSound("Music\\SIntro.wav", false);
	}
	
	
	
	public void render(Graphics g) {
		g.drawImage(shrek, 100, 0, null);
	}
}