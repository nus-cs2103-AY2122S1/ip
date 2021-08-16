package util;

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
}
