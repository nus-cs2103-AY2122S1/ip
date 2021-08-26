package duke.commands;

import duke.Ui;
import duke.storage.Storage;
import duke.tasks.Event;

public class EventCommand extends Command {
    Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public boolean execute(Ui ui, Storage storage) {
        storage.addToList(event);
        ui.print("Got it! I've added this event to the list: \n"
                + "  " + event.toString() + '\n'
                + String.format("Now you have %d tasks in the list", storage.getSize()));
        storage.save();
        return false;
    }
}
