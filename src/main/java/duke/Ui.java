package duke;

import java.util.ArrayList;

/**
 * Class for Ui in Duke which
 * deals with interactions with the user.
 *
 * @author Samuel Lau
 */
public class Ui {

    /**
     * Prints the greeting from Duke.
     */
    public void sayHi() {
        System.out.println("Hello from\n" + "DUKE\n" + "What can I do for you?");
    }

    /**
     * Prints the list of tasks in Duke.
     *
     * @param list List with the tasks.
     */
    public void sayList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your tasks:");
        for (int i = 0; i < list.size(); i++) {
            int num = i + 1;
            if (list.get(i) != null) {
                System.out.println(num + "." + list.get(i).toString());
            }
        }
    }

    /**
     * Prints a reply to indicate successfully
     * marking a specified task as done.
     *
     * @param t Task that was marked.
     */
    public void sayCompleted(Task t) {
        System.out.println("Nice! I've marked this task as done:\n" + "  " + t.toString());
    }

    /**
     * Prints the error message of a Duke Exception.
     *
     * @param e DukeException.
     */
    public void sayError(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints a reply to indicate successfully adding
     * a specified task and the current list size.
     *
     * @param t Added Task.
     * @param size List size.
     */
    public void sayUpdates(Task t, int size) {
        System.out.println("Got it. I've added this task:\n  "
                + t.toString()
                + "\nNow you have " + size + " tasks in the task.");
    }

    /**
     * Prints a reply to indicate successfully deleting
     * a specified task and the current list size.
     *
     * @param t Deleted Task.
     * @param size List size.
     */
    public void sayDeletes(Task t, int size) {
        System.out.println("Noted. I've removed this task:\n  "
                + t.toString()
                + "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Prints a reply indicating wrong input.
     */
    public void sayWrongInput() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints a reply to indicate the exit of Duke.
     */
    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}