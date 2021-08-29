package duke.ui;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * This class encapsulates the UI of the Duke app.
 */
public class Ui {
    private static final String ROBOT_EMOJI_PREFIX = "\uD83E\uDD16: ";
    private static final String COLOR_CYAN = "\u001B[36m";
    private static final String COLOR_RED = "\u001B[91m";
    private static final String COLOR_RESET = "\u001B[0m";
    private static final String LOGO =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final Scanner in;
    private final PrintStream out;

    /**
     * Initializes the UI input and output streams.
     */
    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    /**
     * Prints formatted error messages.
     *
     * @param string The error message.
     */
    public void printErr(String string) {
        this.print(COLOR_RED + string + COLOR_RESET);
    }

    /**
     * Prints formatted messages/
     *
     * @param string The message.
     */
    public void print(String string) {
        this.out.println(ROBOT_EMOJI_PREFIX + string);
    }

    /**
     * Prints the Duke logo.
     */
    public void printLogo() {
        this.out.println(COLOR_CYAN + LOGO + COLOR_RESET);
    }

    /**
     * Prints the exit message.
     */
    public void printExitMessage() {
        this.print("Bye. Hope to see you again soon!");
    }

    /**
     * Prints all the task from the given task list.
     *
     * @param taskList The task list to be printed from.
     */
    public void printAllTasks(TaskList taskList) {
        List<Task> tasks = taskList.getTaskList();
        if (tasks.size() == 0) {
            this.print("You have no tasks in your list.");
            return;
        }

        this.print("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            this.out.println("\t" + ((i + 1) + ". " + tasks.get(i)));
        }
    }

    /**
     * Prints the task that was added.
     *
     * @param task The task that was added.
     */
    public void printTaskAdded(Task task) {
        this.print("Got it. I've added this task:");
        this.out.println("\t" + task);
    }

    /**
     * Prints the current number of task in the task list.
     *
     * @param taskList The task list.
     */
    public void printNumOfTasks(TaskList taskList) {
        List<Task> tasks = taskList.getTaskList();
        String numOfTasks = tasks.size() + " task" + (tasks.size() != 1 ? "s" : "");
        this.print("You now have " + numOfTasks + " in the list.");
    }

    /**
     * Prints the failed to initialize message.
     *
     * @param msg The message to be printed.
     */
    public void printFailedInitMessage(String msg) {
        this.printErr("Unable to initialize duke:");
        this.out.println("\t" + COLOR_RED + msg + COLOR_RESET);
        this.printErr("Exiting...");
    }

    /**
     * Greets the user.
     */
    public void printGreeting() {
        this.print("Hi, im Duke!");
        this.print("What can i do for you?");
    }

    /**
     * Prints the task that was marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printTaskMarkedAsDone(Task task) {
        this.print("Nice! I've marked this task as done:");
        this.out.println("\t" + task);
    }

    /**
     * Prints the task that was deleted.
     *
     * @param task The task that was deleted.
     */
    public void printTaskDeleted(Task task) {
        this.print("Noted. I've removed this task:");
        this.out.println("\t" + task);
    }

    /**
     * Closes the scanner.
     */
    public void exit() {
        this.in.close();
    }

    /**
     * Gets the user command.
     *
     * @return The user command.
     */
    public String readCommand() {
        return this.in.nextLine();
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public void printMatches(List<Task> tasks) {
        if (tasks.isEmpty()) {
            this.print("No matches!");
            return;
        }

        this.print("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            this.out.println("\t" + ((i + 1) + ". " + tasks.get(i)));
        }
    }
}
