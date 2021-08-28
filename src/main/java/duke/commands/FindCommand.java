package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class FindCommand extends Command {
    public FindCommand() {
        super("find");
    }

    /**
     * The execute() method in findCommand filters out the relevant tasks from the TaskList.
     * @param des the user input into the Duke chat-box.
     * @return String object to describe execution of FindCommand.
     * @throws DukeException if done command is not formatted properly
     *                       or if task has already been completed.
     */
    public String execute(String des, TaskList tList) throws DukeException {
        if (des.equals("find") || des.equals("find ")) {
            throw new DukeException("The \"find\" command accepts at least one search input.\nPlease add a keyword to search for.");
        }
        String searchInput = des.substring(des.indexOf(' ') + 1);
        String result = "";
        result = result + "Here are your tasks that contain \"" + searchInput + "\"\n";
        ArrayList<Task> tasks = tList.getTaskList();
        int count = 1;
        for (Task t : tasks) {
            String description = t.formatString();
            if (description.contains(searchInput)) {
                result = result + count + ". " + t.formatString() + "\n";
                count++;
            }
        }
        return result;
    }
}
