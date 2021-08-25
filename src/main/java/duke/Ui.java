package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with the interaction of a user.
 */
public class Ui {
    final private String INTRO = "Hello! I'm Duke\n" +
            "What can I do for you today?";

    final private String OUTRO = "Bye. Hope to see you again soon!";

    /**
     * Prints intro message.
     */
    public void showIntroMessage() {
        System.out.println(INTRO);
    }

    /**
     * Prints outro message.
     */
    public void showGoodbyeMessage() {
        System.out.println(OUTRO);
    }

    /**
     * Prints done message.
     *
     * @param currentTask Task completed.
     */
    public void showDoneMessage(Task currentTask) {
        System.out.println("Nice! I've marked this task as done:" + "\n" + currentTask.toString());
    }

    /**
     * Prints the tasks.
     *
     * @param list Tasks provided.
     */
    public void showListMessage(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task currentTask = list.get(i);
            System.out.println((i + 1) + "." + currentTask.toString());
        }
        System.out.println("You have a total of " + list.size() + " tasks");
    }

    /**
     * Prints the task added.
     *
     * @param currentTask The task added.
     * @param list The list of tasks.
     */
    public void showListMessage(String desc, ArrayList<Task> list) {
        System.out.format("Here are the %s tasks in your list:\n", desc);
        for (int i = 0; i < list.size(); i++) {
            Task currentTask = list.get(i);
            System.out.println((i + 1) + "." + currentTask.toString());
        }
        System.out.format("You have a total of " + list.size() + " %s tasks.\n", desc);
    }

    public void showTaskMessage(Task currentTask, ArrayList<Task> list) {
        System.out.format("Got it. I've added this task:\n" + currentTask.toString() + "\n"
                + "Now you have %d tasks in this list.\n", list.size());
    }

    /**
     * Prints the deleted task.
     *
     * @param currentTask The task deleted.
     * @param list The list of tasks.
     */
    public void showDeleteMessage(Task currentTask, ArrayList<Task> list) {
        System.out.format("Noted. I've removed this task:\n" + currentTask.toString() + "\n"
                + "Now you have %d tasks in the list\n", list.size());
    }

    /**
     * Prints message if unknown command given.
     */
    public void showUnknownMessage() {
        System.out.println("Sorry, unknown command!");
    }

    /**
     * Prints message if there are problems loading the list.
     */
    public void showLoadingErrorMessage() {
        System.out.println("Your file seems to have issues loading.");
    }

    public void showNothingFoundMessage() {
        System.out.println("Sorry, there are no matching tasks");
    }

}
