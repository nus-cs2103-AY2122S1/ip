package duke.classes.commands;

import java.util.List;

import duke.classes.TaskList;
import duke.classes.tasks.Task;

public class ListCommand extends Command {
    private TaskList taskList;
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Execution returns list of tasks to be done
     *
     * @return String to be output by Duke
     */
    public String execute() {
        String output;
        if (taskList.isEmpty()) {
            output = "There are no tasks at the moment";
        } else {
            output = "Here are the tasks in your list:\n";
            List<Task> lst = taskList.getTaskList();
            for (int i = 0; i < lst.size(); i++) {
                Task task = lst.get(i);
                output += (i + 1) + "." + task.toString() + "\n";
            }
        }
        return output;
    }
}
