package duke.classes.commands;

import java.util.List;

import duke.classes.TaskList;
import duke.classes.tasks.Task;

/**
 * Duke class in-charge of generating user input to be returned by the GUI
 */
public class DukeUI {
    /**
     * Unary Function that prints list of task
     *
     * @param taskList List of Duke.tasks to print
     * @return String to be output by Duke
     */
    static String printList(TaskList taskList) {
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

    /**
     * Unary Function that marks task as Done and prints a confirmation message
     *
     * @param task Task that is marked as complete
     * @return String to be output by Duke
     */
    static String completeTask(Task task) {
        return "Nice! I've marked this task as done:\n\t" + task.toString();
    }

    /**
     * Binary Function that deletes a task and prints a confirmation message
     *
     * @param task Task that was removed
     * @param size Remaining size of the list
     * @return String to be output by Duke
     */
    static String removeTask(Task task, int size) {
        return "Noted. I've removed this task: \n\t" + task.toString()
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Unary Function that prints the most recently added Task
     *
     * @param lst List of Task that was added to
     * @return String to be output by Duke
     */
    static String printTask(TaskList lst) {
        int lastItem = lst.last();
        return "Got it. I've added this task:\n\t" + lst.get(lastItem).toString()
                + "\nNow you have " + lst.size() + " tasks in the list.";
    }

    /**
     * Unary function printing the post-filtered list of tasks
     *
     * @param tasks list of tasks post-filter
     * @return String to be output by Duke
     */
    public static String printFiltered(List<Task> tasks) {
        String output = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            output += (i + 1) + "." + task.toString() + "\n";
        }
        return output;
    }

    /**
     * Nullary function printing the user guide for Duke
     *
     * @return String to be output by Duke
     */
    public static String printHelp(String keyword) throws IllegalArgumentException {
        if (keyword == null) {
            return "use help to get the usage of commands";
        } else {
            return DukeParser.Keywords.valueOf(keyword).getHelpText();
        }
    }
}

