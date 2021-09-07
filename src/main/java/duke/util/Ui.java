package duke.util;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;



/**
 * Represents the User Interface of Duke.
 */
public class Ui {
    /**
     * Prints a greeting.
     *
     * @return result content
     */
    public String showWelcome() {
        String words1 = "Hello! Welcome! This is Duke~\n";
        String words2 = "What can I help you today?";
        return words1 + words2;
    }

//    /**
//     * Prints a Goodbye.
//     */
//    public String showGoodBye() {
//        String words = "Bye! Hope to see you again!";
//        return words;
//    }


    /**
     * Outputs the whole existing task list.
     *
     * @param input command to print the list
     * @return Result content
     */
    public String showList(String input) {
        String words = "Here is your duke.task list:\n";
        return words + input;
    }

    /**
     * Prints the done operation.
     *
     * @param task the task to be marked as done.
     * @return result content
     */
    public String showDone(Task task) {
        String words = " Nice! I've marked this duke.task as done:\n";
        return words + task.toString();
    }

    /**
     * Prints the delete operation.
     *
     * @param task a task to be deleted
     * @param num the number of the remaining tasks
     * @return result content
     */
    public String showDelete(Task task, int num) {
        String words1 = "Noted. I've removed this duke.task:\n";
        String words2 = "\nNow you have " + num + " tasks in the list.";
        return words1 + task.toString() + words2;
    }

    /**
     * Prints the add operation.
     *
     * @param task a new task to be added
     * @return result content
     */
    public String showNewTask(Task task) {
        String words = "added:\n";
        return words + task.toString();
    }

    /**
     * Prints the find result
     *
     * @param results List of tasks as result
     * @return result content
     */
    public String showFind(ArrayList<Task> results) {
        String words = "Here are the matching tasks in your list:";
        for (int i = 1; i <= results.size(); i++) {
            words = words + "\n    " + i + ". " + results.get(i - 1);
        }
        return words;
    }


//    /**
//     * Reads command from the terminal.
//     *
//     * @return an input String
//     */
//    public String readCommand() {
//        Scanner scanner = new Scanner(System.in);
//        String command = scanner.nextLine().trim();
//        return command;
//    }

}
