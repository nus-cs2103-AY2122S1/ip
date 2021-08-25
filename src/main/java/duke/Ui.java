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
    public void greetingMsg() {
        String str = "Hello from\n"
                + LOGO
                + LINEBREAK
                + "My favorite partner is back! How can I help?\n"
                + LINEBREAK;
        System.out.print(str);
    }

    /**
     * Prints goodbye message.
     */
    public void goodbyeMsg() {
       System.out.print(LINEBREAK
                + "Have a good day, friend!\n"
                + LINEBREAK);
    }

    /**
     * Prints task list.
     *
     * @param taskList Task list to be printed.
     */
    public void listTaskList(TaskList taskList) {
        System.out.print(LINEBREAK);
        for (int j = 0; j < Task.noOfTask; j++) {
            String listItem = (j + 1)
                    + "."
                    + taskList.getTask(j).getTaskType()
                    + taskList.getTask(j).checkIsDone()
                    + " " + taskList.getTask(j).getDescription() + "\n";
            System.out.print(listItem);
        }
        System.out.print(LINEBREAK);
    }

    /**
     * Prints done task message.
     *
     * @param task Task to be printed.
     */
    public void doneTaskMsg(Task task) {
        System.out.print( LINEBREAK
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
    public void deleteTaskMsg(Task task) {
        System.out.print( LINEBREAK
                + "Roger! I will remove this task from the list: \n"
                + task.getTaskType()
                + task.checkIsDone()
                + task.getDescription() + "\n"
                + "Now you have "
                + Task.noOfTask
                + " tasks left in the list.\n"
                + LINEBREAK);
    }

    /**
     * Prints add task message.
     *
     * @param task Task to be printed.
     */
    public void addTaskMsg(Task task) {
        System.out.print( LINEBREAK
                +"Roger! I will add this task in: \n"
                + task.getTaskType()
                + task.checkIsDone()
                + " "
                + task.getDescription() + "\n"
                + "Now you have "
                + Task.noOfTask
                + " tasks left in the list.\n"
                + LINEBREAK);
    }
}
