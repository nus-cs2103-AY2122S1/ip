package duke.command;

import java.time.temporal.Temporal;

import duke.Ui;
import duke.exception.DukeException;
import duke.exception.TaskExistsException;
import duke.parser.ParsedInput;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Represents a command to create a deadline.
 */
public class DeadlineCommand implements Command {

    private final Temporal dateTime;
    private final String description;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Creates a deadline command.
     *
     * @param parsedInput Parsed input
     * @param tasks Task list
     * @param ui Ui
     */
    public DeadlineCommand(ParsedInput parsedInput, TaskList tasks, Ui ui) {
        this.description = parsedInput.description;
        this.dateTime = parsedInput.dateTime;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Runs the deadline command.
     *
     * @return Success message
     * @throws DukeException Could not create deadline
     */
    @Override
    public String run() throws DukeException {
        // Extra Functionality: No duplicate tasks
        if (tasks.getTaskIndex(description, Task.TaskTypes.DEADLINE) != -1) {
            throw new TaskExistsException(Task.TaskTypes.DEADLINE, description);
        }

        Task deadline = new Deadline(description, dateTime);
        tasks.add(deadline);
        return ui.showAddedMessage(deadline, tasks.size());
    }
}
