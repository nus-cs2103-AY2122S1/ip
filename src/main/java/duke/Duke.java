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

    /** The boolean to indicate whether an Exit command is made */
    private boolean isExit;

    // to be used for undo feature for add, delete and done commands
    private Command previousCommand;

    /**
     * The constructor for Duke class
     */
    public Duke() {
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
        assert(tasks.getListOfTasks() != null);
        this.previousCommand = null;
    }

    /**
     * Method to execute the user command, and return a response to user
     *
     * @param input The user command input
     */
    public String getResponse(String input) {
        String output = "";
        try {
            Parser parser = new Parser(this);
            Command c = parser.parse(input);
            previousCommand = c;
            output = c.execute(tasks, storage);
            isExit = c.isExit();
        } catch (DukeException e) {
            output = e.getMessage();
        }
        return output;
    }

    /**
     * The method to determine if user has input an Exit command
     * @return whether an Exit command has been made
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * The method to retrieve the task list
     *
     * @return the list of tasks
     */
    public TaskList getTaskList() {
        return this.tasks;
    }

    public Command getPreviousCommand() {
        return this.previousCommand;
    }
}
