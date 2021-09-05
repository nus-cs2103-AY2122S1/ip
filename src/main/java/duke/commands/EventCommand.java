package duke.commands;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.DateTimeHandler;
import duke.Event;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
    public String execute(TaskList tl, Storage s, Ui ui, DateTimeHandler dth) {
        String args = super.getArguments();
        if (args.length() == 0) {
            return "Please enter the name of the task after event";

        }
        if (!args.contains("/at")) {
            return "Please enter the start date of the task after /at";
        }
        String description, dateString;
        String[] tags = new String[0];
        if (args.contains("/t")) {
            ArrayList<String> parts = Parser.parseCommandArguments(args, "at", "t");
            description = parts.get(0);
            dateString = parts.get(1);
            tags = parts.get(2).split(" ");
        } else {
            ArrayList<String> parts = Parser.parseCommandArguments(args, "at");
            description = parts.get(0);
            dateString = parts.get(1);
        }
        LocalDateTime startDate = dth.parseDate(dateString);
        if (startDate == null) {
            return dth.invalidFormat();
        }
        Event e = new Event(description, false, startDate, tags);
        tl.addToList(e);
        return ui.formatMessage(tl.taskAddedMessage(e));
    }

    @Override
    public String getCommandPrefix() {
        return "event";
    }
}
