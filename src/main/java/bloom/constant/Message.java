package bloom.constant;

/**
 * Contains messages to communicate with users.
 */
public enum Message {

    COMMAND_GREET("\t Hello! I'm Bloom\n\t What can I do for you?\n"),
    COMMAND_EXIT("\t Bye. Hope to see you again soon!\n"),
    COMMAND_LIST("\t Here are the tasks in your list:\n"),
    COMMAND_FIND("\t Here are the matching tasks in your list:\n"),
    COMMAND_ADD("\t Got it. I've added this task:\n"),
    COMMAND_DELETE("\t Noted. I've removed this task:\n"),
    COMMAND_MARK("\t Nice! I've marked this task as done:\n"),

    EXCEPTION_IO("\t Something is wrong with Bloom memory :(\n"),
    EXCEPTION_UNKNOWN_COMMAND("\t This command is not supported.\n");

    /** The message. */
    private final String message;

    /**
     * Constructor for a message.
     *
     * @param message the message
     */
    Message(String message) {
        this.message = message;
    }

    /**
     * Gets the message.
     *
     * @return the message content
     */
    public String getMessage() {
        return this.message;
    }
}
