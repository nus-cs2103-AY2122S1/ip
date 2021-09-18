package duke.command;

import duke.Storage;
import duke.UI;
import duke.error.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command which finds tasks given a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand object.
     *
     * @param keyword Keyword used in searching the tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns tasks that contain keyword.
     *
     * @param tasks List of tasks.
     * @param ui UI object.
     * @param storage Storage object.
     * @return The execution result.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (keyword.equals("")) {
            throw new DukeException("OOPS!! find needs a keyword.");
        }

        String list = "";

        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                list += String.format("%d.%s\n", i + 1, task);
            }
        }

        return ui.findResponse(list);
    }
}
