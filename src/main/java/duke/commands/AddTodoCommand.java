package duke.commands;

import java.io.IOException;

import duke.TaskList;
import duke.tasks.Todo;

/**
 * Command that adds to-do to task list.
 */
public class AddTodoCommand extends Command {

    /**
     * Constructor for AddTodoCommand.
     *
     * @param desc Description for to-do.
     */
    public AddTodoCommand(String desc) {
        super(desc);
    }

    /**
     * Executes the command. Adds to-do to task list.
     *
     * @param tasks The task list.
     * @return The reply of Duke to the user.
     */
    @Override
    public String execute(TaskList tasks) throws IOException {
        Todo todo = new Todo(super.getDesc(), false);
        tasks.add(todo);

        StringBuilder replyBuilder = new StringBuilder();

        replyBuilder.append("Got it. I've added this task:\n");
        replyBuilder.append(todo + "\n");

        if (tasks.size() == 1) {
            replyBuilder.append("Now you have 1 task in the list. \n");
        } else {
            replyBuilder.append("Now you have " + tasks.size() + " tasks in the list. \n");
        }
        return replyBuilder.toString();
    }
}
