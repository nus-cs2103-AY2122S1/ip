package duke.command;

import java.util.List;

import duke.TaskManager;
import duke.Ui;
import duke.task.Task;

/**
 * Represents the command "find x" to find tasks containing specified keyword x and outputs them to screen.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        List<Task> tasks = taskManager.find(this.keyword);
        StringBuilder s = new StringBuilder();
        s.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            s.append(String.format("%d.%s\n", i, tasks.get(i)));
        }
        ui.reply(s.toString());
    }
}
