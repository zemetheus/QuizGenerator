package com.quiz_generation;

import java.io.*;
import java.util.*;

public class QuizGenerator
{
	public static void generateQuiz(Scanner keyboard, Problem problem)
	{
		boolean toRandomize, addLetters; 
		int nVersions;
		String label, fileName;
		QuestionSet questions;
		QuizConfiguration qc;
		
		qc = getQuizConfiguration(keyboard);
		
		questions = problem.getQuestions();
		toRandomize = qc.isToRandomize();
		nVersions = qc.getnVersions();
		label = qc.getLabel();
		fileName = qc.getFileName();
		addLetters = qc.getAddLetters();
		
		File file;
		PrintWriter pw = null;
		
		char c;
		String header;
		
		try
		{
			file = new File(System.getProperty("user.dir")+"\\Quiz Generator\\Quizzes\\"+fileName);
			pw = new PrintWriter(file);
			
			for(int n=0;n<nVersions;n++)
			{
				c = (char)(65 + n);
				header = "=="+label+" Version "+c+"==";
				
				pw.println(header);
				pw.println("<quiz display=simple>");
				
				//calculates all new elements
				problem.calculate(addLetters);
				
				pw.println(problem.getProblemHeader());
				pw.println();
				
				if(n!=0 && toRandomize)
				{
					questions.randomizeQuestions();
				}
				
				for(Question q : questions)
				{
					q.printToFile(pw,addLetters);
				}
				
				pw.println("</quiz>");
			}
			
			pw.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Quiz Generator Error 1");
			System.exit(4);
		}
	}
	public static String replaceVariables(HashMap<String,Double> elements, String line)
	{
		ArrayList<String> variables = scanForVariables(line);
		
		for(String s : variables)
		{
			if(elements.get(s) == null)
			{
				System.out.println("Variable \""+s+"\" was not found.");
				System.exit(3);
			}
			
			line = line.replace("$"+s,elements.get(s).toString());
		}
		
		return line;
	}
	
	private static ArrayList<String> scanForVariables(String line)
	{
		char c;
		
		boolean isVariable = false;
		int flag = 0;
		
		ArrayList<String> variables = new ArrayList<>();
		
		for(int n=0;n<line.length();n++)
		{
			c = line.charAt(n);
			
			if(c == '$')
			{
				isVariable = true;
				flag = n;
				continue;
			}
			
			if(isVariable && !Character.isAlphabetic(c) && c != '_')
			{
				variables.add(line.substring(flag+1,n));
				isVariable = false;
			}
		}
		
		return variables;
	}
	public static QuizConfiguration getQuizConfiguration(Scanner keyboard)
	{
		String fileName, label, answer;
		int nVersions;
		boolean toRandomize, addLetters = false, toContinue = true;
		
		System.out.print("Enter a fileName to print to file: ");
		fileName = keyboard.nextLine();
		
		toRandomize = getToRandomize(keyboard);
		
		System.out.println("The first quiz will not have the order of the questions randomized.");
		System.out.print("Enter the number of versions you would like (<=26): ");
		nVersions = keyboard.nextInt();
		keyboard.nextLine(); //consumption
		
		System.out.println("Each label will prepend \"Version [Letter]\".");
		System.out.print("Please enter a label for the quizzes: ");
		label = keyboard.nextLine()+" ";
		
		
		
		while(toContinue)
		{
			System.out.println("Would you like to add letters to the answers? (y/n): ");
			answer = keyboard.nextLine().toLowerCase();
			
			if(answer.equals("y"))
			{
				addLetters = true;
				toContinue = false;
			}
			else if(answer.equals("n"))
			{
				addLetters = false;
				toContinue = false;
			}
			else
			{
				System.out.println("Please enter a valid response.");
			}
		}
		return new QuizConfiguration(fileName,label,nVersions,toRandomize, addLetters);
	}
	private static boolean getToRandomize(Scanner keyboard)
	{
		String answer;
		
		System.out.print("Would you like to randomize the question order? (y/n): ");
		answer = keyboard.nextLine().toLowerCase();
		
		switch(answer)
		{
		case "y":
		{
			return true;
		}
		case "n":
		{
			return false;
		}
		default:
		{
			return getToRandomize(keyboard);
		}
		}
	}
}











