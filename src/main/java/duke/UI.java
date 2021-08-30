package duke;

/**
 * Class responsible for interactions with the user
 */
public class UI {
    /**
     * Returns welcome message
     *
     * @return Welcome message
     */
    public static String welcome() {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         */
        return "Stay on track with Duke!\nHow can I help you?";
    }

    /**
     * Returns a goodbye message
     *
     * @return Goodbye message
     */
    public static String bye() {
        return "¡Adiós! See you soon!";
    }

    /** Returns a message for when there is an unknown DukeException **/
    public static String dukeException() {
        return "There seems to be an error with Duke. Please try again!";
    }

    /**
     * Returns a message when a task is marked as done
     * @param taskNumber Number of the task in TaskList
     */
    public static String done(int taskNumber) {
        return String.format("Solid work! I've marked task %d as done.", taskNumber);
    }

    /**
     * Returns a message when a new task is added to TaskList
     *
     * @param taskType String representing the type of task
     */
    public static String added(String taskType) {
        return String.format("Great! I've added your %s. Enter 'list' to see your tasks!%n",
                taskType);
    }

    /**
     * Returns a message when a task is deleted from TaskList
     * @param taskNumber Number of task in TaskList
     */
    public static String delete(int taskNumber) {
        return String.format("Got it! I've removed task %d.", taskNumber);
    }

    /**
     * Prints a message to show the number of current tasks
     * @param numberOfTasks Number of current tasks
     */
    public static String numberOfTasks(int numberOfTasks) {
        return String.format("You currently have %d tasks.%n", numberOfTasks);
    }

    /**
     * Lists the current Tasks in taskArrayList with numbering
     */
    public static String list(TaskList taskList) {
        String list = "Here are your current tasks:\n";
        for (int i = 1; i <= taskList.size(); i++) {
            list += i + "." + taskList.get(i).toString() + "\n";
        }
        return list;
    }
}
