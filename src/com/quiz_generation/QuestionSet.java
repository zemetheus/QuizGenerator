package com.quiz_generation;

import com.helper_methods.*;

import java.util.*;

public class QuestionSet implements Iterable<Question>
{
	private ArrayList<Question> questions;
	
	public QuestionSet()
	{
		questions = new ArrayList<>();
	}
	public void randomizeQuestions()
	{
		ArrayList<Question> randQuestions= new ArrayList<>();
		int randSlot;
		
		while(!questions.isEmpty())
		{
			randSlot = Randomizer.generateRandomIntegerBetween(0,questions.size()-1);
			randQuestions.add(questions.get(randSlot));
			questions.remove(randSlot);
		}
		
		questions = randQuestions;
	}
	public Question get(int n)
	{
		return questions.get(n);
	}
	public void clear()
	{
		questions.clear();
	}
	public void add(Question question)
	{
		questions.add(question);
	}
	public void nQuestions()
	{
		System.out.println(questions.size());
	}
	public Iterator<Question> iterator()
	{
		return questions.iterator();
	}
}
