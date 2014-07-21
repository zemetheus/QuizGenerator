package com.math_parser;

import com.quiz_generation.*;

import java.util.ArrayList;
import java.util.HashMap;

import com.quiz_generation.ProblemFileReader;

public class ParserDebugger
{
	public static void debugProblem(String fileName)
	{	
		Problem problem;

		//debug header
		System.out.println("\n\n-------------");
		System.out.println("DEBUG RESULTS");
		System.out.println("-------------");

		//get the problem from the fileName
		problem = ProblemFileReader.readProblemFromXMLFile(fileName);

		System.out.println("These are the calculations to be done.");
		unpackCalculations(problem);

		//complete calculations
		problem.calculate(false);

		printElements(problem);
	}
	private static void printElements(Problem problem)
	{
		HashMap<String,Double> elements;
		double value;

		System.out.println("\nThese are the calculated values for the variables.");

		elements = problem.getElements();

		for(String variable : elements.keySet())
		{
			value = elements.get(variable);
			
			System.out.println(variable+": "+value);
		}
	}
	private static void unpackCalculations(Problem problem)
	{
		ArrayList<String> expressions = new ArrayList<>();
		String line, calcs;

		calcs = problem.getCalculations();

		int prevFlag = -1;

		for(int n=0;n<calcs.length();n++)
		{
			if(calcs.charAt(n) == ';')
			{
				line = calcs.substring(prevFlag+1,n);
				prevFlag = n;
				expressions.add(line);
			}
		}

		for(String s : expressions)
		{
			System.out.println(s);
		}
	}
}

















