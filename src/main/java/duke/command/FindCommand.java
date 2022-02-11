package duke.command;

import duke.Storage;
import duke.task.TaskList;

/**
 * Represents the command when the user wants to find tasks in the task list.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    /** The user's input to be searched for */
    private String keyword;

    /**
     * Constructs a FindCommand object.
     *
     * @param keyword The user's input to be searched for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find a task from the task list.
     *
     * @param tasks The user's task list.
     * @param storage Storage object used to save the task list to the data file.
     * @return A messsage listing out all the tasks that match the user's input.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.findTask(keyword);
    }
}
