package duke;

/**
 * Represents a Duke bot that can interact with users
 * and keep track of different tasks.
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Duke
     *
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.loadingError();
            tasks = new TaskList();
        }
        parser = new Parser(ui, tasks);

    }

    /**
     * Runs the program.
     */

    public void run() {
        ui.greetUser();
        boolean isBye = false;
        while (!isBye) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isBye = c.isBye();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Creates an instance of Duke and runs the program.
     *
     * @param args Command line arguments
     */

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();

    }
}