package duke;

public class Ui {
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints greeting message.
     */
    public String greetingMsg() {
        String str = "Hello from\n"
                + LOGO
                + "My favorite partner is back! How can I help?\n";
        return (str);
    }

    /**
     * Prints goodbye message.
     */
    public String goodbyeMsg() {
        return ("Have a good day, friend!\n");
    }

    /**
     * Prints task list.
     *
     * @param taskList Task list to be printed.
     */
    public String listTaskList(TaskList taskList) {
        return taskList.toString();
    }

    /**
     * Prints done task message.
     *
     * @param task Task to be printed.
     */
    public String doneTaskMsg(Task task) {
        return ("Well Done, I'll get it marked:\n"
                + task.toString() + "\n");
    }

    /**
     * Prints delete task message.
     *
     * @param task Task to be printed.
     */
    public String deleteTaskMsg(Task task, int noOfTask) {
        return ("Roger! I will remove this task from the list: \n"
                + task.toString() + "\n"
                + "Now you have "
                + noOfTask
                + " tasks left in the list.\n");
    }

    /**
     * Prints add task message.
     *
     * @param task Task to be printed.
     */
    public String addTaskMsg(Task task, int noOfTask) {
        return ("Roger! I will add this task in: \n"
                + task.toString() + "\n"
                + "Now you have "
                + noOfTask
                + " tasks left in the list.\n");
    }

    /**
     * Prints find task message and lists relevant tasks.
     *
     * @param taskList Task list that contains relevant tasks to be printed.
     */
    public String findTaskMsg(TaskList taskList) {
        return ("Are these what you were looking for?\n"
                + taskList.toString());
    }
}
