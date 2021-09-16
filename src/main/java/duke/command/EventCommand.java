package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Event;
import duke.task.TaskList;

/**
 * Represents the command when the user wants to add an event to the task list.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    /** The description of the event to be added. */
    private String description;
    /** The date and time of the event to be added. */
    private String date;
    private String time;

    /**
     * Constructs an EventCommand object.
     *
     * @param description The description of the event to be added.
     * @param dateAndTime The date and time of the event to be added.
     */
    public EventCommand(String description, String date, String time) {
        this.description = description;
        this.date = date;
        this.time = time;
    }

    /**
     * Executes the command to add an event to the task list.
     *
     * @param tasks The user's task list.
     * @param storage Storage object used to save the task list to the data file.
     * @return A message informing the user that the event has been added and the
     * total number of tasks he has now.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Event newEvent = new Event(description, date, time);
        tasks.addTask(newEvent);
        storage.save(tasks);
        return Ui.getAddedMessage(newEvent, tasks.size());
    }
}
