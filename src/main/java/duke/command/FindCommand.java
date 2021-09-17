package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;

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
     * @return String to indicate if tasks has been searched successfully.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        Task[] tasks = taskList.findTasksWithName(this.searchTerm);
        if (tasks.length < 1) {
            return String.format("No task found with search term %s", this.searchTerm);
        } else {
            StringBuilder res = new StringBuilder("Can do! \n");
            res.append("Found ").append(tasks.length).append(" tasks");
            for (Task task : tasks) {
                res.append("\n").append(task.toString());
            }
            return res.toString();
        }
    }
}
