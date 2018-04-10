package Items;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import GameStateManager.Game;

public class ItemManager {
	/*
	 * inventory system for the game
	 */

	ArrayList<Item> items = new ArrayList<Item>();
	Font font = new Font("Courier New", Font.PLAIN, 20);
	int selected = 0;
	public static boolean inMenu = false;

	public ItemManager() {
		items.add(new Potion(2, 20));
		items.add(new Potion(1, 20));
		items.add(new Potion(0, 100));
	}

	/*
	 * uses the selected item
	 */
	public void use() {
		if (selected >= 0) {
			items.get(selected).use();
			if (items.get(selected).returnQ() < 0) {
				items.remove(items.get(selected));
				selected = 0;
				if (items.size() == 0) {
					selected = -1;
				}
			}
		}

	}

	/*
	 * moves the selected item up one
	 */
	public void MoveUp() {
		selected--;
		if (selected < 0) {
			selected = 0;
		}
	}

	/*
	 * move the selected item down one
	 */
	public void MoveDown() {
		selected++;
		if (selected >= items.size()) {
			selected = items.size() - 1;
		}
	}

	public void render(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillRect(Game.WIDTH - 240, 0, 251, Game.HEIGHT + 11);
		g.setColor(Color.WHITE);
		g.drawString("Items", Game.WIDTH - 160, 40);
		g.setFont(font);
		
		//Drawing a filled in or empty square depending on if the player is in their inventory
		if (!inMenu) {
			g.drawRect(Game.WIDTH - 235, 50 + 20 * selected, 10, 10);
		} else {
			g.fillRect(Game.WIDTH - 235, 50 + 20 * selected, 10, 10);
		}
		for (int i = 0; i < items.size(); i++) {
			g.drawString(items.get(i).returnName(), Game.WIDTH - 220, 60 + 20 * i);
		}
	}

}
