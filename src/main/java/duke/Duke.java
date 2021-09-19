package duke;

/**
 * Represents a Personal Assistant Chat-bot that helps a person to keep track of various things.
 */
public class Duke {

    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Class constructor.
     */
    public Duke() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Runs the Duke Personal Assistant Chat-bot.
     */
    public void run() {
        boolean isTerminated = false;
        ui.printMessage(this.onStart());

        while (!isTerminated) {
            String message;
            try {
                String input = ui.readInput();
                message = parser.executeCommand(ui, tasks, input);
                isTerminated = parser.canTerminate();
            } catch (Exception e) {
                message = ui.handleException(e);
            }
            ui.printMessage(message);
        }
    }

    public String onStart() {
        return ui.welcome();
    }

    /**
     * Starts the program
     * @param args User input arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Gets the response message from the parser after parsing the command
     * @param input Command from user.
     * @return Response message.
     */
    protected String getResponse(String input) {
        try {
            return parser.executeCommand(ui, tasks, input);
        } catch (Exception e) {
            return ui.handleException(e);
        }
    }
}
