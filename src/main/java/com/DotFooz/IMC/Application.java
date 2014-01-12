package com.DotFooz.IMC;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.DotFooz.IMC.DataIntepreter.PriceHunter;
import com.DotFooz.IMC.WebAPI.WebAPI;

/**
 * Primary execution class
 * @author DotFooz
 *
 */
public class Application {
	
	
	/**
	 * Main argument. Reads a specific steam inventory
	 * 
	 * @param playerID The ID of the user's steamcommunity profile (E.G. https://steamcommunity.com/id/dotfooz id = dotfooz)
	 * @param appid The appid of the steam game (E.G. TF2 appid = 440)
	 * @param invID Variable i can't truly explain fully, mainly discovered through trial and error
	 * @param forceHashed Some inventories enforce a has on their items, so this is sometimes necessary for some inventories
	 * @param nick Arbitrary String, used for naming the scan so you can remember which it is.
	 */
	public void ReadInv(String playerID, String appid, String invID, boolean forceHashed, String nick)
	{
		System.out.println("//============= SCANNING: "+nick+" =============");
		WebAPI web = new WebAPI();
		
		Set<String> rawItems = new HashSet<String>();
		
		if(forceHashed)
		{
			rawItems.addAll(web.getHashedInv(playerID,appid,invID));
		}
		else
		{
			rawItems.addAll(web.getHashedInv(playerID,appid,invID));
			rawItems.addAll(web.getInv(playerID, appid, invID));
		}
		
		ArrayList<String> items = new ArrayList<String>();
		items.addAll(rawItems);
		ArrayList<Thread> threads = new ArrayList<Thread>();
		
		for(int i = 0; i < items.size(); i++)
		{
			Thread t = new Thread(new PriceHunter(items.get(i),appid,true));
			t.start();
			threads.add(t);

		}
		
		for(Thread t : threads)
		{
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Done! Items: "+items.size());
	}
	
	/**
	 * Small loop function that adds all the values of the items in the inventories together
	 * 
	 * @param total
	 * @return The totaled cash from all items in the inventory
	 */
	public double finalCash(ArrayList<Double> total)
	{
		double d = 0;
		for(int i = 0; i < total.size(); i++)
		{
			d += total.get(i);
		}
		return d;
	}
}
