package duke;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * Encapsulates a TaskList. Stores an array of tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskArray;

    /**
     * Constructs a TaskList.
     */
    public TaskList() {
        taskArray = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param t Task to be added
     * @return String notifying the task added.
     */
    public String[] addTask(Task t) {
        taskArray.add(t);
        return new String[] {
            "Got it. I've added this task:",
            t.toString(),
            "Now you have "
                    + taskArray.size()
                    + " tasks in the list."
        };
    }

    /**
     * Marks a task as completed.
     *
     * @param index Numerical index of task completed.
     * @return String reporting that task is marked done.
     * @throws DukeException Thrown if index out of range.
     */
    public String[] markTask(int index) throws DukeException {
        if (isInvalidID(index)) {
            throw new DukeException("Task ID out of range!");
        }
        Task t = taskArray.get(index);
        t.setCompleted();
        assert t.toString().contains("[X]") : "Task t should be marked as completed";
        return new String[] {
            "Nice! I've marked this task as done:",
            t.toString()
        };
    }

    /**
     * Deletes a task from the list.
     *
     * @param index Numerical index of task to be removed
     * @return String reporting that task is removed.
     * @throws DukeException Thrown if index out of range.
     */
    public String[] deleteTask(int index) throws DukeException {
        if (isInvalidID(index)) {
            throw new DukeException("Task ID out of range!");
        }
        Task t = taskArray.remove(index);
        return new String[] {
            "Noted. I've removed this task:",
            t.toString(),
            "Now you have "
                    + taskArray.size()
                    + " tasks in the list."
        };
    }

    /**
     * Finds tasks which match a query.
     *
     * @param query String to check for in tasks.
     * @return An array of strings with numbered tasks.
     */
    public String[] findMatchingTasks(String query) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskArray) {
            if (task.toString().contains(query)) {
                matchingTasks.add(task);
            }
        }
        String[] taskStrings = new String[matchingTasks.size() + 1];
        taskStrings[0] = "Here are the matching tasks in your list:";
        for (int i = 0; i < matchingTasks.size(); i++) {
            taskStrings[i + 1] = (i + 1) + "." + matchingTasks.get(i).toString();
        }
        return taskStrings;
    }

    /**
     * Gets tasks for a given date.
     *
     * @param time Date to find tasks for.
     * @return An array of strings with matching tasks.
     */
    public String[] getSchedule(LocalDate time) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskArray) {
            if (task.isScheduledFor(time)) {
                matchingTasks.add(task);
            }
        }
        String[] taskStrings = new String[matchingTasks.size() + 1];
        taskStrings[0] = "Here are tasks scheduled for this date:";
        for (int i = 0; i < matchingTasks.size(); i++) {
            taskStrings[i + 1] = (i + 1) + "." + matchingTasks.get(i).toString();
        }
        return taskStrings;
    }

    /**
     * Gets an array of strings with tasks numbered.
     *
     * @return A String array with numbered tasks.
     */
    public String[] getTaskStrings() {
        String[] taskStrings = new String[taskArray.size() + 1];
        taskStrings[0] = "Here are the tasks in your list:";
        for (int i = 0; i < taskArray.size(); i++) {
            taskStrings[i + 1] = (i + 1) + "." + taskArray.get(i).toString();
        }
        return taskStrings;
    }

    /**
     * Checks if an ID is an invalid index for the task list.
     *
     * @param id Integer index of the task.
     * @return Boolean representing validity of ID.
     */
    private boolean isInvalidID(int id) {
        return id < 0 || id >= taskArray.size();
    }
}
