package duke.command;

import duke.Duke;
import duke.exception.InvalidArgumentException;
import duke.task.DeadlineTask;

/**
 * A command to add a deadline task into main DukeList.
 */
public class DeadlineCommand implements Command {
    private Duke duke;

    public DeadlineCommand(Duke duke) {
        this.duke = duke;
    }

    @Override
    public void exec(String args) {
        if (args == null || args.isEmpty())
            throw new InvalidArgumentException("☹ OOPS!!! The description of a deadline cannot be empty.");
        this.duke.getList().addWithResponse(new DeadlineTask(args)).print();
    }

    @Override
    public String getLabel() {
        return "deadline";
    }

}
