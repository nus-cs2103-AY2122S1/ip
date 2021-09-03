package duke;

/**
 * Class that represent all errors/exceptions that are unique to the chat bot.
 */
public class DukeException extends Exception {

    /**
     * Returns a String message indicating an error.
     *
     * @return Error message.
     */
    @Override
    public String toString() {
        return "\uD83D\uDE41 OOPS!!!";
    }
}
