package exception;

public class InvalidCommand extends DukeException {

    /**
     * Returns the information regarding the exception.
     *
     * @return message containing exception reason.
     */
    @Override
    public String getMessage() {
        return "Please don't confuse me with unknown commands, I do not know what that means! >_<";
    }
}
