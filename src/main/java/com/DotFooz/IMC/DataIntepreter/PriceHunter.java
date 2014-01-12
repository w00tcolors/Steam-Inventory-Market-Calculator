package com.DotFooz.IMC.DataIntepreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.DotFooz.IMC.Money.CurrencyInterpreter;
import com.DotFooz.IMC.Static.Count;
import com.DotFooz.IMC.WebAPI.WebAPI;

public class PriceHunter implements Runnable{

	private String item;
	private String appid;
	private boolean printData;
	private String lowestPrice;
	private String date;
	private String averagePrice = null;
	private String numSold = null;
	


	public PriceHunter(String item, String appid, boolean printData)
	{
		this.item = item;
		this.appid = appid;
		this.printData = printData;
	}
	
	@Override
	public void run() {
			findLowestSeller();
			pullDailyAverage();
			if(printData)
			{
				if(averagePrice != null && numSold != null)
				System.out.println(item + lowestPrice+ " Average price: "+averagePrice+" out of "+numSold.replace("\"", ""));
				else
				System.out.println(item + lowestPrice);

			}
			
	}

	
	public void pullDailyAverage()
	{
		WebAPI web = new WebAPI();
		
		String page = web.getMarketPage(item, appid);
		String regex = Pattern.quote("[")
		+ "(.*?)" 
		+ Pattern.quote("\",")
		+ "(.*?)"
		+ Pattern.quote("]")
		+ "(\\W)";
		Pattern huntedString = Pattern.compile(regex);
		Matcher hunter = huntedString.matcher(page);
		while(hunter.find())
		{
			if(hunter.group(3).equals("]"))
			{
				String avgData = hunter.group(2).trim();
				String splitter[] = avgData.split(",");
				double d = Double.parseDouble(splitter[0]);
				averagePrice = "$"+splitter[0];
				numSold = splitter[1];
				Count.totalAverage.add(d);
				break;
			}

		}
	}
	
	public void findLowestSeller()
	{
		WebAPI web = new WebAPI();
		CurrencyInterpreter ci = new CurrencyInterpreter();
		
		String page = web.getMarketPage(item, appid);
		String regex = Pattern.quote("<span class=\"market_listing_price market_listing_price_without_fee\">") 
		+"(.*?)" + Pattern.quote("</span>");
		Pattern huntedString = Pattern.compile(regex);
		Matcher hunter = huntedString.matcher(page);
		while(hunter.find())
		{
			String priceData = hunter.group(1).trim();
			String pricePretty = ci.translateCurrencyType(priceData);
			double USDPrice = ci.convertToUSD(pricePretty);
			String priceConverted = "$"+USDPrice;
			Count.totalLowest.add(USDPrice);
			lowestPrice = priceConverted;
			break;
		}
	}
	
	public String getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(String lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public String getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(String averagePrice) {
		this.averagePrice = averagePrice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNumSold() {
		return numSold;
	}

	public void setNumSold(String numSold) {
		this.numSold = numSold;
	}

}
