package duke;

/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of various things.
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
     * Runs the Duke Personal Assistant Chatbot.
     */
    public void run() {
        boolean terminate = false;
        this.ui.welcome();

        while (!terminate) {
            try {
                String input = ui.readInput();
                parser.executeCommand(ui, tasks, input);
                terminate = parser.getToTerminate();
            } catch (Exception e) {
                ui.handleException(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}

