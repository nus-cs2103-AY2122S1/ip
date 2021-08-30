package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Stores and handles operations for a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private int taskCount;

    /**
     * Initialises a new instance of TaskList.
     *
     * @param tasks The given tasks to store in the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.taskCount = tasks.size();
    }

    public ArrayList<Task> get() {
        return this.tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the task list.
     * @return A String output indicating that the task had been added to the task list.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        this.taskCount++;
        String taskCount = (this.taskCount == 1) ? "1 task" : this.taskCount + " tasks";
        return "Got it. I've added this task:\n" + "  " + task.toString()
                + "\n" + "Now you have " + taskCount + " in the list.\n";
    }

    /**
     * Lists the current tasks in the task list.
     *
     * @return A String representing the tasks in the task list.
     */
    public String listTasks() {
        int i = 0;
        String header = "Here are the tasks in your list:\n";
        String result = "";
        for (Task task : this.tasks) {
            if (task != null) {
                result += ++i + "." + task.toString() + "\n";
            } else {
                break;
            }
        }
        if (i == 0) {
            return header + "Your list is currently empty.\n";
        } else {
            return header + result;
        }
    }

    /**
     * Marks a certain task in the task list as done, using its task number.
     *
     * @param taskNumber The number of the task in the task list.
     * @return A String output indicating that the task was mark as done.
     * @throws DukeException If the task number does not exist.
     */
    public String markTaskAsDone(int taskNumber) throws DukeException {
        try {
            Task doneTask = this.tasks.get(taskNumber - 1);
            doneTask.markDone();
            return "Nice! I've marked this task as done:\n" + "  "
                    + doneTask.toString() +"\n";
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! That task does not exist.");
        }
    }

    /**
     * Deletes a certain task from the task list, using its task number.
     *
     * @param taskNumber The number of the task in the task list.
     * @return A String output indicating that the task was deleted.
     * @throws DukeException If the task number does not exist.
     */
    public String deleteTask(int taskNumber) throws DukeException {
        try {
            Task deletedTask = this.tasks.get(taskNumber - 1);
            this.tasks.remove(taskNumber - 1);
            this.taskCount--;
            String taskCount = (this.taskCount == 1) ? "1 task" : this.taskCount + " tasks";
            return "Noted. I've removed this task:\n" + "  "
                    + deletedTask.toString() + "\n" + "Now you have " + taskCount
                    + " in the list.\n";

        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! That task does not exist.");
        }
    }

    /**
     * Takes in a String and returns a list of the tasks in the task list that contain the given String
     * within their descriptions.
     *
     * @param string The given String.
     * @return A String representing the list of tasks that contain the given String.
     */
    public String find(String string) {
        ArrayList<Task> found = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.hasSubString(string)) {
                found.add(task);
            }
        }

        if (found.size() == 0) {
            return "No matching tasks were found.\n";
        } else {
            StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
            int i = 0;
            for (Task task : found) {
                result.append(++i).append(".").append(task.toString()).append("\n");
            }
            return result.toString();
        }
    }
}
