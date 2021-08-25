import java.util.ArrayList;
/**
 * Encapsulates a TaskList. Stores an array of tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskArray;

    /**
     * Constructor for a TaskList.
     */
    public TaskList() {
        taskArray = new ArrayList<Task>();
    }

    /**
     * Add a task to the task list.
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
     * Mark a task as completed.
     *
     * @param index Numerical index of task completed.
     * @return String reporting that task is marked done.
     * @throws DukeException Thrown if index out of range.
     */
    public String[] markTask(int index) throws DukeException {
        if (invalidID(index)) {
            throw new DukeException("Task ID out of range!");
        }
        Task t = taskArray.get(index);
        t.markCompleted();
        return new String[] {
                "Nice! I've marked this task as done:",
                t.toString()
        };
    }

    /**
     * Delete a task from the list.
     *
     * @param index Numerical index of task to be removed
     * @return String reporting that task is removed.
     * @throws DukeException Thrown if index out of range.
     */
    public String[] deleteTask(int index) throws DukeException {
        if (invalidID(index)) {
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
     * Get an array of strings with tasks numbered.
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
     * Check if an ID is an invalid index for the task list.
     *
     * @param ID Integer index of the task.
     * @return Boolean representing validity of ID.
     */
    private boolean invalidID(int ID) {
        return ID < 0 || ID >= taskArray.size();
    }


}
