package duke;

/**
 * Represents the chat bot
 */
public class Duke {
    private Ui ui;

    /**
     * Constructor for <code>Duke</code>
     */
    public Duke() {
        this.ui = new Ui();
    }

    /**
     * Runs the chat bot
     */
    public void run() {
        ui.greet();
        boolean isExit = false;

        String commandString;
        while (!isExit) {
            commandString = ui.readCommand();
            if (commandString.length() <= 0) {
                continue;
            }
            String response = getResponse(commandString);
            ui.print(response);
            if (CommandParser.isExit(commandString)) {
                break;
            }
        }
    }

    /**
     * Gets the response by the input string
     * @param input input string
     * @return response message
     */
    public String getResponse(String input) {
        try {
            String message = CommandParser.parse(input).run();
            return message;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the greeting message
     * @return greeting message
     */
    public String greet() {
        return "Yo, I'm Xiri.\nHow can I help you?\nType `help` to see all available commands";
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
