package commands;

import duke.Ui;
import tasks.Task;

/**
 * An abstract class that represents commands given by the
 * user that involves adding a task to the taskList.
 */
public abstract class AddCommand extends Command {

    /** The type of task that should be added */
    private final Task.Type type;
    /** The user input */
    private final String userInput;

    /**
     * Create a command to add a type of task to the taskList.
     *
     * @param userInput The user input that triggers the command.
     * @param typeToAdd The type of task that should be added.
     */
    public AddCommand(String userInput, Task.Type typeToAdd) {
        this.userInput = userInput;
        this.type = typeToAdd;
    }

    /**
     * Checks that the user input to add a task is valid.
     *
     * @param input The user input to add a task.
     * @return True if the user input is valid.
     */
    public abstract boolean verifyAddCommand(String input);

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getInvalidArgumentsMessage() {
        return Ui.getErrorMessage(type);
    }

    /**
     * Removes the first word from a String. This method is used to remove the
     * command word from a user input so as to extract the required information
     * and parameters for the command.
     *
     * @return The details of an add task command.
     */
    public String removeFirstWordFromInput() {
        try {
            return this.userInput.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.setExecutionMessage(this.getInvalidArgumentsMessage());
            return null;
        }
    }
}
