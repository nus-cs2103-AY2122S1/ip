package bot.commands;

import static bot.constants.GlobalStringFormats.LINE_BREAK;
import static bot.constants.GlobalStringFormats.TAB_SPACES;

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
        this.index = Integer.parseInt(indexString) - 1;
    }

    /**
     * Executes the Command and returns a String.
     *
     * @return A String to show to the user after execution of the DoneCommand.
     */
    @Override
    public String execute() {
        Task toBeDone = TaskList.showTasks().get(index);
        if (toBeDone.getStatusIcon().equals("X")) {
            return " This task has been marked as done";
        }
        toBeDone.markAsDone();
        return LINE_BREAK + "Nice! I've marked this task as done:" + LINE_BREAK + TAB_SPACES + toBeDone;
    }
}
