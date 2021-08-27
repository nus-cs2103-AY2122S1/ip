package Duke;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindTasksCommand implements ICommand {

    private String keyword;

    public FindTasksCommand(String input) {
        keyword = input.substring(5);
    }

    @Override
    public void execute(TaskManager tm, Ui ui, Storage storage) {
        ArrayList<Task> originalTasks = new ArrayList<Task>();
        originalTasks.addAll(tm.getTasks());

        ArrayList<Task> filteredTasks = (ArrayList<Task>) originalTasks.stream().filter(task -> task.getName().contains(keyword)).collect(Collectors.toList());

        ui.printTasks(filteredTasks);
    }
}
