package petal.command;

import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;
import petal.exception.InvalidInputException;

/**
 * The DateCommand class implements Command
 * and handles displayed tasks on the given date
 */
public class DateCommand implements Command {

    private final String input;

    /**
     * Constructor for the DateCommand class
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
     * @param ui Ui instance
     * @param storage Storage instance
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.showTaskOnDate(input);
        } catch (InvalidInputException e) {
            ui.output(e.getMessage());
        }
    }
}
