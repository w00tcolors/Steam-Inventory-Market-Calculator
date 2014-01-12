package com.DotFooz.IMC;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.DotFooz.IMC.DataIntepreter.PriceHunter;
import com.DotFooz.IMC.WebAPI.WebAPI;

public class Application {
	
	
	
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
