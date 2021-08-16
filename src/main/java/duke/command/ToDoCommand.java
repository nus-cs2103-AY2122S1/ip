package duke.command;

import duke.Duke;
import duke.Response;
import duke.task.ToDoTask;

/**
 * A command to add a todo task into main DukeList.
 */
public class ToDoCommand implements Command {

    @Override
    public void exec(String args) {
        if (args.isEmpty()) {
            new Response("☹ OOPS!!! The description of a todo cannot be empty.").print();
        } else {
            Duke.getList().addWithResponse(new ToDoTask(args)).print();
        }
    }

    @Override
    public String getLabel() {
        return "todo";
    }

}
