import java.util.ArrayList;

/**
 * Encapsulates a task in a task list.
 */
public class Task {

    private final static ArrayList<Task> taskList = new ArrayList<>();
    private final String description;
    private boolean completed;

    /**
     * Add a task to the task list
     *
     * @param t Task to be added
     * @return String notifying the task added.
     */
    public static String[] addTask(Task t) {
        taskList.add(t);
        return new String[] {
                "Got it. I've added this task:",
                t.toString(),
                "Now you have "
                        + taskList.size()
                        + " tasks in the list."
        };
    }

    /**
     * Marks a task as completed.
     *
     * @param index Numerical index of task completed.
     * @return String reporting that task is marked done.
     */
    public static String[] markTask(int index) {
        try {
            if (invalidID(index)) {
                throw new DukeException("Task ID out of range!");
            }
            Task t = taskList.get(index);
            t.completed = true;
            return new String[] {
                    "Nice! I've marked this task as done:",
                    t.toString()
            };
        } catch (DukeException e){
            return new String[] {e.getMessage()};
        }
    }

    /**
     * Delete a task from the list.
     *
     * @param index Numerical index of task to be removed
     * @return String reporting that task is removed.
     */
    public static String[] deleteTask(int index) {
        try {
            if (invalidID(index)) {
                throw new DukeException("Task ID out of range!");
            }
            Task t = taskList.remove(index);
            return new String[] {
                    "Noted. I've removed this task:",
                    t.toString(),
                    "Now you have "
                            + taskList.size()
                            + " tasks in the list."
            };
        } catch (DukeException e) {
            return new String[] {e.getMessage()};
        }
    }

    /**
     * Get an array of strings with tasks numbered.
     *
     * @return A String array with numbered tasks.
     */
    public static String[] getTaskStrings() {
        String[] taskStrings = new String[taskList.size() + 1];
        taskStrings[0] = "Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            taskStrings[i + 1] = (i + 1) + "." + taskList.get(i).toString();
        }
        return taskStrings;
    }

    /**
     * Checks if an ID is an invalid index for the task list.
     *
     * @param ID Integer index of the task.
     * @return Boolean representing validity of ID.
     */
    private static boolean invalidID(int ID) {
        return ID < 0 || ID >= taskList.size();
    }

    /**
     * Constructor for a Task
     *
     * @param description String description of task.
     */
    protected Task(String description) {
        this.description = description;
        this.completed = false;
    }

    /**
     * Gets the status of a task as a checkbox.
     *
     * @return A string representing status of task.
     */
    private String getStatus() {
        return "[" + (completed ? "X" : " ") + "]";
    }

    /**
     * String representation of task.
     *
     * @return String representing task.
     */
    @Override
    public String toString() {
        return getStatus() + " " + description;
    }
}
