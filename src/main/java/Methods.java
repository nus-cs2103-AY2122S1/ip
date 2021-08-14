/**
 * Class containing helpful static methods for various uses around the program.
 */
public class Methods {
	/**
	 * Checks if a String is an int.
	 *
	 * @param message Message to be checked.
	 * @return Whether the String is an int.
	 */
	public static Boolean isInt(String message) {
		try {
			Integer.parseInt(message);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}
