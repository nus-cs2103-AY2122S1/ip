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
    COMMAND_NOTE("\t Noted. I've added a note to this task:\n"),

    EXCEPTION_IO("\t Something is wrong with Bloom memory :(\n"),
    EXCEPTION_UNKNOWN_COMMAND("\t This command is not supported.\n"
            + "\t Available commands include:\n\t todo, deadline, event,\n"
            + "\t list, done, delete, find, note"),
    EXCEPTION_WRONG_FORMAT_TODO_COMMAND("\t Todo command format should be:\n"
            + "\t   todo [task description]"
            + "\t E.g., `todo read book`"),
    EXCEPTION_WRONG_FORMAT_DEADLINE_COMMAND("\t Deadline command format should be:\n"
            + "\t   deadline [task description] /by [task date]\n"
            + "\t   [task date] could be dd/mm/yyyy\n\t or dd-mm-yyyy\n"
            + "\t E.g., `deadline iP /by 17-09-2021 2359`"),
    EXCEPTION_WRONG_FORMAT_EVENT_COMMAND("\t Event command format should be:\n"
            + "\t   event [task description] /at [task date]\n"
            + "\t   [task date] could be dd/mm/yyyy\n\t or dd-mm-yyyy\n"
            + "\t E.g., `event meeting /by 23-09-2021 2200`");

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
