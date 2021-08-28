package duke;

/**
 * Ui is the class that deals with all output to the user.
 */
public class Ui {
    private final String LOGO = " ____        _        \n"
                              + "|  _ \\ _   _| | _____ \n"
                              + "| | | | | | | |/ / _ \\\n"
                              + "| |_| | |_| |   <  __/\n"
                              + "|____/ \\__,_|_|\\_\\___|\n";
    private final String DUKE = "\nDuke:";
    private final String USER = "\nUser:";

    /**
     * Class constructor.
     */
    public Ui() {
        printStartUpMessage();
    }

    /**
     * Prints the message when starting up the program.
     */
    public void printStartUpMessage() {
        System.out.println("Hello from\n" + LOGO);
    }

    /**
     * Prints the message when the program is ready to accept user commands.
     */
    public void printStartInteractionsMessage() {
        System.out.println(DUKE + "Hi, what do you want from me?");
    }

    /**
     * Prints the message when waiting for the user's next command.
     */
    public void printWaitingUserInput() {
        System.out.print(USER);
    }

    /**
     * Prints the current list of tasks.
     *
     * @param tasks the TaskList containing all the current tasks.
     */
    public void printTaskList(TaskList tasks) {
        for (int i = 1; i <= tasks.getSize(); i++) {
            System.out.printf("\t\t%d.%s\n", i, tasks.getTask(i));
        }
        System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list.");
    }

    /**
     * Prints the message when the program has added a task to the list.
     *
     * @param tasks the TaskList containing all the current tasks.
     * @param newTask the task that was added to the list.
     */
    public void printAddTask(TaskList tasks, Task newTask) {
        System.out.println(DUKE + "\n\tAdded:\n\t\t" + newTask);
        System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list.");
    }

    /**
     * Prints the message when the program has marked a task as completed.
     *
     * @param completedTask the task that was marked as completed.
     */
    public void printCompleteTask(Task completedTask) {
        System.out.println(DUKE + "\n\tMarking task as completed:");
        System.out.printf("\t\t%s\n", completedTask);
    }

    /**
     * Prints the message when the program has deleted a task from the list.
     *
     * @param tasks the TaskList containing all the current tasks.
     * @param deletedTask the task that was deleted from the list.
     */
    public void printDeleteTask(TaskList tasks, Task deletedTask) {
        System.out.println(DUKE + "\n\tRemoving task:");
        System.out.printf("\t\t%s\n", deletedTask);
        System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list.");
    }

    /**
     * Prints a list of tasks whose names contain the subject.
     *
     * @param tasks the TaskList containing all the current tasks.
     * @param subject the keyword that is being searched for.
     */
    public void printTasksWithSubject(TaskList tasks, String subject) {
        int nResults = 0;
        System.out.println("\tTasks with \"" + subject + "\":");
        for (int i = 1; i <= tasks.getSize(); i++) {
            if (tasks.getTask(i).getTaskName().contains(subject)) {
                nResults++;
                System.out.printf("\t\t%d.%s\n", i, tasks.getTask(i));
            }
        }

        if (nResults == 0) {
            System.out.println("\n\tNo results found.");
        }
        else if (nResults == 1) {
            System.out.println("\tTotal of 1 result found.");
        }
        else {
            System.out.println("\tTotal of " + nResults + " results found.");
        }
    }

    /**
     * Prints the message when the program has loaded the list of tasks
     * from a previous save file.
     *
     * @param tasks the TaskList containing all the current tasks
     *             that was loaded.
     */
    public void printLoadTasks(TaskList tasks) {
        System.out.println(DUKE + "I have loaded your past tasks list!");
        printTaskList(tasks);
    }

    /**
     * Prints the error message when the program encountered an error.
     *
     * @param message the error message from the exception.
     */
    public void printErrorMessage(String message) {
        System.out.println(DUKE);
        System.out.println("\tError: " + message + ".");
    }

    /**
     * Prints the message when the program is being exited.
     */
    public void printExitMessage() {
        System.out.println(DUKE + "Bye. Have a nice day.");
    }
}
