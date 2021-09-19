package duke.command;

import duke.Ui;
import duke.exception.DukeException;
import duke.parser.ParsedInput;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Represents a command to delete a task.
 */
public class DeleteCommand implements Command {

    private final String description;
    private final Task.TaskTypes taskType;
    private final int index;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Creates a delete command.
     *
     * @param parsedInput Parsed input
     * @param tasks Task list
     * @param ui Ui
     */
    public DeleteCommand(ParsedInput parsedInput, TaskList tasks, Ui ui) {
        this.index = parsedInput.index;
        this.description = parsedInput.description;
        this.taskType = parsedInput.taskType;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Runs the delete command.
     *
     * @return Success message
     * @throws DukeException Could not delete task
     */
    @Override
    public String run() throws DukeException {
        if (description == null) {
            return ui.showDeletedMessage(
                    tasks.remove(index),
                    tasks.size());
        } else {
            return ui.showDeletedMessage(
                    tasks.remove(description, taskType),
                    tasks.size());
        }
    }
}
