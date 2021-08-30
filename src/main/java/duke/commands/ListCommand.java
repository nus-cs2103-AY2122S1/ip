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
     * Returns if the command is the exit command.
     *
     * @return false since this command is not the exit command.
     */
    @Override
    public boolean isExit() {
        return false;
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
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                replyBuilder.append((i + 1) + ". " + " " + task.toString() + "\n");
            }
        }
        return replyBuilder.toString();
    }
}
