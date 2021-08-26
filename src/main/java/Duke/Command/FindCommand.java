package Duke.Command;

import java.util.List;

import Duke.Main.TaskList;
import Duke.Task.Task;

public class FindCommand extends Command {

    private String description;
    private List<Task> taskList;
    public FindCommand(String description, TaskList taskList) {
        super(description, taskList);
        this.description = description;
        this.taskList = taskList.getTaskList();
    }

    @Override
    public String reply() {
        StringBuilder output = new StringBuilder("The following task(s) matches query '" +
                description + "':\n");
        int count = 0;
        for (Task currentTask : taskList) {
            if (currentTask.getTaskName().contains(description)) {
                output.append(count + 1).append(". ").append(currentTask).append("\n");
                count++;
            }
        }
        if (count == 0) {
            return "OOPS! There are no task that matches your query!";
        } else {
            return output.toString();
        }
    }
}
