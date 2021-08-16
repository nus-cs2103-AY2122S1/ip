package duke.command;

import duke.Duke;
import duke.exception.InvalidArgumentException;
import duke.task.ToDoTask;

/**
 * A command to add a todo task into main DukeList.
 */
public class ToDoCommand implements Command {

    @Override
    public void exec(String args) {
        if (args == null || args.isEmpty())
            throw new InvalidArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        Duke.getList().addWithResponse(new ToDoTask(args)).print();
    }

    @Override
    public String getLabel() {
        return "todo";
    }

}
