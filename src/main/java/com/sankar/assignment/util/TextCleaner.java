package com.sankar.assignment.util;

public class TextCleaner {

	private static final String REGEXPATTERN_UNICODECHARS = "[^\\p{L}\\p{Nd}']+";
	private static final String REGEXPATTERN_DOUBLEQUOTES = "\\\"";

	public static String getCleanTextForRediff(String temp) {
		String result = "";
		if (temp != null) {
			result = temp.replace(REGEXPATTERN_UNICODECHARS, "");
			result = result.replace("\\","");
		}
		return result;
	}

}
