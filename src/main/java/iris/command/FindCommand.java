package iris.command;

import java.util.List;

import iris.IrisException;
import iris.TaskList;
import iris.Ui;
import iris.task.Task;

public class FindCommand extends Command {
    private String searchTerm;
    private List<Task> searchResults;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public void runSilently(TaskList tasks) throws IrisException {
        this.searchResults = tasks.find(this.searchTerm);
    }

    @Override
    public void say(TaskList tasks, Ui ui) {
        for (int i = 0; i < searchResults.size(); i++) {
            ui.say(String.format("%d. %s", i + 1, searchResults.get(i)), i == 0);
        }
    }
}
