package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Task;

public class FindTaskCommand implements Command {

    private String query;

    public FindTaskCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        System.out.println("Here are the tasks matching your query!");
        int matchingTaskCount = 0;
        for (int i = 0; i < taskList.numberOfTasks(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(query)) {
                System.out.println((matchingTaskCount + 1) + ". " + task);
                matchingTaskCount++;
            }
        }
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
