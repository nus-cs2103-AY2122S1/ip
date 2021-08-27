package duke;

import java.util.Scanner;

/**
 * Implements a Ui object that handles the interaction with the user.
 */
public class Ui {
    private final String divider = "________________________________";
    private final Scanner scanner;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a divider.
     */
    public void showLine() {
        System.out.println(divider);
    }

    /**
     * Wraps a String object between dividers.
     * @param content The content.
     */
    public void reply(String content) {
        System.out.println(divider);
        System.out.println(content);
        System.out.println(divider);
    }

    /**
     * Displays a welcome message when Duke is loaded.
     */
    public void welcome() {
        reply("Hello i is Duke\nWhat u want?");
    }

    /**
     * Reads the command that the user input.
     * @return The user input in the form of a String.
     */
    public String readCommand() {
        return scanner.hasNext() ? scanner.nextLine() : "bye";
    }

    /**
     * Prints the exit message.
     */
    public void exit() {
        System.out.println("i zao first");
    }

    /**
     * Displays the current task list.
     * @param tasks The TaskList object.
     */
    public void displayList(TaskList tasks) {
        for (int i = 0; i < tasks.length(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + "." + task.toString());
        }
    }

    /**
     * Prints the task completed message.
     * @param completedTask The completed Task.
     */
    public void done(Task completedTask) {
        System.out.println("noice this thing done:\n" + completedTask);
    }

    /**
     * Prints the error message.
     * @param e The DukeException object.
     */
    public void showLoadingError(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints the task added message.
     * @param task The added Task.
     * @param length The number of Tasks in the TaskList.
     */
    public void add(Task task, int length) {
        System.out.println("one more thing: " + task.toString() + "\nNow you got " + length + " thing(s). sian");
    }

    /**
     * Prints the task deleted message.
     * @param task The deleted Task.
     * @param length The number of Tasks in the TaskList.
     */
    public void delete(Task task, int length) {
        System.out.println("this one no more liao ah :\n" + task.toString()
                + "\nNow you got " + length + " thing(s). sian");
    }

    public void displayMatchingList(TaskList tasks) {
        System.out.println("all these similar one:");
        for (int i = 0; i < tasks.length(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + "." + task.toString());
        }
    }
}
