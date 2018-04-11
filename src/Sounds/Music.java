package Sounds;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import GameStateManager.GameStateManager;

public class Music {
	public static Clip audioClip;
	public static AudioInputStream audioStream;
	public static boolean boosted = false;
	public static File audioFile;
	public static AudioInputStream audioStream2;
	public static Clip audioClip2;
	public static File audioFile2;
	static FloatControl gainControl; 
	static FloatControl gainControl2; 
		   
	//start a looping sound
	public static void startSound(String path, boolean loop) {
		audioFile = new File(path);
		try {
			audioStream = AudioSystem.getAudioInputStream(audioFile);
			AudioFormat format = audioStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.open(audioStream);
			gainControl =  (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
			//Lower Volume
			gainControl.setValue(-10.0f);
			audioClip.start();
			if(loop) {
			audioClip.loop(-1);
			}
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
			}catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public static void startSound2(String path, boolean loop) {
		audioFile2 = new File(path);
		try {
			audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
			AudioFormat format = audioStream2.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			audioClip2 = (Clip) AudioSystem.getLine(info);
			audioClip2.open(audioStream2);
			gainControl2 =  (FloatControl) audioClip2.getControl(FloatControl.Type.MASTER_GAIN);
			//Lower Volume
			gainControl2.setValue(-15.0f);
			audioClip2.start();
			if(loop) {
			audioClip2.loop(-1);
			}
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	//stop a looping sound
	public static void stopSound() {
		audioClip.close();
		try {
			audioStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void stopSound2() {
		audioClip2.close();
		try {
			audioStream2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//changes current track to specified track at time position of previous track
	public static void boost() {
		boosted = true;
		String newPath = "";
		if(audioFile.getName().equals("All_Star_Chip.wav")) {
			newPath = "Music\\XAll_Star_ChipX.wav";
		}
		long time = audioClip.getMicrosecondPosition();
		stopSound();
		startSound(newPath, true);
		audioClip.setMicrosecondPosition(time);	
	}
	 
	public static void norm() {
		boosted = false;
		String newPath = "";
		if(audioFile.getName().equals("XAll_Star_ChipX.wav")) {
			newPath = "Music\\All_Star_Chip.wav";
		}
		long time = audioClip.getMicrosecondPosition();
		stopSound();
		startSound(newPath, true);
		audioClip.setMicrosecondPosition(time);	
	}
}
