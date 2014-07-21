package com.quiz_generation;

public class QuizConfiguration
{
	private String label, fileName;
	private int nVersions;
	private boolean toRandomize, addLetters;
	
	public QuizConfiguration(String fileName, String label, int nVersions, boolean toRandomize, boolean addLetters)
	{
		this.fileName = fileName;
		this.label = label;
		this.nVersions = nVersions;
		this.toRandomize = toRandomize;
		this.addLetters = addLetters;
	}
	
	public boolean getAddLetters()
	{
		return addLetters;
	}
	
	/**
	 * @return the toRandomize
	 */
	public boolean isToRandomize() {
		return toRandomize;
	}
	/**
	 * @param toRandomize the toRandomize to set
	 */
	public void setToRandomize(boolean toRandomize) {
		this.toRandomize = toRandomize;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the nVersions
	 */
	public int getnVersions() {
		return nVersions;
	}
	/**
	 * @param nVersions the nVersions to set
	 */
	public void setnVersions(int nVersions) {
		this.nVersions = nVersions;
	}
}
