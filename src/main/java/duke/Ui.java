package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class handling interactions with the users
 */
public class Ui {
    private final Scanner s = new Scanner(System.in);

    /**
     * Retrieve the next command from the user and executes it.
     *
     * @return The next line of input
     */
    public String nextLine() {
        return s.nextLine();
    }

    /**
     * Prints the welcome message.
     *
     * @return Output String.
     */
    public String printWelcome() {
        String response = "Hello! I'm \nWhat can I do for you?";
        System.out.println(response);
        return response;
    }

    /**
     * Prints the goodbye message.
     *
     * @return Output String.
     */
    public String printGoodbye() {
        String response = "Bye. Hope to see you again soon!";
        System.out.println(response);
        return response;
    }


    /**
     * Prints the message for successful load.
     *
     * @return Output String.
     */
    public String printLoadSuccess() {
        String response = "Save file successfully loaded";
        System.out.println(response);
        return response;
    }

    /**
     * Prints the list of task.
     *
     * @param tasks TaskList to be printed.
     * @return Output String.
     */
    public String printList(TaskList tasks) {
        String response = "---------\n" + tasks.toString() + "\n---------";
        System.out.println(response);
        return response;
    }


    /**
     * Prints the error of a given DukeException.
     *
     * @param err Exception to be printed.
     * @return Output String.
     */
    public String printDukeException(DukeException err) {
        String response = "Error: " + err.getMessage();
        System.out.println(response);
        return response;
    }


    /**
     * Print message when adding a new task.
     * @param tasks TaskList of the program.
     * @return Output String.
     * @throws DukeException When TaskList cannot retreive the last element in the list.
     */
    public String printNewTask(TaskList tasks) throws DukeException {
        String response = "---------\n";
        response += "Got it. I've added this task:\n";
        response += tasks.get(tasks.count() - 1).toString() + "\n";
        response += "Now you have " + tasks.count() + " task in the list\n";
        response += "---------";

        System.out.println(response);
        return response;
    }

    /**
     * Prints message when a task is marked as completed.
     * @param task Task that was marked complete.
     * @return Output String.
     */
    public String printDoneTask(Task task) {
        String response = "---------\nNice! I've marked this task as done:\n" + task + "\n---------";
        System.out.println(response);
        return response;
    }

    /**
     * Prints message when a task is deleted.
     * @param size Current size of the TaskList.
     * @return Output String.
     */
    public String printDeleteTask(int size) {
        String response = "---------\nNoted. I've removed this task\n" +
                "Now you have " + size + " task in the list " +
                 "\n---------";
        System.out.println(response);
        return response;
    }

    /**
     * Prints tasks based on the indexes provided.
     *
     * @param indexes Indexes of the results.
     * @param tasks List of tasks.
     * @return Output String.
     * @throws DukeException Any index does not exist in the TaskList.
     */
    public String printIndexes(ArrayList<Integer> indexes, TaskList tasks) throws DukeException{
        String response = "---------\n";
        int i = 1;
        for (int index : indexes) {
            response += i+"."+ tasks.get(index) + '\n';
            i++;
        }
        response += "---------";
        System.out.println(response);
        return response;
    }

    /**
     * Closes the scanner when the program is done.
     */
    public void closeScanner() {
        s.close();
    }
}
