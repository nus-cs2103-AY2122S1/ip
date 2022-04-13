package duke.command;

import duke.processor.Executable;
import duke.processor.TaskList;
import duke.processor.Ui;

/**
 * A class for the 'list' command, which lists all available tasks with type, whether done or not, names and dates.
 * 
 * @author Tianqi-Zhu
 */
public class ListCommand implements Executable {

    /**
     * Prints the task list onto the screen.
     * 
     * @param taskList Current list of tasks.
     */
    public String execute(TaskList taskList) {
        if (taskList.tasks().size() == 0) {
            return "No current task available.";
        } else {
            String out = "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.tasks().size(); i++) {
                int index = i + 1;
                out = out + Ui.SPACE_STRING + index + "." + taskList.tasks().get(i) + "\n";
            }
            return out;
        }
    }
}