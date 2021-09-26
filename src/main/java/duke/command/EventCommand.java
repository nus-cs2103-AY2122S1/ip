package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the EventCommand in the Duke program.
 */
public class EventCommand extends Command {
    private final Event event;

    /**
     * Constructs a EventCommand to create an Event task with the given description and datetime.
     *
     * @param description Description of the Event task.
     * @param at          Datetime of the Event task.
     */
    public EventCommand(String description, String at) {
        event = new Event(description, at);
    }

    /**
     * Returns the response after creating an Event task.
     *
     * @param tasks   Tasks of the Duke program.
     * @param ui      Ui of the Duke program.
     * @param storage Storage of the Duke program.
     * @throws DukeException If changes cannot be saved to storage.
     */
    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(event);

        String response = "Got it. I've added this task:\n"
                + " " + event + "\n"
                + "Now you have "
                + tasks.getSize() + (tasks.getSize() > 1 ? " tasks" : " task")
                + " in the list.";

        storage.save(tasks.getTaskList());

        return response;
    }

    /**
     * Returns false as this command is not the ExitCommand.
     *
     * @return false as this command is not the ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
