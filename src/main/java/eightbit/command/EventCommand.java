package eightbit.command;

import eightbit.task.Event;
import eightbit.util.Storage;
import eightbit.util.TaskList;

/**
 * Represents a command to add an event.
 */
public class EventCommand extends Command {

    private final Event event;

    /**
     * Constructs a command to add an event.
     *
     * @param event Event instance to be added.
     */
    public EventCommand(Event event) {
        this.event = event;
    }

    /**
     * Adds the event into the user's list.
     *
     * @param taskList User's list of tasks.
     * @param storage  Storage responsible for reading/writing the file.
     * @return The response after executing the command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert event != null : "Event should be initialized";

        taskList.add(event);
        storage.appendToFile(event);
        return "Got it. I've added this task:\n  " + event
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }
}
