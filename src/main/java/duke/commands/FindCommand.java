package duke.commands;
import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;

import java.util.ArrayList;


public class FindCommand extends Command {
    String keyword;

    public FindCommand(String input) {
        keyword = input.split("find ")[1];
    }
    /**
     * Finds a list of tasks that matches the keyword and return it up to duke to be printed in the GUI
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            String tasksToPrint = "";
            assert (tasksToPrint == "");
            ArrayList<Task> userInputs = tasks.getTasks();
            for (int i = 0; i < userInputs.size(); i++) {
                Task task = userInputs.get(i);
                String description = task.getDescription();
                if (description.contains(keyword)) {
                    tasksToPrint += (task + " ");
                }
            }
            return tasksToPrint;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
