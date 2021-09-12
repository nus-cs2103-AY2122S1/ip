package duke.command;

import duke.Ui;
import duke.exception.DukeException;
import duke.parser.ParsedInput;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Represents a command to mark a task as done.
 */
public class DoneCommand implements Command {

    private final String description;
    private final Task.TaskTypes taskType;
    private final int index;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Creates a done command.
     *
     * @param parsedInput Parsed input
     * @param tasks Task list
     * @param ui Ui
     */
    public DoneCommand(ParsedInput parsedInput, TaskList tasks, Ui ui) {
        this.index = parsedInput.index;
        this.description = parsedInput.description;
        this.taskType = parsedInput.taskType;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Runs the done command.
     *
     * @return Success message
     * @throws DukeException Could not mark task done
     */
    @Override
    public String run() throws DukeException {
        if (description == null) {
            return ui.showMarkedDoneMessage(tasks.markDone(index));
        } else {
            return ui.showMarkedDoneMessage(tasks.markDone(description, taskType));
        }
    }
}
