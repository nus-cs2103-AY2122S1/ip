package duke;

import duke.command.Command;

/**
 * The main logic flow of the Duke program.
 */
public class Duke {

    /** The storage utility */
    private Storage storage;

    /** The list of tasks */
    private TaskList tasks;

    /** The boolean to indicate whether an Exit command is made */
    private boolean isExit;

    /** The previous command executed */
    private Command previousCommand;

    /** The boolean to indicate whether a DukeException has occurred */
    private boolean hasError;

    /**
     * The constructor for Duke class
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
        assert(tasks.getListOfTasks() != null);
        this.previousCommand = null;
    }

    /**
     * Executes the user command, and returns a response to user.
     *
     * @param input The user command input
     */
    public String getResponse(String input) {
        hasError = false;
        String output = "";
        try {
            Parser parser = new Parser(this);
            Command c = parser.parse(input);
            previousCommand = c;
            output = c.execute(tasks, storage);
            isExit = c.isExit();
        } catch (DukeException e) {
            hasError = true;
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

    /**
     * The method to retrieve the previous command that was executed
     *
     * @return the previous command that was executed
     */
    public Command getPreviousCommand() {
        return this.previousCommand;
    }

    /**
     * The method to retrieve whether a DukeException has occurred
     *
     * @return whether a DukeException has occurred
     */
    public boolean getHasError() {
        return this.hasError;
    }
}
