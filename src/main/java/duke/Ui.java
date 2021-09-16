package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;


/**
 * User Interface that user directly interacts with
 * e.g. displaying messages, scanning inputs
 */
public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String END =
            "    ---------------------------------------------------------------------------------";

    private static final String INDENTATION = "     ";


    private Scanner sc;

    /**
     * Constructor for UI
     * starts scanner object to read user inputs
     *
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * scan the next line
     *
     * @return the user's raw input
     *
     */
    public String readInput() {
        return sc.nextLine();
    }
    private void printBreak() {
        System.out.println(END);
    }

    /**
     * Print welcome message when duke runs initially
     */
    public void printGreetings() {
        System.out.println("Hello from\n" + LOGO);
    }

    /**
     * Prints the current list formatted
     *
     * @param taskList current list
     *
     */
    public void printList(TaskList taskList) {
        printBreak();
        ArrayList<Task> todos = taskList.getList();
        todos.trimToSize();
        if (todos.size() == 0) {
            System.out.println(INDENTATION + " ٩(◕‿◕｡)۶ Ehe no task for now.");
        } else {
            for (int i = 0; i < todos.size(); i++) {
                System.out.println(INDENTATION + (i + 1) + ": " + todos.get(i).toString());
            }
        }
        printBreak();
    }

    /**
     * prints the task added to list formatted
     *
     * @param task new task added
     * @param taskLength the new length of the list
     *
     */
    public void printAdd(Task task, int taskLength) {
        printBreak();
        System.out.println(INDENTATION + "added: " + task.toString());
        System.out.println("\n" + INDENTATION + "You have " + taskLength + " task(s) to go! (]＞＜)]");
        printBreak();
    }

    /**
     * prints the goodbye message to user
     */
    public void printBye() {
        printBreak();
        System.out.println(INDENTATION + "Bye. Hope to see you again soon! (´｡• ω •｡`)");
        printBreak();
    }

    /**
     * prints the task that has been marked as complete formatted
     *
     * @param completedTask the description field of the task that has been completed
     *
     */
    public void printDone(String completedTask) {
        printBreak();
        System.out.println(INDENTATION + "(´• ω •`) What a rarity! This task has been marked as done:");
        System.out.println(INDENTATION + completedTask);
        printBreak();
    }

    /**
     * prints the specific task deleted by user formatted
     *
     * @param deletedTask description field of the task that has been deleted
     * @param listLength current length of list after deletion
     *
     */
    public void printDelete(String deletedTask, int listLength) {
        printBreak();
        System.out.println(INDENTATION + "(￢_￢) Ok... This task has been deleted:");
        System.out.println(INDENTATION + "deleted: " + deletedTask);
        System.out.println("\n" + INDENTATION + "You have " + listLength + " task(s) to go! (]＞＜)]");
        printBreak();
    }

    /**
     * Print file error that has occurred during storage
     *
     * @param e the error to getMessage()
     */
    public void printFileError(IOException e) {
        printBreak();
        System.out.println(INDENTATION + "╮(￣ω￣;)╭ File Error..." + e.getMessage());
        printBreak();
    }

    /**
     * print type of error formatted
     *
     * @param message error message to be understood
     * @param emoticon emoticon to add expressions and character to duke
     *
     */
    public void printError(String message, String emoticon) {
        printBreak();
        System.out.println(INDENTATION + emoticon + " " + message);
        printBreak();
    }

    /**
     * print already formatted error messages
     *
     * @param message formatted error message
     *
     */
    public void printError(String message) {
        printBreak();
        System.out.println(INDENTATION + message);
        printBreak();
    }

    /**
     * print tasks related to keywords
     *
     * @param foundTasks list of tasks that matches
     */
    public void printFound(ArrayList<Task> foundTasks) {
        printBreak();
        if (foundTasks.size() == 0) {
            System.out.println(INDENTATION + "(///￣ ￣///) No tasks with that keyword was found...");
        } else {
            System.out.println(INDENTATION + "(*¯︶¯*) Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println(INDENTATION + (i + 1) + ": " + foundTasks.get(i).toString());
            }
        }
        printBreak();
    }

    /**
     * print tasks that has been snoozed
     *
     * @param snoozedTask task that has been snoozed
     */
    public void printSnooze(String snoozedTask) {
        printBreak();
        System.out.println("Ok... This task has been snoozed zzZ");
        System.out.println(snoozedTask);
        printBreak();
    }
}
