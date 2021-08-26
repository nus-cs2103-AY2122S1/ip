package duke;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * Ui class
 *
 * @author Timothy Wong Eu-Jin
 */

/** Deals with interactions with the user. */
public class Ui {

    boolean isExit;

    public Ui() {
        isExit = false;
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showDivider() {
        System.out.println("-------------------------------------");
    }

    /** Prints greeting statement */
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

    /** Checks if program has exited */
    public boolean isExit() {
        return this.isExit;
    }

    /** Prints goodbye statement */
    public void sendGoodbye() {
        showDivider();
        System.out.println("Bye. Hope to see you again soon!");
        showDivider();
        isExit = true;
    }

    /** Prints response after successfully adding task */
    public void sendAddTask(Task task) {
        showDivider();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        showDivider();
    }

    /** Prints done task statement */
    public void sendDone(Task task) {
        showDivider();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        showDivider();
    }

    /** Prints deleted task statement */
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
        for (Task t : tasks) {
            System.out.println(count + ". " + t.toString());
            count += 1;
        }
        showDivider();
    }

    /** Prints all tasks statement */
    public void enumTasks(ArrayList<Task> tasks) {
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

    /** Print error message */
    public void showError(String s) {
        System.err.println(s);
    }
}
