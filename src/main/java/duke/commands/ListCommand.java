package duke.commands;

import duke.TaskList;
import duke.tasks.Task;

/**
 * Command that lists the tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Constructor for ListCommand.
     *
     * @param desc
     */
    public ListCommand(String desc) {
        super(desc);
    }

    /**
     * Executes the command. Adds deadline to task list. Updates the save file.
     *
     * @param tasks the task list.
     */
    @Override
    public String execute(TaskList tasks) {
        StringBuilder replyBuilder = new StringBuilder();
        if (tasks.size() == 0) {
            return "You have not added anything to the list.";
        } else {
            for (int i = 1; i < tasks.size() + 1; i++) {
                Task task = tasks.get(i);
                if (i < 10) {
                    replyBuilder.append(i + ". " + "   " + task.toString() + "\n");
                } else {
                    replyBuilder.append(i + ". " + " " + task.toString() + "\n");
                }
            }
        }
        return replyBuilder.toString();
    }
}
