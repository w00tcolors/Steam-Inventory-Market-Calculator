package com.DotFooz.IMC.Money;

/**
 * Small class that converts all currencies found on the market to USD (In need of better code)
 * @author DotFooz
 *
 */
public class CurrencyInterpreter {

	/**
	 * Converts the HTML codes to readable names.
	 * @param currencyString
	 * @return An interpretable currency name
	 */
	public String translateCurrencyType(String currencyString)
	{
		return currencyString.replace("&#82;&#36;", "Brazilian Equals;;")
				.replace("&#36;", "$;;")
				.replace("&#163;", "�;;")
				.replace("p&#1091;&#1073;", ";;rubles")
				.replace("&#8364;",";;�")
				.replace(",", ".")
				.replace("-", "0");
	}
	
	/**
	 * Actually converts the monetary values
	 * @param currencyString String to interpret for currency
	 * @return
	 */
	public double convertToUSD(String currencyString)
	{
		String[] splitter = currencyString.split(";;");
		if(splitter.length > 1){
		if(!splitter[1].equalsIgnoreCase("�") && !splitter[1].equalsIgnoreCase("rubles."))
		{
			double d = Double.parseDouble(splitter[1]);
			switch(splitter[0])
			{
			case "$":
				return d;
				
			case "Brazilian Equals":
				return d*0.44;
				
			case "�":
				return d*1.59;
			
			default:
				return d;
			}
		}else{
			double d = Double.parseDouble(splitter[0]);
			switch(splitter[1])
			{
			case "rubles.":
				return d*0.031;
				
			case "�":
				return d*1.33;
			
			default:
				return d;
			}

		}
	}else{return 0.000;}
	}
	
}