package com.environment_management;

import java.io.*;
import java.nio.file.*;

public class EnvironmentManager
{
	private static String[] folders = {"/Problems/XML Problems","/Problems/Conceptual Problems","/Quizzes","/Debug Files"};
	
	public static boolean establishEnvironment() throws IOException
	{
		String dir;
		File file;
		Path target;
		
		dir = System.getProperty("user.dir")+"\\Quiz Generator";

		file = new File(dir);
		target = Paths.get(dir);
		
		if(checkFile(target))
		{
			if(file.isDirectory())
			{
				return verifyEnvironment(dir);
			}
			else
			{
				return false;
			}
		}
		else
		{
			return createDefaultEnvironment(dir);
		}
		
		
	}
	/**
	 * verifyEnvironment method ensures that the necessary folders and sub folders exist.
	 * @return
	 */
	private static boolean verifyEnvironment(String dir) throws IOException
	{
		Path target;
		
		for(String folder : folders)
		{
			target = Paths.get(dir+folder);
			
			if(!checkFile(target))
			{
				Files.createDirectories(target);
			}
		}
		
		return true;
	}
	/**
	 * createDefaultEnvironment creates the default fileSystem under the provided
	 * directory
	 * @param dir
	 * @return
	 */
	private static boolean createDefaultEnvironment(String dir)
	{
		Path target;
		
		try
		{
			for(String folder : folders)
			{
				target = Paths.get(dir+folder);
				
				Files.createDirectories(target);	
			}	
		}
		catch(IOException e)
		{
			System.out.println("There was a problem creating the necessary directories.");
			e.printStackTrace();
			System.exit(4);
		}
		
		return true;
	}
	/**
	 * checkFile method checks whether or not a file exists at the specified Path target.
	 * @param target Path object detailing a file location
	 * @return true/false on Files.exist(target) outcome
	 */
	public static boolean checkFile(Path target)
	{
		boolean exists = Files.exists(target);
		
		if(exists)
			return exists;
		else
		{
			return exists;
		}
	}	
}