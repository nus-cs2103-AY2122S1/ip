package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Task;

public class FindTaskCommand implements Command {

    private final String query;

    public FindTaskCommand(String query) {
        this.query = query;
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks matching your query! \n");
        int matchingTaskCount = 0;
        for (int i = 0; i < taskList.numberOfTasks(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(query)) {
                sb.append((matchingTaskCount + 1) + ". " + task + "\n");
                matchingTaskCount++;
            }
        }
        return sb.toString();
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
