package com.quiz_generation;

import java.io.*;
import java.util.*;
public class QuizReader
{
	public static Problem readQuizFromFile(Scanner keyboard, String fileName)
	{
		Scanner reader;
		File file;
		
		String line;
		char c;
		
		String questionString ="";
		int correctAnswerIndex = 0, counter = 0;
		ArrayList<String> answers = new ArrayList<>();
		QuestionSet questions = new QuestionSet();
		
		try
		{
			fileName = "\\Quiz Generator\\Problems\\Conceptual Problems\\"+fileName;
			file = new File(fileName);
			reader = new Scanner(file);
			
			while(reader.hasNext())
			{
				line = reader.nextLine();
				
				if(line.equals(""))
				{
					questions.add(new ConceptualQuestion(questionString,correctAnswerIndex,answers));
					
					//reset data tracking for the next question
					counter = 0;
					answers = new ArrayList<>();
					continue;
				}

				c = line.charAt(0);
				
				switch(c)
				{
					case '{':
					{
						questionString = line;
						break;
					}
					case '|':
					{
						questionString += "\n"+line;
						break;
					}
					case '+':
					{
						correctAnswerIndex = counter;
						answers.add(line);
						break;
					}
					case '-':
					{
						answers.add(line);
						counter++;
						break;
					}
					default:
					{
						break;
					}
				}	
			}
			
			reader.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem with opening file");
			System.exit(4);
		}
		
		return new Problem(questions);
	}
}
