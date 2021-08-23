package duke.command;

import duke.DukeList;
import duke.exception.InvalidArgumentException;
import duke.task.ToDoTask;

/**
 * A command to add a todo task into main DukeList.
 */
public class ToDoCommand implements Command {
    private DukeList duke;

    public ToDoCommand(DukeList duke) {
        this.duke = duke;
    }

    @Override
    public void exec(String args) {
        if (args == null || args.isEmpty())
            throw new InvalidArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        this.duke.addWithResponse(new ToDoTask(args)).print();
    }

    @Override
    public String getLabel() {
        return "todo";
    }

}
