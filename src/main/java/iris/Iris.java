package iris;

import iris.command.Command;

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
            storage.readTasks(tasks);
        } catch (IrisException exception) {
            ui.sayError(exception);
        }
    }

    /**
     * Starts an Iris instance
     */
    public void run() {
        ui.sayHello();

        String rawCommand = ui.prompt();
        while (!rawCommand.equals(ENDING_COMMAND)) {
            try {
                Command command = Parser.parse(rawCommand);
                command.run(tasks, ui, storage);
            } catch (IrisException exception) {
                ui.sayError(exception);
            }
            rawCommand = ui.prompt();
        }

        ui.sayGoodbye();
    }

    public static void main(String[] args) {
        new Iris("data/tasks.txt").run();
    }
}
