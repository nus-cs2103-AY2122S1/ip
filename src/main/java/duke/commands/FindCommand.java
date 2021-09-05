package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand given the search query.
     *
     * @param keyword The search query.
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = tasks.filter(keyword);
        String message = String.format("Here are the matching %s in your list:\n%s",
                filteredTasks.size() <= 1 ? "task" : "tasks", filteredTasks);
        return message;
    }
}
