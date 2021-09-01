package bloom.command;

import bloom.app.TaskList;
import bloom.constant.Message;
import bloom.task.Task;

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

    public void run() {
        System.out.println(Message.COMMAND_FIND.getMessage());
        for (int i = 0, j = 1; i < TaskList.size(); ++i) {
            Task t = TaskList.get(i);
            if (t.getDescription().contains(keyword)) {
                System.out.println("\t " + j + ". " + t);
                ++j;
            }
        }
    }
}
