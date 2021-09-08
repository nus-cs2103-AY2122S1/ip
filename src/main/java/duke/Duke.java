package duke;

import duke.command.Command;

/**
 * The main logic flow of the Duke program.
 */
public class Duke {

    /** The filepath for storage */
    private static final String FILE_PATH = "data/tasks.txt";

    /** The storage utility */
    private Storage storage;

    /** The list of tasks */
    private TaskList tasks;

    /** The UI for the program */
    private Ui ui;

    private boolean isExit;

    /**
     * The constructor for Duke class
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
        assert(tasks.getListOfTasks() != null);
    }

    /**
     * Method to execute the user command, and return a response to user
     *
     * @param input The user command input
     */
    public String getResponse(String input) {
        String output = "";
        try {
            Command c = Parser.parse(input);
            output = c.execute(tasks, ui, storage);
            isExit = c.isExit();
        } catch (DukeException e) {
            output = e.getMessage();
        }
        return output;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public TaskList getTaskList() {
        return this.tasks;
    }
}
