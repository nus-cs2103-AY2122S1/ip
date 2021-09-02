package duke.commands;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.tasks.Event;

/**
 * Class to handle the event command.
 */
public class EventCommand extends Command {
    private Event event;

    /**
     * Construct a new EventCommand instance with the specified event task stored.
     *
     * @param event The Event task.
     */

    public EventCommand(Event event) {
        this.event = event;
    }

    /**
     * Add the task to the list and print out reply message
     *
     * @param ui The Ui instance for printing
     * @param storage The Storage instance to add the task to
     * @return A boolean of false to indicate the main while loop should not be broken
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        storage.addToList(event);
        storage.save();
        return ui.print("Got it! I've added this event to the list: \n"
                + "  " + event.toString() + '\n'
                + String.format("Now you have %d tasks in the list", storage.getSize()));
    }
}
