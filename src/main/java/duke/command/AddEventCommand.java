package duke.command;

import java.time.LocalDateTime;

import duke.DateTime;
import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;

public class AddEventCommand extends Command {
    private final String description;
    private final LocalDateTime time;

    /**
     * Creates a command that adds a {@link Event} to the user's tasks.
     *
     * @param arguments Command arguments.
     */
    public AddEventCommand(String arguments) throws DukeException {
        if (arguments.length() == 0) {
            throw new InvalidCommandException("Command `event` requires arguments");
        }

        String[] eventInputs = arguments.trim().split("\\s+/at\\s+", 2);

        if (eventInputs.length == 1) {
            if (eventInputs[0].length() == 0) {
                throw new InvalidCommandException("Event must have description and time");
            } else {
                throw new InvalidCommandException("Event must have time");
            }
        }

        if (eventInputs.length != 2) {
            throw new InvalidCommandException("Event must have description and time");
        }

        this.description = eventInputs[0];
        this.time = DateTime.parseDateTime(eventInputs[1]);
    }

    /**
     * Adds an event to the user's tasklist.
     *
     * @param taskList The tasklist.
     * @param ui The instance of the {@link Ui} class.
     * @param storage The instance of the {@link Storage} class.
     * @throws DukeException when unable to save tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task event = new Event(description, time);
        taskList.addTask(event);

        storage.saveTasks(taskList);
        ui.printMessage("Added the following task:\n    " + event + "\n" + "You now have "
                + taskList.size() + " tasks in your list.");
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
