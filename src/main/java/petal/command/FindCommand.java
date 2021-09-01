package petal.command;

import petal.components.Storage;
import petal.components.TaskList;
import petal.exception.InvalidInputException;

/**
 * The FindCommand implements Command
 * and handles the finding of keywords in tasks
 */
public class FindCommand implements Command {

    private final String input;

    /**
     * Constructs a FindCommand instance
     *
     * @param input The given input
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Finds the task with the given keyword
     *
     * @param taskList TaskList instance
     * @param storage Storage instance
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            return taskList.findTaskWithKeyword(input);
        } catch (InvalidInputException e) {
            return e.getMessage();
        }
    }
}
