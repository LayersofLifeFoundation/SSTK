package GameStateManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import Intro.Button;
import Items.ItemManager;

public class OptionState extends GameState {
	/*
	 * State for accessing inventory and options
	 */

	static ArrayList<Button> buttons = new ArrayList<Button>();
	static int selectedState = 0;
	Font font = new Font("Courier New", Font.PLAIN, 50);
	public static ItemManager itemM = new ItemManager();
	public static boolean inInv = false;

	public OptionState() {
		/*
		 * adds the buttons in the menu
		 */
		buttons.add(new Button(4, 10, 80, "Bag"));
		buttons.add(new Button(3, 10, 120, "Save"));
		buttons.add(new Button(2, 10, 160, "Return"));
		buttons.get(selectedState).select();

	}

	/*
	 * interacts with the given button
	 */
	public static void pushButton() {
		if (!inInv) {
			buttons.get(selectedState).press();
		} else {
			itemM.use();
		}
	}

	/*
	 * Moves the selected button up.
	 * If button is at the top it is set to the lowest position
	 */
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

	/*
	 * Moves the selected button down.
	 * if button previously selected is already at the bottom the then the selected button is the first one 
	 */
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
		
		//rendering the overworld as a background
		Game.gameStateManager.overworld.render(g);
		
		//Left side of inventory
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 250, 2000);
		g.setColor(Color.WHITE);
		g.drawString("Options", 10, 40);
		g.drawLine(250, 0, 250, 2000);
		
		//draws the buttons
		for (Button b : buttons) {
			b.render(g);
		}
		
		//right side of the inventory
		itemM.render(g);
	}
}
