package duke.command;

import duke.DukeList;
import duke.exception.InvalidArgumentException;
import duke.task.EventTask;

/**
 * A command to add an event task into main DukeList.
 */
public class EventCommand implements Command {
    private DukeList duke;

    public EventCommand(DukeList duke) {
        this.duke = duke;
    }

    @Override
    public void exec(String args) {
        if (args == null || args.isEmpty())
            throw new InvalidArgumentException("☹ OOPS!!! The description of an event cannot be empty.");
        this.duke.addWithResponse(new EventTask(args)).print();
    }

    @Override
    public String getLabel() {
        return "event";
    }

}
