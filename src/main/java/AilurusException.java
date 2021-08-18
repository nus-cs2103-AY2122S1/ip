public class AilurusException extends IllegalArgumentException {
    private Error error;

    /**
     * List of errors for Ailurus
     */
    public enum Error {
        EMPTYLIST, EMPTYTODO, EMPTYEVENT, EMPTYDEADLINE, EMPTYBY, EMPTYAT, BY, AT, NUMBER, NAN
    }

    /**
     * Constructor for AilurusException
     *
     * @param e error to be thrown
     */
    public AilurusException(Error e) {
        this.error = e;
    }

    @Override
    public String toString() {
        switch (this.error) {
            case EMPTYLIST:
                return "☹ OOPS!!! No items in list!";
            case EMPTYTODO:
                return "☹ OOPS!!! The description of a todo cannot be empty.";
            case EMPTYDEADLINE:
                return "☹ OOPS!!! The description of a deadline cannot be empty.";
            case EMPTYEVENT:
                return "☹ OOPS!!! The description of an event cannot be empty.";
            case EMPTYBY:
                return "☹ OOPS!!! The description of /by cannot be empty.";
            case EMPTYAT:
                return "☹ OOPS!!! The description of /at cannot be empty.";
            case BY:
                return "☹ OOPS!!! A deadline must have a /by <specific date or time>.";
            case AT:
                return "☹ OOPS!!! An event must have a /at <specific date or time period>.";
            case NUMBER:
                return "☹ OOPS!!! Invalid number used.";
            case NAN:
                return "☹ OOPS!!! Not a number.";
            default:
                return getMessage();
        }
    }
}
