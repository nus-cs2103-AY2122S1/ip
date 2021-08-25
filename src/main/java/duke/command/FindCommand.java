package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class FindCommand extends Command{
    private final String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        Task[] tasks = taskList.findTasksWithName(this.searchTerm);
        if (tasks.length < 1) {
            ui.respond(String.format("No task found with search term %s", this.searchTerm));
        } else {
            ui.findResponse(tasks);
        }
    }
}
