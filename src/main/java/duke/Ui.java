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
     * @return String A welcome message.
     */
    public String welcome() {
        reply("Hello i is Duke\nWhat u want?");
        return "Perry the platypus?!\nWhat do you want?";
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
     * @return The exit message.
     */
    public String exit() {
        System.out.println("i zao first");
        return "i zao first";
    }

    /**
     * Displays the current task list.
     * @param tasks The TaskList object.
     * @return A string representation of the task list.
     */
    public String displayList(TaskList tasks) {
        String list = "";
        for (int i = 0; i < tasks.length(); i++) {
            Task task = tasks.get(i);
            String taskString = (i + 1) + "." + task.toString();
            System.out.println(taskString);
            list = list + taskString + "\n";
        }
        return list;
    }

    /**
     * Prints the task completed message.
     * @param completedTask The completed Task.
     * @return A string representation of the completed task.
     */
    public String done(Task completedTask) {
        System.out.println("noice this thing done:\n" + completedTask);
        return "noice this thing done:\n" + completedTask;
    }

    /**
     * Prints the error message.
     * @param e The DukeException object.
     * @return A string representation of the error.
     */
    public String showLoadingError(DukeException e) {
        System.out.println(e.getMessage());
        return e.getMessage();
    }

    /**
     * Prints the task added message.
     * @param task The added Task.
     * @param length The number of Tasks in the TaskList.
     * @return A string representation of the task added.
     */
    public String add(Task task, int length) {
        String taskAdded = "one more thing: " + task.toString() + "\nNow you got " + length + " thing(s). sian";
        System.out.println(taskAdded);
        return taskAdded;
    }

    /**
     * Prints the task deleted message.
     * @param task The deleted Task.
     * @param length The number of Tasks in the TaskList.
     * @return A string representation of the deleted task.
     */
    public String delete(Task task, int length) {
        String deleteMessage = "this one no more liao ah :\n" + task.toString()
                + "\nNow you got " + length + " thing(s). sian";
        System.out.println(deleteMessage);
        return deleteMessage;
    }

    /**
     * Prints the list of matching Tasks.
     * @param tasks The list of matching Tasks.
     * @return A string representation of the matching list.
     */
    public String displayMatchingList(TaskList tasks) {
        System.out.println("all these similar one:");
        String matchingList = "all these similar one:\n";
        for (int i = 0; i < tasks.length(); i++) {
            Task task = tasks.get(i);
            String matchingTask = (i + 1) + "." + task.toString();
            System.out.println(matchingTask);
            matchingList = matchingList + matchingTask + "\n";
        }
        return matchingList;
    }
}
