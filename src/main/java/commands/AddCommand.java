package commands;

import duke.Ui;
import tasks.Task;

/**
 * An abstract class that represents commands given by the
 * user that involves adding a task to the task:ist.
 */
public abstract class AddCommand implements Command {

    /** The type of task that should be added */
    private final Task.Type type;
    /** The user input */
    private final String userInput;

    public abstract boolean verifyAddCommand(String input);

    public AddCommand(String userInput, Task.Type typeToAdd) {
        this.userInput = userInput;
        this.type = typeToAdd;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void invalidArgumentsProvided() {
        Ui.printErrorMessage(type);
    }

    /**
     * Removes the first word from a String. This method is used to remove the
     * command word from a user input so as to extract the required information
     * for the command.
     *
     * @return The details of an add task command.
     */
    public String removeFirstWordFromInput() {
        try {
            return this.userInput.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.invalidArgumentsProvided();
            return null;
        }
    }
}
