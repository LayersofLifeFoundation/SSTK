package Maps;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import GameStateManager.OverworldState;

public class Link {
	
	static Clip audioClip;
	static AudioInputStream audioStream;
	String nextTrack;
	
	int x,y,toX,toY;
	String linkMovement, linkText;
	/*
	 * Provides a tile to link different maps
	 */	
	public static void startSound(String path) {
			File audioFile = new File(path);
			try {
				audioStream = AudioSystem.getAudioInputStream(audioFile);
				AudioFormat format = audioStream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				audioClip = (Clip) AudioSystem.getLine(info);
				audioClip.open(audioStream);
				audioClip.start();
				audioClip.loop(-1);
				
			} catch (UnsupportedAudioFileException | IOException e) {
				e.printStackTrace();
			}catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
	//startSound("Music\\onepunch.wav", true); 
	
	
	
	
	

	/*
	 * A bunch of functions to set variables
	 */
	
	public void setX(int x) {
		this.x = x;
	}
	public void setToX(int x) {
		toX = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setToY(int y) {
		toY = y;
	}
	public void setLinkMovement(String s) {
		linkMovement = s;
	}
	public void setLinkText(String s) {
		linkText = s;
	}
	public void setNextTrack(String s) {
		nextTrack = s;
	}
	
	/*
	 * Changes map if player is on given x and y
	 */
	public void tick() {
		if(OverworldState.player.returnX() == x && OverworldState.player.returnY() == y) {
			try {
				audioClip.close();
				audioStream.close();
				
				startSound(nextTrack); 
				OverworldState.player.setX(toX);
				OverworldState.player.setY(toY);
				
				
				OverworldState.movMap.loadMap(linkMovement);
				OverworldState.textMap.loadMap(linkText);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void render(Graphics g) {
		
	}
	
}
