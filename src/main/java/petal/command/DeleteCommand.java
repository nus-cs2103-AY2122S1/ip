package petal.command;

import petal.components.Responses;
import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;
import petal.exception.InvalidInputException;

/**
 * The DeleteCommand class implements Command
 * and handles the deletion of tasks
 */
public class DeleteCommand implements Command {

    private final String input;

    /**
     * Constructs a DeleteCommand instance
     *
     * @param input The given class
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Deletes the task corresponding to the input
     *
     * @param taskList TaskList instance
     * @param ui Ui instance
     * @param storage Storage instance
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.deleteTask(input);
        } catch (InvalidInputException e) {
            ui.output(e.getMessage());
            ui.output(Responses.REQUIRED_FORMAT);
        }
    }
}
