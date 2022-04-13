package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;

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

    /**
     * Executes the FindCommand and find task matching the keyword.
     *
     * @param tasks The collection of tasks.
     * @param storage The storage manager that deals with loading from and
     *                saving into a file.
     * @return The message represent the response.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        TaskList filteredTasks = tasks.filter(keyword);
        String message = String.format("Here are the matching %s in your list:\n%s",
                filteredTasks.size() <= 1 ? "task" : "tasks", filteredTasks);
        return message;
    }
}
