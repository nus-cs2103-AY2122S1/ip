package duke.exception;

/**
 * NoDateIndicatorException is thrown if there is no /at or /by provided by Event or Deadline respectively.
 */
public class NoDateIndicatorException extends Exception {
	private final static String SAD_ROBOT_ICON = "[~T-T~]";

	/**
	 * Constructs error message depending on whether it is Deadline or Event task.
	 *
	 * @param dateType the dateType is either Deadline or Event task in string.
	 */
	public NoDateIndicatorException(String dateType) {
		super(SAD_ROBOT_ICON + ": OOPS!!! You are missing " + dateType + " before your timing/date");
	}

}
