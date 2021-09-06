package iris.command;

import java.util.List;

import iris.TaskList;
import iris.task.Task;

public class FindCommand extends Command {
    private String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public String run(TaskList tasks) {
        assert tasks != null;
        List<Task> searchResults = tasks.find(this.searchTerm);
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < searchResults.size(); i++) {
            response.append(String.format("%d. %s\n", i + 1, searchResults.get(i)));
        }
        return response.toString();
    }
}
