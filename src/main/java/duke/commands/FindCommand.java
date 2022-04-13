package duke.commands;

import java.util.ArrayList;

import duke.TaskList;
import duke.tasks.Task;

/**
 * Command that searches for tasks containing the keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword The keyword entered.
     */
    public FindCommand(String desc, String keyword) {
        super(desc);
        this.keyword = keyword;
    }

    /**
     * Executes the command. Returns the tasks that match the keyword.
     *
     * @param tasks The task list.
     * @return The reply of Duke to the user.
     */
    @Override
    public String execute(TaskList tasks) {
        ArrayList<Task> filtered = tasks.filter(keyword);

        StringBuilder replyBuilder = new StringBuilder();

        if (filtered.size() == 0) {
            replyBuilder.append("Unfortunately, no match can be found.\n");
        } else {
            replyBuilder.append("Here are the tasks you were looking for:\n");
            for (int i = 0; i < filtered.size(); i++) {
                replyBuilder.append((i + 1) + ". " + filtered.get(i) + "\n");
            }
        }

        return replyBuilder.toString();
    }

}
