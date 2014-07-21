package com.quiz_generation;

import java.io.PrintWriter;
import java.util.HashMap;

public class Question
{
	private String questionString;
	private int correctAnswerIndex;
	private String[] answers;
	
	public Question(String questionString, int correctAnswerIndex)
	{
		this.questionString = questionString;
		this.correctAnswerIndex = correctAnswerIndex;
	}
	
	public void print()
	{
		System.out.println(questionString);
		
		for(String s : answers)
		{
			System.out.println(s);
		}
		System.out.println();
	}
	
	public void printToFile(PrintWriter pw, boolean addLetters)
	{
		pw.println(questionString);
		
		if(addLetters)
			addLetters(answers);
		
		for(String s : answers)
		{	
			pw.println(s);	
		}
		pw.println();
	}
	
	public void calculate(HashMap<String, Double> elements, boolean addLetters)
	{
		
	}
	
	private void addLetters(String[] answers)
	{
		char c;
		String l, regex;

		for(int n=0;n<answers.length;n++)
		{
			c = answers[n].charAt(0);

			l = c+" "+(char)(97 + n)+") ";
			
			System.out.println(l);
			
			regex = "\\"+c+"\\s";
			answers[n] = answers[n].replaceFirst(regex, l);

		}
	}
	public void setQuestionString(String questionString)
	{
		this.questionString = questionString;
	}
	public void setAnswers(String[] answers)
	{
		this.answers = answers;
	}
	public void setCorrectAnswerIndex(int correctAnswerIndex)
	{
		this.correctAnswerIndex = correctAnswerIndex;
	}
	public int getCorrectAnswerIndex()
	{
		return correctAnswerIndex;
	}
}
