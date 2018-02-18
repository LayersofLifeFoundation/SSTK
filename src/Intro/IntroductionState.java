package Intro;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import GameStateManager.GameState;

public class IntroductionState extends GameState{
	BufferedImage shrek;
	static Clip audioClip;
	static AudioInputStream audioStream;
	
	
	public IntroductionState() {
		try {
			shrek = ImageIO.read(getClass().getResource("/Shrek.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void stop() {
		audioClip.close();
		try {
			audioStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void startIntroState() {
		File audioFile = new File("Music\\SIntro.wav");
		try {
			audioStream = AudioSystem.getAudioInputStream(audioFile);
			AudioFormat format = audioStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.open(audioStream);
			audioClip.start();
			
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void render(Graphics g) {
		g.drawImage(shrek, 100, 0, null);
	}
}
