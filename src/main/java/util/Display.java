package util;

import java.util.List;

public class Display {
	/**
	 * helper function to display the sentence for the CLI
	 *
	 * @param sentence the text to be printed
	 */
	public static void printSentence(String sentence) {
		System.out.println(("\t____________________________________________________________\n" +
				"\t" + sentence.replace("\n", "\n\t") +
				"\n\t____________________________________________________________\n"));
	}
	
	public static <T> void printIndexedList(List<T> list) {
		int numbering = 1;
		StringBuilder string = new StringBuilder();
		
		for (T item : list) {
			string.append(" ")
			      .append(numbering)
			      .append(". ")
			      .append(item.toString())
			      .append("\n");
			
			numbering++;
		}
		
		// remove the last extra \n if there is at least an item in the list
		if (list.size() > 0) string.deleteCharAt(string.length() - 1);
		
		printSentence(string.toString());
	}
}
