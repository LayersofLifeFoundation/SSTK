package GameStateManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import Intro.Button;
import Items.ItemManager;

public class OptionState extends GameState {

	static ArrayList<Button> buttons = new ArrayList<Button>();
	static int selectedState = 0;
	Font font = new Font("Courier New", Font.PLAIN, 50);
	public static ItemManager itemM = new ItemManager();
	public static boolean inInv = false;

	public OptionState() {
		buttons.add(new Button(4, 10, 80, "Bag"));
		buttons.add(new Button(3, 10, 120, "Save"));
		buttons.add(new Button(2, 10, 160, "Return"));
		buttons.get(selectedState).select();

	}

	public static void pushButton() {
		if (!inInv) {
			buttons.get(selectedState).press();
		} else {
			itemM.use();
		}
	}

	public static void moveUp() {
		if (!inInv) {
			buttons.get(selectedState).deselect();
			if (selectedState == 0) {
				selectedState = buttons.size() - 1;
			} else {
				selectedState--;
			}
			buttons.get(selectedState).select();
		} else {
			itemM.MoveUp();
		}
	}

	public static void moveDown() {
		if (!inInv) {
			buttons.get(selectedState).deselect();
			if (selectedState == buttons.size() - 1) {
				selectedState = 0;
			} else {
				selectedState++;
			}
			buttons.get(selectedState).select();
		} else {
			itemM.MoveDown();
		}
	}

	public void tick() {

	}

	public void render(Graphics g) {
		g.setFont(font);
		Game.gameStateManager.overworld.render(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 250, 2000);
		g.setColor(Color.WHITE);
		g.drawString("Options", 10, 40);
		g.drawLine(250, 0, 250, 2000);
		for (Button b : buttons) {
			b.render(g);
		}
		itemM.render(g);
	}
}
