package duke;

/**
 * A class to represent the user interface of Duke.
 */
public class Ui {
	/**
	 * Prints the formatted string substituted with the string representation of the args.
	 * 
	 * @param format The string format to be printed.
	 * @param args The argument to be substituted.
	 */
	public void showf(String format, Object... args) {
		System.out.printf(format, args);
	}

	/**
	 * Prints the string with a newline at the end.
	 * 
	 * @param s The string to be printed.
	 */
	public void show(Object s) {
		System.out.println(s);
	}
}
