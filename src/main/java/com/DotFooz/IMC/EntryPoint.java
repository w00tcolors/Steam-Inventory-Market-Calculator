package com.DotFooz.IMC;

import com.DotFooz.IMC.Static.Count;

/**
 * Main entrypoint class, with example code usage
 * @author DotFooz
 *
 */
public class EntryPoint {

	public static void main(String[] args)
	{
		Application app = new Application();
		String id = "dotfooz"; //Pulling this id: https://steamcommunity.com/id/dotfooz
		app.ReadInv(id,"440","2",false,"Team Fortress 2 backpack");//Gets the TF2 backpack for the player
		app.ReadInv(id, "730", "2",false,"CS:GO Inventory");//Gets CS:GO inventory
		//app.ReadInv(id, "570", "2",false);//DOTA
		app.ReadInv(id, "753", "6",true, "Steam Inventory");//Gets the steam inventory. Notice that hash must be forced for this application

		System.out.println("//============= RESULTS =============");
		System.out.println("Total cash at competitors pricing: $"+app.finalCash(Count.totalLowest));//Prints competitors pricing
		System.out.println("Total cash at market pricing: $"+app.finalCash(Count.totalAverage));//Prints market pricing

	}
	
}
