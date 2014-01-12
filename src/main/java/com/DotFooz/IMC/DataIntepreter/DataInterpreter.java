package com.DotFooz.IMC.DataIntepreter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.DotFooz.IMC.Money.CurrencyInterpreter;
import com.DotFooz.IMC.Static.Count;
import com.DotFooz.IMC.WebAPI.WebAPI;

/**
 * Regex interpreter, mainly used for parsing information grabbed by the WebAPI
 * @author DotFooz
 *
 */
public class DataInterpreter {

	public String getItemPrice(String item, String appid)
	{
		WebAPI web = new WebAPI();
		CurrencyInterpreter ci = new CurrencyInterpreter();
		
		String page = web.getMarketPage(item, appid);
		String regex = 
				Pattern.quote("<span class=\"market_listing_price market_listing_price_without_fee\">") +
				"(.*?)" + Pattern.quote("</span>");
		Pattern huntedString = Pattern.compile(regex);
		Matcher hunter = huntedString.matcher(page);
		while(hunter.find())
		{
			String priceData = hunter.group(1).trim();
			String pricePretty = ci.translateCurrencyType(priceData);
			String priceConverted = "$"+ci.convertToUSD(pricePretty);
			Count.totalLowest.add(ci.convertToUSD(pricePretty));
			return priceConverted;
			
		}
			return "Price not found!";
	}

	public ArrayList<String> readItemNamesData(String data)
	{
		ArrayList<String> temp = new ArrayList<String>();
		String regex = Pattern.quote("market_name") + "(.*?)" + Pattern.quote(",") + "(.*?)" + Pattern.quote("\"marketable\":") + "(\\d)";
		
		Pattern huntedString = Pattern.compile(regex);
		Matcher hunter = huntedString.matcher(data);
		
		while(hunter.find())
		{
			int i = Integer.parseInt(hunter.group(3));
			if(i == 1)
			temp.add(hunter.group(1).substring(2));
		}
		
		return temp;
	}
	
	public ArrayList<String> readHashedItemNamesData(String data)
	{
		ArrayList<String> temp = new ArrayList<String>();
		String regex = Pattern.quote("market_hash_name") + "(.*?)" + Pattern.quote(",") + "(.*?)" + Pattern.quote("\"marketable\":") + "(\\d)";
		
		Pattern huntedString = Pattern.compile(regex);
		Matcher hunter = huntedString.matcher(data);
		
		while(hunter.find())
		{
			int i = Integer.parseInt(hunter.group(3));
			if(i == 1)
			temp.add(hunter.group(1).substring(2));
		}
		
		return temp;
	}
	
}
