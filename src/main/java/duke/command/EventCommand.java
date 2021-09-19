package duke.command;

import java.time.temporal.Temporal;

import duke.Ui;
import duke.exception.DukeException;
import duke.exception.TaskExistsException;
import duke.parser.ParsedInput;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Represents a command to create an event.
 */
public class EventCommand implements Command {

    private final Temporal dateTime;
    private final String description;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Creates an EventCommand.
     *
     * @param parsedInput Parsed input
     * @param tasks Task list
     * @param ui Ui
     */
    public EventCommand(ParsedInput parsedInput, TaskList tasks, Ui ui) {
        this.description = parsedInput.description;
        this.dateTime = parsedInput.dateTime;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Runs the event command.
     *
     * @return Success message
     * @throws DukeException Could not create event
     */
    @Override
    public String run() throws DukeException {
        // Extra Functionality: No duplicate tasks
        if (tasks.getTaskIndex(description, Task.TaskTypes.EVENT) != -1) {
            throw new TaskExistsException(Task.TaskTypes.EVENT, description);
        }

        Task event = new Event(description, dateTime);
        tasks.add(event);
        return ui.showAddedMessage(event, tasks.size());
    }
}
