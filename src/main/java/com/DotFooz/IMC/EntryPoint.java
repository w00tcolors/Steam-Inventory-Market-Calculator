package com.DotFooz.IMC;

import com.DotFooz.IMC.Static.Count;

public class EntryPoint {

	public static void main(String[] args)
	{
		Application app = new Application();
		String id = "dotfooz";
		app.ReadInv(id,"440","2",false,"Team Fortress 2 backpack");
		app.ReadInv(id, "730", "2",false,"CS:GO Inventory");
		//app.ReadInv(id, "570", "2",false);//DOTA
		app.ReadInv(id, "753", "6",true, "Steam Inventory");

		System.out.println("//============= RESULTS =============");
		System.out.println("Total cash at competitors pricing: $"+app.finalCash(Count.totalLowest));
		System.out.println("Total cash at market pricing: $"+app.finalCash(Count.totalAverage));

	}
	
}
