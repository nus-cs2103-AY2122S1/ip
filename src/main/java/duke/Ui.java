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
     * Returns the greeting from Duke.
     *
     * @return Greeting in string.
     */
    public String sayHi() {
        return "Hello from\n" + "DUKE\n" + "What can I do for you?";
    }

    /**
     * Returns the list of tasks in Duke.
     *
     * @param list List with the tasks.
     * @return string of list.
     */
    public String sayList(ArrayList<Task> list) {
        StringBuilder string = new StringBuilder();
        string.append("Here are the tasks in your tasks:\n");
        for (int i = 0; i < list.size(); i++) {
            int num = i + 1;
            if (list.get(i) != null) {
                string.append(num).append(".").append(list.get(i).toString()).append("\n");
            }
        }
        return string.toString();
    }

    /**
     * Returns a string representing a reply to indicate
     * successfully marking a specified task as done.
     *
     * @param t Task that was marked.
     * @return String for the reply.
     */
    public String sayCompleted(Task t) {
        return "Nice! I've marked this task as done:\n" + "  " + t.toString();
    }

    /**
     * Returns the error message of a Duke Exception.
     *
     * @param e DukeException.
     * @return String for the message.
     */
    public String sayError(DukeException e) {
        return e.getMessage();
    }

    /**
     * Returns a reply to indicate successfully adding
     * a specified task and the current list size.
     *
     * @param t Added Task.
     * @param size List size.
     * @return String representing the reply.
     */
    public String sayUpdates(Task t, int size) {
        return "Got it. I've added this task:\n  "
                + t.toString()
                + "\nNow you have " + size + " tasks in the task.";
    }

    /**
     * Returns a reply to indicate successfully deleting
     * a specified task and the current list size.
     *
     * @param t Deleted Task.
     * @param size List size.
     * @return String representing the reply.
     */
    public String sayDeletes(Task t, int size) {
        return "Noted. I've removed this task:\n  "
                + t.toString()
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Returns a reply indicating wrong input.
     *
     * @return String representing the reply.
     */
    public String sayWrongInput() {
        return " OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Returns a reply to indicate the exit of Duke.
     *
     * @return String representing the reply.
     */
    public String sayBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns in a string the matching tasks in the list with the keyword.
     *
     * @param list List of matching tasks.
     * @return String representing the tasks.
     */
    public String sayFind(ArrayList<Task> list) {
        StringBuilder string = new StringBuilder();
        string.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            int num = i + 1;
            if (list.get(i) != null) {
                string.append(num).append(".").append(list.get(i).toString()).append("\n");
            }
        }
        return string.toString();
    }
}
