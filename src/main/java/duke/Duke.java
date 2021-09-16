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
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Processes a user input and returns a response.
     *
     * @param input The command that the user inputs.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
