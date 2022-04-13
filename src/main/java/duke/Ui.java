package duke;

/**
 * Encapsulates the user interface. Scans for input and responds to user.
 */
public class Ui {

    public static final String WELCOME_MSG = "What's up, I'm duke!";
    public static final String BYE_MSG = "Bye mate!";
    private final TaskList tasks;
    private final Storage storage;
    private Parser parser;

    /**
     * Constructs a user interface.
     *
     * @param tasks TaskList to record tasks.
     * @param storage Storage to store tasks.
     */
    public Ui(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Initializes the user interface.
     *
     * @return Response as string.
     */
    public String initialize() {
        parser = new Parser(tasks);
        return WELCOME_MSG;
    }

    /**
     * Responds to a user input.
     *
     * @param message User input.
     * @return A message formatted as a string.
     */
    public String respond(String message) {
        try {
            storage.writeCommand(message);
            return formatResponse(parser.parseCommand(message));
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Formats a response into a single string.
     *
     * @param messages Strings to be formatted.
     * @return A single string combining all messages.
     */
    private static String formatResponse(String... messages) {
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < messages.length; i++) {
            assert !messages[i].isEmpty() : "message should not be empty";
            response.append(messages[i]);
            if (i < messages.length - 1) {
                response.append("\n");
            }
        }
        return response.toString();
    }

    /**
     * Checks if a message is a terminating message.
     *
     * @param message Message string to be checked.
     * @return Boolean describing if the message is terminal.
     */
    public static boolean isByeMsg(String message) {
        return message.equals(BYE_MSG);
    }
}
