package duke;

import java.util.Scanner;
import java.util.ArrayList;
/**
 * Deals with interactions with the user.
 */
public class Ui {

    private boolean isExit;

    /**
     * Constructs a Ui Object.
     */
    public Ui() {
        isExit = false;
    }

    /**
     * Creates a scanner to read the input command.
     *
     * @return One command line.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints a standard line break.
     */
    public void showDivider() {
        System.out.println("-------------------------------------");
    }

    /**
     * Prints greeting statement.
     */
    public void greet() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        showDivider();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        showDivider();
    }

    /**
     * Checks if program has exited.
     * @return boolean isExit.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Prints goodbye statement.
     *
     * Changes isExit to true.
     */
    public void sendGoodbye() {
        showDivider();
        System.out.println("Bye. Hope to see you again soon!");
        showDivider();
        this.isExit = true;
    }

    /**
     * Prints response after successfully adding task.
     *
     * @param task Added Task.
     */
    public void sendAddTask(Task task) {
        showDivider();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        showDivider();
    }

    /**
     * Prints done task statement.
     *
     * @param task Completed Task.
     */
    public void sendDone(Task task) {
        showDivider();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        showDivider();
    }

    /**
     * Prints deleted task statement.
     *
     * @param task Deleted Task.
     */
    public void sendDeleted(Task task) {
        showDivider();
        System.out.println("Noted. I've removed this task:\n" + task.toString());
        showDivider();
    }

    /**
     * Prints matched Tasks.
     *
     * @param tasks ArrayList of matched tasks.
     */
    public void sendMatchedTasks(ArrayList<Task> tasks) {
        showDivider();
        int count = 1;

        if (tasks.size() == 0) {
            System.out.println("No matches!");
        } else {
            for (Task t : tasks) {
                System.out.println(count + ". " + t.toString());
                count += 1;
            }
        }
        showDivider();
    }

    /**
     * Prints all tasks statement.
     *
     * @param tasks Entire
     */
    public void enumTasks(ArrayList<Task> tasks) {
        showDivider();
        int count = 1;

        for (Task t : tasks) {
            System.out.println(count + ". " + t.toString());
            count += 1;
        }
        showDivider();
    }

    /**
     * Prints error message.
     *
     * @param s
     */
    public void showError(String s) {
        System.err.println(s);
    }
}
