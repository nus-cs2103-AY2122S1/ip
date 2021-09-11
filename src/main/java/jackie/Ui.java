package jackie;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * A class that takes charge of interactions between the user and Jackie programme.
 * A collection of methods are provided to have different behaviours in different scenarios.
 *
 * @author Gu Geng
 */
public class Ui {

    private Scanner scanner;

    /**
     * Returns an Ui instance by initialising a scanner to track user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the user input by the scanner.
     * @return A String of user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints the welcome interface.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Heyllo! Jackie here\n"
                + "What can I do for you?\n");
        showLine();
    }

    /**
     * Prints the loading error interface.
     */
    public void showLoadingError() {
        System.out.println("D: OH NOOOOO! I cannot locate the file!!\n");
    }

    /**
     * Prints the error interface with the provided error message.
     *
     * @param errMess A String containing the error message.
     */
    public void showError(String errMess) {
        System.out.println(errMess);
    }

    /**
     * Returns String complete task interface.
     *
     * @param task A jackie.task.Task object that was marked done.
     */
    public String showDone(jackie.task.Task task) {
        return "Noice! I've marked this task as done: \n" + task + "\n";
    }

    /**
     * Returns String in case where List is to be shown but there is no tasks.
     */
    public String emptyList() {
        return "Darling, you have nothing in your list though \n";
    }

    /**
     * Returns String the full TaskList.
     *
     * @param taskList A TaskList that contains all the jackie.task.Task objects' information.
     */
    public String showFullList(TaskList taskList) {
        return "Darling, here are the tasks in your list:\n" + taskList;
    }

    /**
     * Returns String the TaskList after being filtered by a date.
     *
     * @param taskList A TaskList that contains all the jackie.task.Task objects' information.
     * @param dateFilter A LocalDate object that is used to filter the TaskList.
     */
    public String showScheduleList(TaskList taskList, LocalDate dateFilter) {
        return String.format("Darling, here are the tasks with a schedule of %s:\n",
                dateFilter.toString()) + taskList.listSchedule(dateFilter);
    }

    /**
     * Returns String the TaskList after being filtered by a String.
     *
     * @param taskList A TaskList that contains all the jackie.task.Task objects' information.
     * @param searchFilter A String object that is used to filter the TaskList.
     */
    public String showFindList(TaskList taskList, String searchFilter) {
        return String.format("Darling, here are the matching tasks(with %s) in your list:\n",
                searchFilter) + taskList.listFind(searchFilter);
    }

    /**
     * Returns String the delete interface with information of the deleted task.
     *
     * @param task A jackie.task.Task object that was deleted.
     * @param num A int object indicating number of objects left in the tasklist after deletion.
     */
    public String showDelete(jackie.task.Task task, int num) {
        return "okie! I've removed this annoying task: \n" + task + "\n" + showNumOfTask(num);
    }

    /**
     * Returns String the interface containing the number of objects in the tasklist.
     *
     * @param num A int object indicating number of objects in the current tasklist.
     */
    public String showNumOfTask(int num) {
        return "Now you have " + num + " tasks in the list.\n";
    }

    /**
     * Returns String the interface when a task is added to the tasklist.
     *
     * @param task A jackie.task.Task object that is to be added.
     * @param num A int object indicating number of objects left in the tasklist after the addition.
     */
    public String showAdd(jackie.task.Task task, int num) {
        return "Gotcha my dear. I've added this task for you: \n" + task
                + "\nNow you have " + num + " tasks in the list.\n";
    }

    /**
     * Returns String the exit interface.
     */
    public String showFarewell() {
        scanner.close();
        return "Bye bye. Love you\n";
    }

    /**
     * Returns String when undo command is executed.
     * @return A String
     */
    public String showUndo(boolean ifUndone, jackie.TaskList taskList) {
        if (ifUndone) {
            return "Gotcha my dear! I have restored the previous version.\n" + taskList;
        } else {
            return "Dear there is no available version for me to restore to T.T";
        }
    }


}
