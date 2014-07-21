package com.quiz_generation;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ProblemFileReader
{
	public static Problem readProblemFromXMLFile(String fileName)
	{
		try
		{
			fileName = System.getProperty("user.dir")+"\\Quiz Generator\\Problems\\XML Problems\\"+fileName;
			
			//xml resources
			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			//optional, but recommended
			doc.getDocumentElement().normalize();

			//other resources
			Problem problem = null;
			
			NodeList nList = doc.getElementsByTagName("problem");
			
			for (int n=0; n<nList.getLength();n++)
			{
				Node nNode = nList.item(n);

				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNode;
					
					problem = extractProblem(eElement);
				}
			}
			
			return problem;
		}
		catch(Exception e)
		{
			System.out.println("Problem Reading Problem File e:01");
			e.printStackTrace();
			System.exit(4);
		}
		
		return null;
	}
	
	private static Problem extractProblem(Element e)
	{
		String header;
		String calculations;
		
		header = "{"+e.getElementsByTagName("header").item(0).getTextContent().trim()+"}";
		calculations = e.getElementsByTagName("calculations").item(0).getTextContent().trim();
	
		header = header.replaceAll("\\s+", " ");
		calculations = calculations.replaceAll(";\\s+",";");
		
		NodeList questionElements = e.getElementsByTagName("question");
		Element tempElement;
		String ef=null, 
				units=null,
				statement=null,
				answer=null;
		QuestionSet questions = new QuestionSet();
		
		for(int n=0;n<questionElements.getLength();n++)
		{
			tempElement = (Element) questionElements.item(n);
			
			ef = tempElement.getAttribute("ef");
			units = tempElement.getAttribute("units");
			
			statement = "{"+tempElement.getElementsByTagName("statement").item(0).getTextContent().trim()+"}";
			answer = tempElement.getElementsByTagName("answer").item(0).getTextContent().trim();
		
			questions.add(new MultipleChoiceQuestion(statement,answer,units,ef));
		}

		return new Problem(header,calculations,questions);
	}
}