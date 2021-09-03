package duke.views.cli.strategies.commands;

import java.util.List;
import java.util.stream.Collectors;

import duke.domain.Task;
import duke.domain.TaskList;



public class FindCommand extends TaskCommand {
    private static Command singleInstance;

    public FindCommand(TaskList taskList) {
        super(taskList);
    }

    public static Command getInstance(TaskList taskList) {
        if (singleInstance == null) {
            singleInstance = new FindCommand(taskList);
        }
        return singleInstance;
    }

    @Override
    public String produce(String query) {
        assert query != null;
        String keyword = query.substring(find.length()).strip();
        List<Task> filteredTasks = userTasks.stream()
                .filter(task -> task.match(keyword))
                .collect(Collectors.toList());
        return listTasksWithMessage("Here are the matching tasks in your list:",
                new TaskList(filteredTasks), "No matches found");
    }
}
