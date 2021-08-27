package petal.command;

import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;
import petal.exception.InvalidInputException;

/**
 * The FindCommand implements Command
 * and handles the finding of keywords in tasks
 */
public class FindCommand implements Command {

    private final String input;

    /**
     * Constructor for FindCommand class
     * @param input The given input
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Finds the task with the given keyword
     *
     * @param taskList TaskList instance
     * @param ui Ui instance
     * @param storage Storage instance
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.findTaskWithKeyword(input);
        } catch (InvalidInputException e) {
            ui.output(e.getMessage());
        }
    }
}
