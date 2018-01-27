package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class InputHandler implements KeyListener {

	public void keyPressed(KeyEvent a) {
		int keyCode = a.getKeyCode();
		String keyChar = KeyEvent.getKeyText(a.getKeyChar());
	
	}

	public void keyReleased(KeyEvent a) {
		int keyCode = a.getKeyCode();

	}

	public void keyTyped(KeyEvent d) {

	}


}