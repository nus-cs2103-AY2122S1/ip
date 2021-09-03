package duke.command;

import java.util.List;
import java.util.stream.Collectors;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * FindCommand class encapsulates command to help find tasks.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword Keyword to find a task.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, boolean shouldPrintMessage) {
        List<Task> filtered =
            tasks.stream().filter(task -> task.getDescription().contains(keyword)).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < filtered.size(); i++) {
            String item = i + 1 + ". " + filtered.get(i) + "\n";
            sb.append(item);
        }
        String message = sb.toString();
        if (shouldPrintMessage) {
            ui.showMessage(message);
        }
        return message;
    }
}


