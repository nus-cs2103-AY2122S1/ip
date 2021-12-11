package duke.commands;

import duke.tasks.Events;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The command indicating that the user wants to add in a event into the task list.
 */
public class EventCommand extends Command {
    private String description;
    private String dateTimeAt;

    /**
     * Constructor for EventCommand.
     *
     * @param description the description of the Deadline to be added.
     * @param dateTimeAt the time/place/period where this event will occur.
     */
    public EventCommand(String description, String dateTimeAt) {
        this.description = description;
        this.dateTimeAt = dateTimeAt;
    }

    /**
     * Performs the actions that adds the event to the task list.
     *
     * @param tasks the full task list containing all the tasks.
     * @param ui the ui instance.
     * @param storage the storage instance.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Events newEvent = new Events(description, dateTimeAt, false);
        tasks.addTask(newEvent);
        ui.showTaskAddedInteraction(newEvent, tasks);
        storage.addTaskToPersistedData(newEvent);
    }
}
