package exception;

public class InvalidDateFormat extends DukeException {

    /**
     * Returns the information regarding the exception.
     *
     * @return message containing exception reason.
     */
    @Override
    public String getMessage() {
        return "Invalid date format, please use the correct flag (/by for deadline, /at for event),"
                + "\nfollowed by <dd/mm/yyyy> format.";
    }
}
