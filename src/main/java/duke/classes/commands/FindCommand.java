package duke.classes.commands;

import java.util.List;

import duke.classes.TaskList;
import duke.classes.tasks.Task;

public class FindCommand extends Command {
    private String[] words;

    /**
     * Class Constructor
     *
     * @param words The User input to process
     */
    public FindCommand(String[] words) {
        this.words = words;
    }

    /**
     * Filters the task list using the filter provided
     *
     * @return String to be output by Duke
     */
    public String execute() {
        List<Task> tasks = TaskList.filter(words[1]);
        String output = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            output += (i + 1) + "." + task.toString() + "\n";
        }
        return output;
    }
}
