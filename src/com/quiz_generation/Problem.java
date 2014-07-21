package com.quiz_generation;

import java.util.HashMap;

import com.math_parser.ArithmeticParser;

public class Problem
{
	private String problemHeader = "", problemHeaderOriginal = "",
			calculations = null;
	private HashMap<String,Double> elements = new HashMap<>();
	private QuestionSet questions = new QuestionSet();
	
	public Problem()
	{}
	
	public Problem(QuestionSet questions)
	{
		this.questions = questions;
	}
	public Problem(String problemHeader,String calculations,QuestionSet questions)
	{
		this.problemHeader = this.problemHeaderOriginal = problemHeader;
		this.calculations = calculations;
		this.questions = questions;
	}
	public void calculate(boolean addLetters)
	{
		elements.clear();
		
		if(calculations != null)
		{
			elements = ArithmeticParser.doCalculationInstructions(calculations);
			
			problemHeader = QuizGenerator.replaceVariables(elements,problemHeaderOriginal);
			
			for(Question q : questions)
			{
				q.calculate(elements,addLetters);
			}
		}
	}
	
	public void addQuestion(Question question)
	{
		questions.add(question);
	}
	public void setProblemHeader(String problemHeader)
	{
		this.problemHeader = "{"+problemHeader+"}";
	}
	public String getProblemHeader()
	{
		return problemHeader;
	}
	public QuestionSet getQuestions()
	{
		return questions;
	}
	public HashMap<String,Double> getElements()
	{
		return elements;
	}
	public String getCalculations()
	{
		return calculations;
	}
}
