package com.math_parser;

import java.util.ArrayList;
import java.util.HashMap;

public class ArithmeticParser
{
	private static final String[] orderOfOperations = {"^","*","/","+","-"};
	
	public static HashMap<String,Double> doCalculationInstructions(String line)
	{
		HashMap<String,Double> elements = new HashMap<>();
		
		String expression, variable;
		double result;
		char c;
		int prevFlag = -1;
		ArrayList<String> variables = new ArrayList<>();
		ArrayList<String> expressions = new ArrayList<>();
		
		for(int n=0;n<line.length();n++)
		{
			c = line.charAt(n);
			
			switch(c)
			{
				case '=':
				{
					variable = line.substring(prevFlag+1,n).trim();
					variables.add(variable);
					prevFlag = n;
					break;
				}
				case ';':
				{
					expression = line.substring(prevFlag+1,n).trim();
					expressions.add(expression);
					prevFlag = n;
					break;
				}
			}
		}
		
		for(int n=0;n<variables.size();n++)
		{
			variable = variables.get(n);
			expression = expressions.get(n);
			
			result = ArithmeticParser.evaluateExpression(elements,expression);
			
			elements.put(variable,result);
		}
		
		return elements;
	}
	public static double evaluateExpression(HashMap<String,Double> elements, String line)
	{
		ArrayList<String> tokens = new ArrayList<>();
		ArrayList<Integer> flags = new ArrayList<>();		
		
		//remove whitespace
		line = removeWhitespace(line);		
		
		//separate the operations from the variables
		separateIntoTokens(tokens, flags, line);
		
		if(tokens.size() == 1)
		{
			tokens.set(0, evaluateToken(elements,tokens,0).toString());
		}
		else
		{
			//for each of the operations, calculate
			for(String operation : orderOfOperations)
			{
				for(int n=0;n<tokens.size();n++)
				{
					String token = tokens.get(n);
					if(token.equals(operation))
					{
						doOperation(elements,tokens,n,token);
						n = 0;
					}
				}
			}
		}	
		return Double.parseDouble(tokens.get(0));
	}
	private static Double doOperation(HashMap<String, Double> elements, 
			ArrayList<String> tokens, int index,String token)
	{
		double a = evaluateToken(elements,tokens,index-1),
			   b = evaluateToken(elements,tokens,index+1);
		
		Double result = null;
		
		switch(token)
		{
			case "^":
			{
				result = Math.pow(a, b);
				break;
			}
			case "*":
			{
				result =  a * b;
				break;
			}
			case "/":
			{
				result =  a / b;
				break;
			}
			case "+":
			{
				result =  a + b;
				break;
			}
			case "-":
			{
				result =  a - b;
				break;
			}
			default:
			{
				System.out.println("error");
				System.exit(0);
			}
		}
		
		replace(tokens,index,result);
		
		return result;
	}
	private static Double evaluateToken(HashMap<String,Double> elements,
			ArrayList<String> tokens, int n)
	{
		String token = tokens.get(n);
		char c = token.charAt(0);
		
		int isNegative = 1;
		
		if(c == '-')
		{
			//minus sign indicates negative
			//to repeat less code, change isNegative if 
			//the minus sign is there.
			isNegative = -1;
			//remove the negative
			token = token.substring(1);
			//update the first character of the negative string
			c = token.charAt(0);
		}
		
		if(Character.isAlphabetic(c))
		{
			return isNegative * retrieveVariable(elements,token);
		}
		else if(Character.isDigit(c))
		{
			return isNegative * Double.parseDouble(token);
		}
		else if(c == '(')
		{
			//evaluate as a separate expression
			//remove the parens by pulling out the substring
			return isNegative * evaluateExpression(elements,token.substring(1,token.length()-1));
		}
		else if(c == '%')
		{
			//the functionIdentifier class will remove the thing
			
			return isNegative * FunctionIdentifier.evaluateFunction(elements,token);
		}
			
		return null;
	}
	private static void replace(ArrayList<String> tokens, int index, double result)
	{
		String newToken = Double.toString(result);
		
		tokens.set(index-1, newToken);
		
		//remove the two following "token slots"
		tokens.remove(index);
		tokens.remove(index);
	}
	private static void separateIntoTokens(ArrayList<String> tokens, ArrayList<Integer> flags, String line)
	{
		flagOperations(flags,line);
		
		int flag, previousFlag = -1;

		if(flags.size() == 0)
		{
			tokens.add(line);
		}
		else
		{
			for(int n=0;n<flags.size();n++)
			{
				//get the next flag
				flag = flags.get(n);
				
				//add the substring
				tokens.add(line.substring(previousFlag+1,flag));
				//add the operation at the flag
				tokens.add(line.substring(flag,flag+1));
				
				//set the next flag
				previousFlag = flag;
			}	
		//get the last token
		tokens.add(line.substring(flags.get(flags.size()-1)+1));
		}
	}
	private static void flagOperations(ArrayList<Integer> flags, String line)
	{
		int parens = 0;
		char c;
		for(int n=0;n<line.length();n++)
		{
			c = line.charAt(n);
			switch(c)
			{
			case '(':
			{
				parens++;
				break;
			}
			case ')':
			{
				parens--;
				break;
			}
			}
			
			//parens are matched if parens == 0
			if(parens == 0)
			{
				//flag operations
				switch(c)
				{
				case '^':
				case '*':
				case '/':
				case '+':
				case '-':
				{
					//check to see if the minus sign is for negative notation
					if(c == '-')
					{
						//negative notation will always follow another operation
						//or be the first operation in the line
						if(n == 0 || isPemdasNotation(line.charAt(n-1)))
							continue;
					}
					
					flags.add(n);
					break;
				}
				}
			}
		}
	}
	private static double retrieveVariable(HashMap<String, Double> elements, String variable)
	{
		double result = 0;

		try
		{
			result = elements.get(variable);
		}
		catch(NullPointerException e)
		{
			System.out.println("Failed to retrieve variable: "+variable);
			e.printStackTrace();
		}
		return result;
	}
	private static boolean isPemdasNotation(char c)
	{
		boolean isPemdasNotation;

		switch(c)
		{
		case '^':
		case '*':
		case '/':
		case '+':
		case '-':
		{
			isPemdasNotation = true;
			break;
		}
		default:
		{
			isPemdasNotation = false;
		}
		}

		return isPemdasNotation;
	}
	/**
	 * removeWhiteSpace method removes all 
	 * @param line
	 * @return
	 */
	private static String removeWhitespace(String line)
	{
		String temp = "";
		char c;
		for(int n=0;n<line.length();n++)
		{
			c = line.charAt(n);
			if(!Character.isWhitespace(c))
				temp += c;
		}
		return temp;
	}
}