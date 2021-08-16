package duke.command;

import duke.Duke;
import duke.task.EventTask;

/**
 * A command to add an event task into main DukeList.
 */
public class EventCommand implements Command {

    @Override
    public void exec(String args) {
        Duke.getList().addWithResponse(new EventTask(args)).print();
    }

    @Override
    public String getLabel() {
        return "event";
    }

}
