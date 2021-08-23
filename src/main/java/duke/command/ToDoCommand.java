package duke.command;

import duke.Duke;
import duke.exception.InvalidArgumentException;
import duke.task.ToDoTask;

/**
 * A command to add a todo task into main DukeList.
 */
public class ToDoCommand implements Command {
    private Duke duke;

    public ToDoCommand(Duke duke) {
        this.duke = duke;
    }

    @Override
    public void exec(String args) {
        if (args == null || args.isEmpty())
            throw new InvalidArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        this.duke.getList().addWithResponse(new ToDoTask(args)).print();
    }

    @Override
    public String getLabel() {
        return "todo";
    }

}
