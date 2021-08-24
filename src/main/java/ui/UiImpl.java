package ui;

import java.util.List;

public class UiImpl implements IUi {
	/**
	 * Displays a list for the CLI using --- + ordered list + ---- format.
	 *
	 * @param list List.
	 * @param <T> Parameter, implicitly inferred.
	 */
	public <T> void printIndexedList(List<T> list) {
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
	
	/**
	 * Displays the sentence for the CLI using ---- + sentence + ----- format.
	 *
	 * @param sentence String to be printed.
	 */
	public void printSentence(String sentence) {
		System.out.println(("\t____________________________________________________________\n" +
				"\t" + sentence.replace("\n", "\n\t") +
				"\n\t____________________________________________________________\n"));
	}
}
