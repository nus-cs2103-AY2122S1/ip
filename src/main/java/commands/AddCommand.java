package commands;

import duke.Ui;
import tasks.Task;
import tasks.TaskList;

import java.util.ArrayList;

/**
 * An abstract class that represents commands given by the
 * user that involves adding a task to the taskList.
 */
public abstract class AddCommand extends UndoableCommand {

    /** The type of task that should be added */
    private final Task.Type type;
    /** The user input */
    private final String userInput;
    /** The taskList */
    private final TaskList taskList;

    /**
     * Creates a command that add a type of task to the taskList.
     *
     * @param userInput The user input that triggers the command.
     * @param typeToAdd The type of task that should be added.
     * @param taskList The taskList to add the task to.
     */
    protected AddCommand(String userInput, Task.Type typeToAdd, TaskList taskList) {
        this.userInput = userInput;
        this.type = typeToAdd;
        this.taskList = taskList;
    }

    @Override
    protected void setUndo() {
        int indexToRemove = this.taskList.getTotalNumOfTasks();
        this.setUndoFunction(() -> this.taskList.removeTask(indexToRemove, new ArrayList<>()));
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
    protected String removeFirstWordFromInput() {
        try {
            return this.userInput.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.setExecutionMessage(this.getInvalidArgumentsMessage());
            return null;
        }
    }
}
