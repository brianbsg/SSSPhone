package com.bsgco;

public final class Editor {
	private final String data;
	
	private String name;
	
	public Editor(String file) {
		//set name blank
		name = null;
		//split one line into many around newline
		String [] lines = file.split("\n");
		//start a new String
		StringBuilder sb = new StringBuilder();
		//denotes first line
		boolean first = true;
		for(String line : lines) {
			if(first) //if first line do nothing
				first = false;
			else
				sb.append('\n'); //otherwise put a line break
			//add the line after fixes
			sb.append(handleLine(line));
		}
		//set end result
		data = sb.toString();
	}
	
	/**
	 * Get the String data
	 */
	public String toString() { return data; }
	
	private String handleLine(String line) {
		if(line.contains("<NAME>")) {
			String fix = line.trim(); //cut whitespace
			fix = fix.substring(6, fix.length() - 7); //cut tags
			char c = Character.toUpperCase(fix.charAt(0)); //get the first character upper case
			name = c + fix.substring(1) + ". "; //put together and store 
			return line; //return original
		}
		else if(line.contains("<LABEL>")) {
			String fix = line.trim(); //cut whitespace
			fix = fix.substring(7, fix.length() - 8); //cut tags
			if(fix.startsWith("\"") && fix.endsWith("\""))
				fix = fix.substring(1, fix.length() - 1).trim();
			return "<LABEL>" + name + fix + "</LABEL>";
		}
		else if(line.contains("<value "))
			return handleValue(line);
		else //return original
			return line;
	}
	
	private String handleValue(String line) {
		String fix = line.trim();
		fix = fix.substring(0, fix.length() - 8);
		StringBuilder sb = new StringBuilder();
		int i;
		for(i = 0; i < fix.length(); i++) {
			char c = fix.charAt(i);
			sb.append(c);
			if(c == '>')
				break;
		}
		fix = fix.substring(i + 1);
		if(fix.startsWith("\"") && fix.endsWith("\""))
			fix = fix.substring(1, fix.length() - 1);
		sb.append(fix);
		sb.append("</value>");
		return sb.toString();
	}
}
