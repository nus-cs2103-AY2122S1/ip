package duke;

/**
 * Class responsible for displaying replies to the user.
 */
public class Ui {
    /**
     * Returns welcome message when the user opens Duke.
     *
     * @return Welcome message.
     */
    public static String welcome() {
        return "Stay on track with Duke!\nHow can I help you?";
    }

    /**
     * Returns a goodbye message.
     *
     * @return Goodbye message.
     */
    public static String bye() {
        return "¡Adiós! See you soon!";
    }

    /** Returns a message for when there is an unknown DukeException.
     *
     * @return Exception message.
     */
    public static String dukeException() {
        return "There seems to be an error with Duke. Please try again!";
    }

    /**
     * Returns a message when a task is marked as done.
     *
     * @param taskNumber Number of the task in TaskList.
     */
    public static String done(int taskNumber) {
        return String.format("Solid work! I've marked task %d as done.", taskNumber);
    }

    /**
     * Returns a message when a new task is added to TaskList.
     *
     * @param taskType String representing the type of task.
     */
    public static String added(String taskType) {
        return String.format("Great! I've added your %s. Enter 'list' to see your tasks!%n",
                taskType);
    }

    /**
     * Returns a message when a task is deleted from TaskList.
     *
     * @param taskNumber Number of task in TaskList.
     */
    public static String delete(int taskNumber) {
        return String.format("Got it! I've removed task %d.", taskNumber);
    }

    /**
     * Returns a message to show the number of current tasks.
     *
     * @param numberOfTasks Number of current tasks.
     */
    public static String numberOfTasks(int numberOfTasks) {
        return String.format("You currently have %d tasks.%n", numberOfTasks);
    }

    /**
     * Returns a string of the current Tasks in taskArrayList with numbering.
     *
     * @return String of tasks.
     */
    public static String list(TaskList taskList) {
        if (taskList.size() == 0) {
            return "You currently have no tasks!";
        }
        String stringOfTasks = "Here are your current tasks:\n";
        for (int i = 1; i <= taskList.size(); i++) {
            stringOfTasks += i + "." + taskList.get(i).toString() + "\n";
        }
        return stringOfTasks;
    }

    /**
     * Returns a short message to greet the user.
     *
     * @return Short message string to greet the user.
     */
    public static String hi() {
        return "Hello! How can I help?";
    }
}
