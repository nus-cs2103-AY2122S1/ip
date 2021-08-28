package duke.commands;

import duke.DateTimeHandler;
import duke.Event;
import duke.Storage;
import duke.TaskList;
import duke.UI;


import java.time.LocalDateTime;

/**
 * Encapsulates the event command, used to create event tasks
 */
public class EventCommand extends Command {

    public EventCommand(String arguments) {
        super(arguments);
    }

    @Override
    public Command of(String arguments) {
        return new EventCommand(arguments);
    }

    @Override
    public String execute(TaskList tl, Storage s, UI ui, DateTimeHandler dth) {
        String args = super.getArguments();
        if (args.length() == 0) {
            return "Please enter the name of the task after event";

        }
        if (!args.contains("/at")) {
            return "Please enter the start date of the task after /at";
        }
        String[] parts = args.split(" /at ");
        LocalDateTime startDate = dth.parseDate(parts[1]);
        if (startDate == null) {
            return dth.invalidFormat();
        }
        Event e = new Event(parts[0], false, startDate);
        tl.addToList(e);
        return ui.formatMessage(tl.taskAddedMessage(e));
    }

    @Override
    public String startsWith() {
        return "event";
    }
}
