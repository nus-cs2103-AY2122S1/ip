package duke;

import java.util.Scanner;
import duke.task.Task;

/**
 * Represents the UI of the chatbot.
 * Contains a Scanner that reads the input given by the user.
 */

public class Ui {

    private Scanner sc;

    /**
     * Creates a UI Object containing a Scanner that reads the inputs.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    private String logo = " ____        _        \n"
    + "|  _ \\ _   _| | _____ \n"
    + "| | | | | | | |/ / _ \\\n"
    + "| |_| | |_| |   <  __/\n"
    + "|____/ \\__,_|_|\\_\\___|\n";


    /**
     * Prints the greeting message the user sees when the Bot is started up.
     *
     */
    public void showWelcome() {
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    /**
     * Prints the error message in the case the file path that the Storage is supposed to read from does not exist.
     */
    public void showLoadingError() {
        System.out.println("File Not Found");
    }

    /**
     * Prints the error message when an Exception is thrown.
     * @param error error message given by the Exception.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Prints the message when a Task is added to the TaskList.
     * @param task Task to be added to the TaskList
     * @param tasks TaskList the Task is to be added to.
     */

    public void printAdd(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(String.format("  %s", task.toString()));
        System.out.println(String.format("Now you have %d tasks in the list", tasks.size()));
    }

    /**
     * Prints the message when a Task is completed in the TaskList.
     * @param tasks TaskList containing the Task that was completed.
     * @param toComplete Index of the completed Task in the TaskList
     */
    public void printDone(TaskList tasks, int toComplete) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format("  %s", tasks.get(toComplete)));
    }

    /**
     * Prints the message when a Task is deleted in the TaskList.
     * @param tasks TaskList containing the Task that was deleted.
     * @param toDelete Index of the deleted Task in the TaskList
     */
    public void printDelete(TaskList tasks, int toDelete){
        System.out.println("Noted. I've removed this task: ");
        System.out.println(String.format("  %s", tasks.get(toDelete)));
        System.out.println(String.format("Now you have %d tasks in the list", tasks.size() - 1));
    }

    /**
     * Prints the message when the Bot is Terminated.
     */
    public void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    /**
     * Prints the line between each output.
     */
    public void showLine() {
        System.out.println("_______");
    }

    /**
     * Prints out the list of Tasks stored in the TaskList.
     * @param tasks TaskList to be printed.
     */
    public void printList(TaskList tasks) {
        System.out.println("Here are the tasks in your list: ");
        for(int i = 0; i < tasks.size(); i++){
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Returns the input given by the user.
     * @return String containing input of the user.
     */
    public String readCommand() {
        String input = sc.nextLine();
        return input;
    }
}