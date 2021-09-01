package petal.command;

import petal.components.Storage;
import petal.components.TaskList;
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
     * Constructs a TaskCommand instance
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
     * @param storage Storage instance
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            return taskList.handleTask(type, input);
        } catch (EmptyDescException | InvalidInputException e) {
            return e.getMessage();
        }
    }

}
