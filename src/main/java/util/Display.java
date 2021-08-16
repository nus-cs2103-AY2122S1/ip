package util;

public class Display {
	public static void printSentence(String sentence) {
		System.out.println(("\t____________________________________________________________\n" +
				"\t" + sentence.replace("\n", "\n\t") +
				"\n\t____________________________________________________________\n"));
	}
}
