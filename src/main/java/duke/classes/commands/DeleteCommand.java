package duke.classes.commands;

import static java.lang.Integer.parseInt;

import duke.classes.TaskList;
import duke.classes.exceptions.DukeException;
import duke.classes.tasks.Task;

public class DeleteCommand {
    private String[] words;
    private TaskList taskList;

    /**
     * Class Constructor
     *
     * @param taskList The tasklist to be modified
     * @param words The User input to process
     */
    public DeleteCommand(TaskList taskList, String[] words) {
        this.taskList = taskList;
        this.words = words;
    }

    /**
     * Removes a target task and returns confirmation
     *
     * @return String to be output by Duke
     */
    public String execute() throws IllegalArgumentException, DukeException {
        if (taskList.checkForInt(words[1])) {
            int index = parseInt(words[1]) - 1;
            if (index < 0) {
                throw new DukeException("!!! Please input a number greater than 0 !!!");
            } else if (index >= taskList.size()) {
                throw new DukeException("!!! The number you input exceeds the size of the list !!!");
            }
            Task task = taskList.remove(index);
            return "Noted. I've removed this task: \n\t" + task.toString()
                    + "\nNow you have " + taskList.size() + " tasks in the list.";
        }
        return "!!! Please input command as delete [int] !!!";
    }
}



