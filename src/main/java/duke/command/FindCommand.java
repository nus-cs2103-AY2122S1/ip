package duke.command;

import java.util.List;

import duke.main.TaskList;
import duke.task.Task;

public class FindCommand extends Command {
    private static final String REPLY_HEADER = "The following task(s) match given query '";
    private static final String NO_MATCH = "OOPS! There are no task that matches your query!";
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
        StringBuilder output = new StringBuilder(REPLY_HEADER + description + "':\n");
        int count = 0;
        for (Task currentTask : taskList) {
            if (currentTask.contains(description)) {
                output.append(count + 1).append(". ").append(currentTask).append("\n");
                count++;
            }
        }
        if (count == 0) {
            return NO_MATCH;
        } else {
            return output.toString();
        }
    }
}
