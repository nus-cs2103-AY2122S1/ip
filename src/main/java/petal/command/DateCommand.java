package petal.command;

import petal.components.Responses;
import petal.components.Storage;
import petal.components.TaskList;
import petal.exception.InvalidInputException;

/**
 * The DateCommand class implements Command
 * and handles displayed tasks on the given date
 */
public class DateCommand implements Command {

    private final String input;

    /**
     * Constructs a DateCommand instance
     *
     * @param input The given input
     */
    public DateCommand(String input) {
        this.input = input;
    }

    /**
     * Displays all the tasks on the given date
     *
     * @param taskList TaskList instance
     * @param storage Storage instance
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            return taskList.showTaskOnDate(input);
        } catch (InvalidInputException | ArrayIndexOutOfBoundsException e) {
            return Responses.INVALID_DATE_TIME.toString();
        }
    }
}
