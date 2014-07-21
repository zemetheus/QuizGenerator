package com.quiz_generation;

import java.text.DecimalFormat;
import java.util.HashMap;
import com.helper_methods.Randomizer;
import com.math_parser.ArithmeticParser;

public class MultipleChoiceQuestion extends Question
{
	private String[] answers = new String[5];
	private String correctAnswerString, factorString, units, originalQuestionString;
	
	public MultipleChoiceQuestion(String questionString, String correctAnswerString,
								 String units, String factorString)
	{
		super(questionString,Randomizer.generateRandomIntegerBetween(0, 4));
		
		this.originalQuestionString = questionString;
		this.correctAnswerString = correctAnswerString;
		this.factorString = factorString;
		this.units = units;
	}
	
	public void calculate(HashMap<String, Double> elements, boolean addLetters)
	{
		double correctAnswer = ArithmeticParser.evaluateExpression(elements, correctAnswerString),
				factor = Double.parseDouble(factorString);
		
		DecimalFormat df = new DecimalFormat("0.00E0");
		
		double d; //temp variable in for loop
		int cai = Randomizer.generateRandomIntegerBetween(0,4);
		String c;
		
		for(int n=0;n<answers.length;n++)
		{
			//generate answer
			d = correctAnswer * Math.pow(10,(n-cai)/factor);
			c = (n == cai) ? "+ " : "- ";
			answers[n] = c + df.format(d) +" "+units;
		}
		
		super.setAnswers(answers);
		
		String questionString = QuizGenerator.replaceVariables(elements,originalQuestionString);
		super.setQuestionString(questionString);
	}
}
