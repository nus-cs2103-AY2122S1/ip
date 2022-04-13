package duke.command;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.Storage;

/**
 * This class abstracts the find command that the user wants to execute.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private final String keyword;

    /**
     * Constructs a FindCommand that will delete the given task number when executed.
     *
     * @param keyword The String to be found.
     */
    public FindCommand(String keyword) {
        assert !keyword.isEmpty();
        this.keyword = keyword;
    }

    /**
     * Execute the command to find a given keyword in tasks.
     *
     * @param tasks   The TaskList of the Duke instance.
     * @param storage The storage handler of the Duke instance.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert !keyword.isEmpty();
        String str = "Here are the matching tasks in your list:";
        StringBuilder stringBuilder = new StringBuilder(str);
        int index = 1;
        for (Task task : tasks.getAllTasks()) {
            if (!task.toString().contains(keyword)) {
                continue;
            }
            stringBuilder.append("\n").append(index++).append(".").append(task);
        }
        return stringBuilder.toString();
    }
}
