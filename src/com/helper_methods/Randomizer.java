package com.helper_methods;

public class Randomizer
{
	/**
	 * generateRandomDoubleBetween method will generate a random number, inclusively, between the parameters
	 * provided. min must always come first; there is no error checking. It will provide a truncation
	 * to a number of decimal places as provided by the sigFigs argument.
	 * @param min
	 * @param max
	 * @param sigFigs
	 * @return
	 */
	public static double generateRandomDoubleBetween(int min, int max, int sigFigs)
	{
		if(max == min)
			return min;
		
		if(max - min < 0)
		{
			int temp = min;
			max = min;
			max = temp;
		}
		
		double r, //range
			   v; //value
		
		//calculate number of sigFigs
		sigFigs = (int) Math.pow(10,sigFigs);
		
		//calculate random number between max and min
		r = min + (Math.random() * (max - min));
		
		//truncate to sigFigs number of decimal places
		v = (int)(r * sigFigs);
		v /= sigFigs;
		
		return v;
	}
	
	public static int generateRandomIntegerBetween(int min, int max)
	{
		if(max == min)
			return min;
		
		if(max - min < 0)
		{
			int temp = min;
			max = min;
			max = temp;
		}
		
		double v; //value
	
		//calculate random number between max and min
		v = min + (Math.random() * (max - min));
	
		return (int)v;
	}
	
	public static int generateEvenIntegerBetween(int min, int max)
	{
		//if the boundaries are the same, return it
		if(max == min)
			return min;
		
		//calculate the range
		int r = max - min;
		
		//if the range is negative, flip
		if(r < 0)
		{
			int temp = min;
			max = min;
			max = temp;
			//recalculate range
			r = max - min;
		}
		//if the range is one, return the even number
		else if(r == 1)
		{
			if(min % 2 == 0)
				return min;
			else
				return max;
		}
		else
		{}
		//make sure the boundaries are even
		if(min % 2 != 0)
			min++;
		if(max % 2 != 0)
			max--;
		
		//choose an even number less than r
		int v = 2 * (int)Randomizer.generateRandomDoubleBetween(0,r/2,0);
		
		v += min;
		
		return v;
	}
}
















