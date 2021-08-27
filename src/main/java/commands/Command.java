package commands;

import java.util.ArrayList;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The Command class represents the action to be done.
 */
public abstract class Command{

    private final ArrayList<String> input;

    /**
     * Constructs the Command object.
     *
     * @param s the entire line of user input
     */
    public Command(ArrayList<String> s) {
        this.input = s;
    }

    /**
     * Returns the stored user input.
     *
     * @return the user's line of input
     */
    public ArrayList<String> getInput() {
        return input;
    }

    /**
     * Returns true if it is a command to exit the program.
     *
     * @return the result if it is a command to exit the program
     */
    public boolean isExit() {
        return this instanceof ByeCommand;
    }

    /**
     * Executes the command.
     *
     * @param lst the TaskList object that stores the list of tasks
     * @param ui the Ui object that interacts with the user
     * @param storage the Storage object that saves changes to stored tasks, if any
     */
    public abstract void execute(TaskList lst, Ui ui, Storage storage);
}
