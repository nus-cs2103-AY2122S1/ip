package exception;

public class InvalidNotes extends DukeException {

    /**
     * Returns the information regarding the exception.
     *
     * @return message containing exception reason.
     */
    @Override
    public String getMessage() {
        return "I don't see any notes information added, please add it in or remove the '--' flag ";
    }
}
