package petal.command;

import petal.components.Responses;
import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;
import petal.exception.EmptyDescException;
import petal.exception.InvalidInputException;

/**
 * The TaskCommand implements Command and deals
 * with handling commands related to task creation
 */
public class TaskCommand implements Command {

    private final String type;
    private final String input;

    /**
     * Constructor for TaskCommand
     *
     * @param type The type of Task
     * @param input The input
     */
    public TaskCommand(String type, String input) {
        this.type = type;
        this.input = input;
    }

    /**
     * Creates the corresponding Task object
     *
     * @param taskList TaskList instance
     * @param ui Ui instance
     * @param storage Storage instance
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.handleTask(type, input);
        } catch (EmptyDescException | InvalidInputException e) {
            ui.output(e.getMessage());
            ui.output(Responses.REQUIRED_FORMAT);
        }
    }

}
