package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class FindCommand extends Command {
    private final String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Finds all task names that contains of search term.
     *
     * @param storage storage instance initialised when duke is created.
     * @param taskList task list instance initialised when duke is created.
     * @param ui ui instance initialised when duke is created.
     * @return String to indicate if tasks has been searched successfully.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        Task[] tasks = taskList.findTasksWithName(this.searchTerm);
        if (tasks.length < 1) {
            return ui.respond(String.format("No task found with search term %s", this.searchTerm));
        } else {
            return ui.findResponse(tasks);
        }
    }
}
