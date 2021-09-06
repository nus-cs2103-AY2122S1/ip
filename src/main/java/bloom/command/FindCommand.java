package bloom.command;

import bloom.app.TaskList;
import bloom.constant.Message;
import bloom.task.Task;

/**
 * Represents a find command which find tasks when being given a keyword.
 */
public class FindCommand extends Command {

    /** The input keyword. */
    private final String keyword;

    /**
     * Constructor for a FindCommand.
     *
     * @param keyword the input keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds a task based on input keyword.
     */
    public String run() {
        StringBuilder response = new StringBuilder(
                Message.COMMAND_FIND.getMessage());
        for (int i = 0, j = 1; i < TaskList.size(); ++i) {
            Task t = TaskList.get(i);
            if (t.getDescription().contains(keyword)) {
                response.append("\t ")
                        .append(j).append(". ")
                        .append(t).append("\n");
                ++j;
            }
        }
        return response.toString();
    }
}
