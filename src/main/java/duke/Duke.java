package duke;

/**
 * A personal assistant chatbot that maintains a to-do list.
 * Operations supported are add, list, mark as done, delete, and exit.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for an instance of Duke.
     *
     * @param filePath The path of the text file that stores Duke's data.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Runs an instance of Duke, that will prompt the user for an input.
     */
    public void run() {
        // Display welcome message to user
        ui.showWelcome();

        // Continuously prompt the user for input until the user commands Duke to exit
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showResponse(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
