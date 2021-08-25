package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    private String userInput;
    private boolean exit = false;

    /**
     * Constructor for abstract class Command
     *
     * @param userInput  user's input
     */
    public Command(String userInput) {
        this.userInput = userInput;
    }

    /**
     * The method to be overriden
     *
     * @param tasks contains the task list
     * @param ui deals with interactions with the user
     * @param storage deals with loading tasks from the file and saving tasks in the file
     * @throws DukeException If user input is incorrect
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {}

    /**
     * Sets exit to true
     */
    public void setExitTrue() {
        this.exit = true;
    }

    /**
     * Checks if the program should exist
     * @return boolean of whether to exit
     */
    public boolean isExit() {
        return this.exit;
    }

    /**
     * Returns user input
     * @return user input
     */
    public String getUserInput() {
        return this.userInput;
    }
}
