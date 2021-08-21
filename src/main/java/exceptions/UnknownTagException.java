public class UnknownTagException extends DukeException {
    /**
     * Thrown when the user input an unknown string to the chat bot.
     * Chat bot does not recognise the input given.
     */

    public UnknownTagException() {
        super("No such tag! Please input the correct tag!");
    }
}
