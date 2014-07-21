package com.math_parser;

import com.helper_methods.*;

import java.util.*;

public class FunctionIdentifier
{
	public static double evaluateFunction(HashMap<String,Double> elements, String line)
	{
		double result;
		String functionName = "", argString = "";
		String[] args;
		
		//we need extract the name of the function.
		
		//scan for the parenthesis, skipping the %
		for(int n=1;n<line.length();n++)
		{
			if(line.charAt(n) == '(')
			{
				functionName = line.substring(1,n);
				argString = line.substring(n);
				
			}
		}
		args = packageArgs(argString);
		result = identifyFunction(functionName,args);
		
		return result;
	}
	
	private static double identifyFunction(String functionName, String[] args)
	{
		double result;
		
		switch(functionName)
		{
		case "randInt":
		{
			int a = Integer.parseInt(args[0]),
				b = Integer.parseInt(args[1]);
			result = Randomizer.generateRandomIntegerBetween(a,b);
			break;
		}
		case "randEvenInt":
		{
			int a = Integer.parseInt(args[0]),
				b = Integer.parseInt(args[1]);
			
			result = Randomizer.generateEvenIntegerBetween(a,b);
			break;
		}
		case "truncate":
		{
			double d = Double.parseDouble(args[0]);
			
			result = UtilityMethods.truncate(d);
			break;
		}
		case "sin":
		{
			double d = Double.parseDouble(args[0]);
			
			result = Math.sin(d);
			break;
		}
		case "cos":
		{
			double d = Double.parseDouble(args[0]);
			
			result = Math.cos(d);
			break;
		}
		default:
			result = 0;
			break;
		}
		
		return result;
	}
	private static String[] packageArgs(String line)
	{
		ArrayList<String> args = new ArrayList<>();
		String[] argArray;
		int prevFlag = 0;
		char c;
		
		//we do not need to parse the first parenthesis
		for(int n=1;n<line.length();n++)
		{
			c = line.charAt(n);
			if(c == ',' || c == ')')
			{
				args.add(line.substring(prevFlag+1,n));
				prevFlag = n;
			}
		}
		
		argArray = new String[args.size()];
		
		args.toArray(argArray);
		
		return argArray;
	}
}

























