package petal.command;

import petal.components.Responses;
import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;
import petal.exception.InvalidInputException;

/**
 * The DoneCommand class implements Command and deals
 * with marking the corresponding Task as done
 */
public class DoneCommand implements Command {

    private final String input;

    /**
     * Constructs a DoneCommand instance
     *
     * @param input The input in question
     */
    public DoneCommand(String input) {
        this.input = input;
    }

    /**
     * Marks the given task as done
     *
     * @param taskList TaskList instance
     * @param ui Ui instance
     * @param storage Storage instance
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.markTaskAsDone(input);
        } catch (InvalidInputException e) {
            ui.output(e.getMessage());
            ui.output(Responses.REQUIRED_FORMAT);
        }
    }
}
