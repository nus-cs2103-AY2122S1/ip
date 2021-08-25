package duke.util;

import duke.task.Task;

import java.util.Scanner;

/**
 * Represents the User Interfacer of Duke.
 */
public class Ui {
    public void print(String... strings) {
        System.out.println("_________________________________________________");
        for (String string: strings) {
            System.out.println("\n" + "    " + string);
        }
        System.out.println("_________________________________________________");
    }

    /**
     * Prints a divider line.
     */
    public void showLine() {
        System.out.println("_________________________________________________");
    }

    /**
     * Prints a greeting.
     */
    public void showWelcome() {
        String words1 = "Hello! Welcome! This is Duke~";
        String words2 = "What can I help you today?";
        print(words1, words2);
    }

    /**
     * Prints a Goodbye.
     */
    public void showGoodBye() {
        String words = "Bye! Hope to see you again!";
        print(words);
    }

    /**
     * Outputs the whole existing task list.
     *
     * @param input command to print the list
     */
    public void showList(String input) {
        String words = "Here is your duke.task list:";
        print(words, input);
    }

    /**
     * Prints the done operation.
     *
     * @param task the task to be marked as done
     */
    public void showDone(Task task) {
        String words = " Nice! I've marked this duke.task as done:";
        print(words, task.toString());
    }

    /**
     * Prints the delete operation.
     *
     * @param task a task to be deleted
     * @param num the number of the remaining tasks
     */
    public void showDelete(Task task, int num) {
        String words1 = "Noted. I've removed this duke.task:";
        String words2 = "Now you have " + num + " tasks in the list.";
        print(words1, task.toString(), words2);
    }

    /**
     * Prints the add operation.
     *
     * @param task a new task to be added
     */
    public void showNewTask(Task task) {
        String words = "added:";
        print(words, task.toString());
    }

    /**
     * Reads command from the terminal.
     *
     * @return an input String
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine().trim();
        return command;
    }
}
