package duke;

public class Ui {
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String LINEBREAK = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

    /**
     * Prints greeting message.
     */
    public String greetingMsg() {
        String str = "Hello from\n"
                + LOGO
                + LINEBREAK
                + "My favorite partner is back! How can I help?\n"
                + LINEBREAK;
        return (str);
    }

    /**
     * Prints goodbye message.
     */
    public String goodbyeMsg() {
        return (LINEBREAK
                + "Have a good day, friend!\n"
                + LINEBREAK);
    }

    /**
     * Prints task list.
     *
     * @param taskList Task list to be printed.
     */
    public String listTaskList(TaskList taskList) {
        return (LINEBREAK
                + taskList.toString()
                + LINEBREAK);
    }

    /**
     * Prints done task message.
     *
     * @param task Task to be printed.
     */
    public String doneTaskMsg(Task task) {
        return (LINEBREAK
                + "Well Done, I'll get it marked:\n"
                + task.checkIsDone()
                + " " + task.getDescription() + "\n"
                + LINEBREAK);
    }

    /**
     * Prints delete task message.
     *
     * @param task Task to be printed.
     */
    public String deleteTaskMsg(Task task, int noOfTask) {
        return (LINEBREAK
                + "Roger! I will remove this task from the list: \n"
                + task.getTaskType()
                + task.checkIsDone()
                + task.getDescription() + "\n"
                + "Now you have "
                + noOfTask
                + " tasks left in the list.\n"
                + LINEBREAK);
    }

    /**
     * Prints add task message.
     *
     * @param task Task to be printed.
     */
    public String addTaskMsg(Task task, int noOfTask) {
        return (LINEBREAK
                + "Roger! I will add this task in: \n"
                + task.getTaskType()
                + task.checkIsDone()
                + " "
                + task.getDescription() + "\n"
                + "Now you have "
                + noOfTask
                + " tasks left in the list.\n"
                + LINEBREAK);
    }

    /**
     * Prints find task message and lists relevant tasks.
     *
     * @param taskList Task list that contains relevant tasks to be printed.
     */
    public String findTaskMsg(TaskList taskList) {
        return (LINEBREAK
                + "Are these what you were looking for?\n"
                + taskList.toString()
                + LINEBREAK);
    }
}
