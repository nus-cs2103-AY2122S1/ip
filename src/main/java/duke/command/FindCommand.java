package duke.command;

import java.util.List;

import duke.main.TaskList;
import duke.task.Task;

public class FindCommand extends Command {

    private final String description;
    private final List<Task> taskList;

    /**
     * Constructor for the Find Command class
     * @param description description of what to find
     * @param taskList task list to be modified
     */
    public FindCommand(String description, TaskList taskList) {
        this.description = description;
        this.taskList = taskList.getTaskList();
    }

    @Override
    public String reply() {
        StringBuilder output = new StringBuilder("The following task(s) matches query '"
                + description + "':\n");
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
