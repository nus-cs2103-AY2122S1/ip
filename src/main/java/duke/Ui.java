package duke;

import java.util.ArrayList;

public class Ui {
    public void sayHi() {
        System.out.println("Hello from\n" + "DUKE\n" + "What can I do for you?");
    }

    public void sayList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your tasks:");
        for (int i = 0; i < list.size(); i++) {
            int num = i + 1;
            if (list.get(i) != null) {
                System.out.println(num + "." + list.get(i).toString());
            }
        }
    }

    public void sayCompleted(Task t) {
        System.out.println("Nice! I've marked this task as done:\n" + "  " + t.toString());
    }

    public void sayError(DukeException e) {
        System.out.println(e.getMessage());
    }

    public void sayUpdates(Task t, int size) {
        System.out.println("Got it. I've added this task:\n  "
                + t.toString()
                + "\nNow you have " + size + " tasks in the task.");
    }

    public void sayDeletes(Task t, int size) {
        System.out.println("Noted. I've removed this task:\n  "
                + t.toString()
                + "\nNow you have " + size + " tasks in the list.");
    }

    public void sayWrongInput() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the matching tasks in the list with the keyword.
     *
     * @param list List of matching tasks.
     */
    public void sayFind(ArrayList<Task> list) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            int num = i + 1;
            if (list.get(i) != null) {
                System.out.println(num + "." + list.get(i).toString());
            }
        }
    }
}