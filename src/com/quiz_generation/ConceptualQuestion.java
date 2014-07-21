package com.quiz_generation;

import com.helper_methods.*;

import java.io.PrintWriter;
import java.util.ArrayList;

public class ConceptualQuestion extends Question
{
	private String[] answers;
	private ArrayList<String> fAnswers;
	private String correctAnswer;

	public ConceptualQuestion(String questionString, int correctAnswerIndex,
			ArrayList<String> fAnswers)
	{
		//parent constructor
		super(questionString,correctAnswerIndex);

		//immediately pull out and store the correct answer
		//remove it from rawAnswers
		this.correctAnswer = fAnswers.get(correctAnswerIndex);
		fAnswers.remove(correctAnswerIndex);

		this.fAnswers = fAnswers;
	}

	private void parseQuestion()
	{
		int nAnswers, 
		cAI, //correctAnswerIndex
		nextSlot,
		r; //used for random number

		ArrayList<Integer> openSlots = new ArrayList<>();

		//how many answers?
		//add one for the correctAnswer
		nAnswers = fAnswers.size()+1; 

		//allocate space for answers
		answers = new String[nAnswers];

		//fill the answers array with null
		for(int n=0;n<answers.length;n++)
			answers[n] = null;

		if(nAnswers == 2)
		{
			cAI = super.getCorrectAnswerIndex();
			answers[cAI] = correctAnswer;
			//we can assume that it's the first slot because
			//fAnswers should only have one remaining object
			//after the constructor because nAnswers == 2
			answers[(cAI + 1)%2] = fAnswers.get(0);
		}
		else
		{
			//choose a random slot for the correct Answer
			cAI = Randomizer.generateRandomIntegerBetween(0,nAnswers-1);

			//place it in the slot
			answers[cAI] = correctAnswer;

			//what are the open slots?
			for(int n=0;n<nAnswers;n++)
			{
				if(n != cAI)
				{
					openSlots.add(n);
				}
			}

			//place the fAnswers into the remaining slots
			for(String s : fAnswers)
			{
				//choose a random index of openSlots
				r = Randomizer.generateRandomIntegerBetween(0, openSlots.size()-1);
				//get the index it represents
				nextSlot = openSlots.get(r);
				//remove the index
				openSlots.remove(r);
				//set the fAnswer to the nextSlot
				answers[nextSlot] = s;
			}
		}

		super.setAnswers(answers);
	}
	public void print()
	{
		parseQuestion();

		super.print();
	}
	public void printToFile(PrintWriter pw, boolean addLetters)
	{
		parseQuestion();

		super.setAnswers(answers);
		super.printToFile(pw, addLetters);
	}
}















