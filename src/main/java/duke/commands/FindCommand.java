package duke.commands;

import java.util.*;
import duke.tasks.Task;
import duke.utils.*;

public class FindCommand extends Command {
    String keyword;

    public FindCommand(String input) {
        keyword = input.split("find ")[1];
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String tasksToPrint = "";
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
