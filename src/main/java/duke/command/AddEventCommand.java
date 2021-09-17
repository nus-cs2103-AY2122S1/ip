package duke.command;

import duke.DateTime;
import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime time;

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

        String description = eventInputs[0];
        LocalDateTime time;
        try {
            time = DateTime.parse(eventInputs[1]);
        } catch(Exception e) {
            throw new InvalidCommandException("Unable to parse event time");
        }

        this.description = description;
        this.time = time;
    }

    /**
     * Adds an event to the user's tasklist.
     *
     * @param taskList The tasklist.
     * @param ui The instance of the {@link Ui} class.
     * @param storage The instance of the {@link Storage} class.
     * @throws IOException when unable to save tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        Task event = new Event(description, time);
        taskList.addTask(event);

        storage.saveTasks(taskList);
        ui.printMessage("Added the following task:\n    " + event.toString() + "\n" + "You now have "
                + taskList.size() + " tasks in your list.");
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
