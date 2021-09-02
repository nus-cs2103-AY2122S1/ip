package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the Commands that Duke can respond to.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public abstract class Command {
    /** A String representing the user's input. */
    protected String userInput;

    /**
     * Constructor of the Command class.
     *
     * @param userInput A string representing the user's input.
     */
    public Command(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Empty Constructor of the Command class.
     */
    public Command() {
    }

    /**
     * Returns the user input.
     *
     * @return A string representing the user input.
     */
    public String getUserInput() {
        return this.userInput;
    }

    /**
     * Returns Duke's response as a string after executing a specific command.
     * The method body must be implemented by its subclasses.
     *
     * @param tasks The taskList where all tasks are stored.
     * @param ui An instance of the Ui class that is responsible for Duke's user interactions.
     * @param storage An instance of a the Storage class that saves and loads Duke's data.
     * @return A string representing Duke's reply after executing a command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
