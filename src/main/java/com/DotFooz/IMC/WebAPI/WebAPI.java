package com.DotFooz.IMC.WebAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.DotFooz.IMC.DataIntepreter.DataInterpreter;

/**
 * Pulls Web information for the applicaiton to parse
 * @author DotFooz
 *
 */
public class WebAPI {

	public String getMarketPage(String item, String appid)
	{
		String page = "";
		try
		{
		String url = "http://steamcommunity.com/market/listings/"
					+appid+"/"
					+item.replace("\"", "")
					.replace(" ", "%20")
					.replace("#", "%23");
		URL site = new URL(url);
		//System.out.println("URL: "+ url);
		URLConnection conn = site.openConnection();
		BufferedReader rdr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String dataTemp;
		String dataRead = "";
		while ((dataTemp = rdr.readLine())!= null)
		{
			dataRead += dataTemp;
		}
		page = dataRead;

		}catch(Exception e){e.printStackTrace();}
		return page;
	}
	
	public ArrayList<String> getInv(String playerID,String appid,String invID)
	{
		DataInterpreter di = new DataInterpreter();
		ArrayList<String> items = new ArrayList<String>();
		try
		{
			URL site = new URL("http://steamcommunity.com/id/"+playerID+"/inventory/json/"+appid+"/"+invID);
			URLConnection conn = site.openConnection();
			BufferedReader rdr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String dataTemp;
			String dataRead = "";
			while ((dataTemp = rdr.readLine())!= null)
			{
				dataRead += dataTemp;
			}
			
			items = di.readItemNamesData(dataRead);
			
			
		}catch(Exception e){e.printStackTrace();}
		return items;

	}
	
	public ArrayList<String> getHashedInv(String playerID,String appid,String invID)
	{
		DataInterpreter di = new DataInterpreter();
		ArrayList<String> items = new ArrayList<String>();
		try
		{
			URL site = new URL("http://steamcommunity.com/id/"+playerID+"/inventory/json/"+appid+"/"+invID);
			URLConnection conn = site.openConnection();
			BufferedReader rdr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String dataTemp;
			String dataRead = "";
			while ((dataTemp = rdr.readLine())!= null)
			{
				dataRead += dataTemp;
			}
			
			items = di.readHashedItemNamesData(dataRead);
			
			
		}catch(Exception e){e.printStackTrace();}
		return items;

	}
	
}
