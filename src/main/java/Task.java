import java.util.ArrayList;

public class Task {
    private final static ArrayList<Task> taskList = new ArrayList<>();

    private final String description;

    /**
     * Add a task.
     *
     * @param description Task description as string.
     */
    public static void addTask(String description) {
        taskList.add(new Task(description));
    }

    /**
     * Get an array of strings with tasks numbered.
     *
     * @return A String array with numbered tasks.
     */
    public static String[] getTaskStrings() {
        String[] taskStrings = new String[taskList.size()];
        for (int i = 0; i < taskList.size(); i++) {
            taskStrings[i] = (i + 1) + ". " + taskList.get(i).toString();
        }
        return taskStrings;
    }

    /**
     * Constructor for a Task
     *
     * @param description String description of task.
     */
    private Task(String description) {
        this.description = description;
    }

    /**
     * String representation of task.
     *
     * @return String representing task.
     */
    @Override
    public String toString() {
        return description;
    }
}
