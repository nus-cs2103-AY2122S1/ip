package duke;

import duke.tasks.Task;

/**
 * Class for the ui responsible for interactions with the user
 *
 */
public class Ui {

    private static String DIVIDER = "_______________________________________________\n";

    /**
     * Constructor that initializes ui
     *
     */
    public Ui(){}

    /**
     * Method that prints the start message
     *
     */
    public void startMessage() {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(DIVIDER
                + "\nHello!\n"
                + LOGO
                + "\nI'm Duke! How can I help you?\n"
                + DIVIDER
        );
    }

    /**
     * Method that prints the exit message
     *
     */
    public void byeMessage() {
        System.out.println("Bye! See you next time!\n");
    }

    /**
     * Method that prints message stating that task has been added
     *
     * @param taskList The tasklist that the task has been added to
     * @param task The task added
     */
    public void addedMessage(TaskList taskList, Task task) {
        System.out.println("Got it. I've added this task:\n"
                + "\t"
                + task.toString()
                +"\n"
                + "Now you have "
                + taskList.size()
                +" tasks in your list\n"
                + DIVIDER
        );
    }

    /**
     * Method that prints message stating that task has been deleted
     *
     * @param taskList The tasklist that the task has been deleted from
     * @param task The task deleted
     */
    public void deleteMessage(TaskList taskList, Task task) {
        System.out.println("Noted. I've removed this task:\n"
                + "\t"
                + task.toString()
                + "\n"
                + "Now you have "
                + taskList.size()
                + " tasks in your list\n"
                + DIVIDER
        );
    }

    /**
     * Method that prints message stating that task has been set as done
     *
     * @param task The task set as done
     */
    public void doneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done: \n"
                + "\t"
                + task.toString()
                + "\n"
                + DIVIDER
        );
    }

    /**
     * Method that prints out all tasks
     *
     * @param taskList The list of tasks to be printed
     */
    public void listTasks(TaskList taskList) {
        System.out.println(taskList.toString()
                + DIVIDER
        );
    }

    /**
     * Method that prints the list of tasks matching input string
     *
     * @param taskList List of tasks matching input string
     */
    public void listFoundTasks(TaskList taskList) {
        System.out.println("Here are your matching entries!\n"
                + taskList.toString()
                + DIVIDER
        );
    }

    /**
     * Method that prints divider
     *
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Method that prints file load error message
     *
     */
    public void loadErrorMessage() {
        System.out.println("File could not be read.\n"
                + DIVIDER);
    }

    /**
     * Method that prints error message
     *
     * @param error Error whose message needs to be printed
     */
    public void errorMessage(DukeException error) {
        System.out.println(error.getMessage()
                + DIVIDER
        );
    }
}