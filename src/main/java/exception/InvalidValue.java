package exception;

public class InvalidValue extends DukeException {

    /**
     * Returns the information regarding the exception.
     *
     * @return message containing exception reason.
     */
    @Override
    public String getMessage() {
        return "I don't see a task at that index, try again with a different number?";
    }
}
