package eightbit.command;

import eightbit.task.Event;
import eightbit.util.Storage;
import eightbit.util.TaskList;
import eightbit.util.Ui;

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
     * @param ui Ui responsible for printing messages.
     * @param storage Storage responsible for reading/writing the file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(event);
        storage.appendToFile(event);
        ui.printWithLines("Got it. I've added this task:\n  " + event
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
