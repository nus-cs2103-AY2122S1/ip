package duke;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * A class that takes charge of interactions between the user and Duke programme.
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
        showLine();
        System.out.println("☹ OH NOOOOO! I cannot locate the file!!\n");
        showLine();
    }

    /**
     * Prints the error interface with the provided error message.
     *
     * @param errMess A String containing the error message.
     */
    public void showError(String errMess) {
        showLine();
        System.out.println(errMess);
        showLine();
    }

    /**
     * Prints complete task interface.
     *
     * @param task A duke.task.Task object that was marked done.
     */
    public void showDone(duke.task.Task task) {
        showLine();
        System.out.println("Noice! I've marked this task as done: \n" + task + "\n");
        showLine();
    }

    /**
     * Prints interface in case where List is to be shown but there is no tasks.
     */
    public void emptyList() {
        showLine();
        System.out.println("Darling, you have nothing in your list though \n");
        showLine();
    }

    /**
     * Prints the full TaskList.
     *
     * @param taskList A TaskList that contains all the duke.task.Task objects' information.
     */
    public void showFullList(TaskList taskList) {
        showLine();
        System.out.println("Darling, here are the tasks in your list:\n" + taskList);
        showLine();
    }

    /**
     * Prints the TaskList after being filtered by a date.
     *
     * @param taskList A TaskList that contains all the duke.task.Task objects' information.
     * @param dateFilter A LocalDate object that is used to filter the TaskList.
     */
    public void showScheduleList(TaskList taskList, LocalDate dateFilter) {
        showLine();
        System.out.println(String.format("Darling, here are the tasks with a schedule of %s:\n",
                dateFilter.toString()));
        System.out.println(taskList.listSchedule(dateFilter));
        showLine();
    }

    /**
     * Prints the TaskList after being filtered by a String.
     *
     * @param taskList A TaskList that contains all the duke.task.Task objects' information.
     * @param searchFilter A String object that is used to filter the TaskList.
     */
    public void showFindList(TaskList taskList, String searchFilter) {
        showLine();
        System.out.println(String.format("Darling, here are the matching tasks(with %s) in your list:\n",
                searchFilter));
        System.out.println(taskList.listFind(searchFilter));
        showLine();
    }

    /**
     * Prints the delete interface with information of the deleted task.
     *
     * @param task A duke.task.Task object that was deleted.
     * @param num A int object indicating number of objects left in the tasklist after deletion.
     */
    public void showDelete(duke.task.Task task, int num) {
        showLine();
        System.out.println("okie! I've removed this annoying task: \n" + task + "\n");
        showNumOfTask(num);
        showLine();
    }

    /**
     * Prints the interface containing the number of objects in the tasklist.
     *
     * @param num A int object indicating number of objects in the current tasklist.
     */
    public void showNumOfTask(int num) {
        System.out.println("Now you have " + num + " tasks in the list.\n");
    }

    /**
     * Prints the interface when a task is added to the tasklist.
     *
     * @param task A duke.task.Task object that is to be added.
     * @param num A int object indicating number of objects left in the tasklist after the addition.
     */
    public void showAdd(duke.task.Task task, int num) {
        showLine();
        System.out.println("Gotcha my dear. I've added this task for you: \n" + task
                + "\nNow you have " + num + " tasks in the list.\n");
        showLine();
    }

    /**
     * Prints the exit interface.
     */
    public void showFarewell() {
        showLine();
        System.out.println("Bye bye. Love you\n");
        showLine();
        scanner.close();
    }


}
