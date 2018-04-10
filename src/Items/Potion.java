package Items;

import Battle.BattleState;
import GameStateManager.OverworldState;

public class Potion extends Item{
	
	/*
	 * Item for Every Potion Type
	 */

	String name;
	int value;
	String description;
	int q;
	
	
	public Potion(int t, int quantity) {
		if(t == 0) {
			value = 30;
			name = "Small Potion";
		}else if(t == 1) {
			value = 60;
			name = "Medium Potion";
		}else if(t == 2) {
			value = 100;
			name = "Large Potion";
		}
		description = "Restores " + value + " health points";
		q = quantity;
	}
	
	/*
	 *Uses the item
	 */
	public void use() {
		BattleState.shrek.hpLev += value;
		System.out.println(BattleState.shrek.hpLev);
	
		q--;
	}
	
	/*
	 *returns the name of the given item
	 */
	public String returnName() {
		return name;
	}
	
	/*
	 * Returns the Quantity of the item
	 */
	public int returnQ() {
		System.out.println(q);
		return q;
		
	}
	
	
	
	
}
