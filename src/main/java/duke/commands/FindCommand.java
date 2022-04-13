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
     * Returns String object to describe the relevant tasks from the given TaskList.
     *
     * @param des User input into the Duke chat-box.
     * @return String representation of FindCommand.
     * @throws DukeException If done command is not formatted properly
     *                       or if task has already been completed.
     */
    public String execute(String des, TaskList tList) throws DukeException {
        checkValidDes(des);
        if (des.equals("find") || des.equals("find ")) {
            throw new DukeException("The \"find\" command accepts at least one search input.\nPlease add a keyword to search for.");
        }
        String result = getRelevantTasks(des, tList);
        return result;
    }

    /**
     * Returns String representation of relevant tasks that match the keywords being searched.
     *
     * @param des User input into the Duke chat-box.
     * @param tList TaskList object used to keep track of all tasks.
     * @return String object representing tasks that match keywords.
     */
    private String getRelevantTasks(String des, TaskList tList) {
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
