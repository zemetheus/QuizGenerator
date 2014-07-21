package com.main;

import java.io.IOException;
import java.util.*;

import com.environment_management.EnvironmentManager;
import com.math_parser.ParserDebugger;
import com.quiz_generation.*;

public class Main
{
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Welcome."); 
		
		try
		{
			if(EnvironmentManager.establishEnvironment())
			{
				getOption(keyboard);
			}
			else
			{
				System.out.println("There has been a problem with the Environment. Existing program.");
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		keyboard.close();
	}
	
	public static void getOption(Scanner keyboard)
	{
		String option;
		char c;
		
		System.out.println("Please select an option from the menu.");
		System.out.println("a : Generate a quiz from an existing quiz.");
		System.out.println("b : Generate a quiz from an xml file.");
		System.out.println("c : Debug an XML file.");
		System.out.println("exit : exit the program");
		System.out.print(">> ");
		
		option = keyboard.nextLine().toLowerCase().trim();
		
		if(option.equals("exit"))
		{
			System.out.println("Good bye.");
			System.exit(0);
		}
		
		c = option.charAt(0);
		
		switch(c)
		{
		case 'a':
		{
			generateProblemFromQuiz(keyboard);
			break;
		}
		case 'b':
		{
			generateProblemFromFile(keyboard);
			break;
		}
		case 'c':
		{
			debugProblem(keyboard);
			break;
		}
		default:
		{
			System.out.println("That was not a valid option.");
			getOption(keyboard);
		}
		}
	}
	public static void generateProblemFromQuiz(Scanner keyboard)
	{
		String fileName;
		
		fileName = getFileName(keyboard);
		
		Problem problem = QuizReader.readQuizFromFile(keyboard, fileName);
		
		QuizGenerator.generateQuiz(keyboard, problem);
	}
	public static void generateProblemFromFile(Scanner keyboard)
	{
		String fileName;
		
		fileName = getFileName(keyboard);
		
		Problem problem = ProblemFileReader.readProblemFromXMLFile(fileName);
		
		QuizGenerator.generateQuiz(keyboard, problem);
	}
	public static void debugProblem(Scanner keyboard)
	{
		String fileName;
		
		fileName = getFileName(keyboard);
		
		ParserDebugger.debugProblem(fileName);
	}
	public static String getFileName(Scanner keyboard)
	{
		String fileName, ext;
		
		System.out.println("Please verify that your file has the .txt extension.");
		System.out.print("Please enter the file name: ");
		fileName = keyboard.nextLine();
		
		//get extension:
		ext = fileName.substring(fileName.length()-4);
		
		if(ext.equals(".txt"))
		{
			return fileName;
		}
		else
		{
			System.out.println("Your file name was invalid.");
			return getFileName(keyboard);
		}
	}
}




