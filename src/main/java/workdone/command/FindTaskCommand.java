package workdone.command;

import workdone.data.Storage;
import workdone.data.TaskList;

/**
 * Represents a command that finds a task with keyword. A subclass of Command.
 */
public class FindTaskCommand extends Command {
    /** The keyword to be found */
    private final String keyword;

    /**
     * Constructor of the class `FindTaskCommand`.
     *
     * @param keyword The keyword to be found.
     */
    public FindTaskCommand(String keyword) {
        super("find");
        this.keyword = keyword;
        this.message = "Here are the tasks I found:\n";
    }

    /**
     * Executes the command. Finds a list of tasks that contain the keyword and updates the message.
     *
     * @param tasks A list of tasks.
     * @param storage An instance of Storage that can read from and write to the hard disk.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        this.message += tasks.getFilteredListAsString(task -> task.containsKeyword(this.keyword));
    }
}
