package iris;

/**
 * Represents an Iris object
 */
public class Iris {
    private static final String ENDING_COMMAND = "bye";

    private final Storage storage;
    private final TaskList tasks = new TaskList();
    private final Ui ui;

    /**
     * Creates a new Iris instance
     *
     * @param filePath Path to file containing tasks
     */
    public Iris(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            // TODO: reading tasks should not need UI since it's silent
            storage.readTasks(tasks, ui);
        } catch (IrisException exception) {
            ui.sayError(exception);
        }
    }

    /**
     * Starts an Iris instance
     */
    public void run() {
        ui.say("Hello! I'm Iris. What can I do for you?");
        String command = ui.prompt();
        while (!command.equals(ENDING_COMMAND)) {
            try {
                Parser.handleCommand(command, tasks, ui);
            } catch (IrisException exception) {
                ui.sayError(exception);
            }
            storage.writeTasks(tasks);
            command = ui.prompt();
        }

        ui.say("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        new Iris("data/tasks.txt").run();
    }
}
