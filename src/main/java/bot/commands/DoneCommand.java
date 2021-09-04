package bot.commands;

import bot.tasks.Task;
import bot.utility.TaskList;

/**
 * Represents a command to mark tasks as done.
 */
public class DoneCommand extends Command {
    private final int index;

    /**
     * Returns a DoneCommand;
     *
     * @param indexString A String containing an integer value;
     */
    public DoneCommand(String indexString) {
        this.index = Integer.parseInt(indexString);
    }

    /**
     * Executes the Command and returns a String.
     *
     * @return A String to show to the user after execution of the Command.
     */
    @Override
    public String execute() {
        Task toBeDone = TaskList.showTasks().get(index);
        if (toBeDone.getStatusIcon().equals("X")) {
            return "\t This task has been marked as done";
        }
        toBeDone.markAsDone();
        return "\n\t Nice! I've marked this task as done:\n\t\t" + toBeDone;
    }
}
