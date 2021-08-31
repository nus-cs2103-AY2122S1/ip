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
     * Initialize the user interface.
     *
     * @return Response as string.
     */
    protected String initialize() {
        parser = new Parser(tasks);
        return WELCOME_MSG;
    }

    protected String respond(String message) {
        try {
            storage.cache(message);
            return formatResponse(parser.parseCommand(message));
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    protected static String formatResponse(String[] messages) {
        String response = "";
        for (int i = 0; i < messages.length; i++) {
            response += messages[i];
            if (i < messages.length - 1) {
                response += "\n";
            }
        }
        return response;
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
