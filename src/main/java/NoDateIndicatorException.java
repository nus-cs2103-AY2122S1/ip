/**
 * NoDateIndicatorException is thrown if there is no /at or /by provided by either Event or Deadline task respectively.
 */
public class NoDateIndicatorException extends Exception{
    private final static String SAD_ROBOT_ICON = "[~T-T~]";

    /**
     * Constructs error message depending on whether it is Deadline or Event task type.
     *
     * @param dateType the dateType can be either Deadline or Event task type.
     */
    public NoDateIndicatorException(String dateType) {
        super(SAD_ROBOT_ICON +": OOPS!!! You are missing " + dateType + " before your timing/date");
    }

}
