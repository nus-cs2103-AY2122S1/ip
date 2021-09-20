package sariel.util.ui;

import java.util.ArrayList;
import java.util.Scanner;

import sariel.util.commons.Messages;
import sariel.util.tasks.Task;



/**
 * Deals with the interactions of the user.
 *
 */

public class Ui {
    private final Scanner sc;

    /**
     * Constructor method for the Ui.
     *
     *
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }


    /**
     * Ui method to print a string
     * in a special way. (only single line inputs though, multi line requires tabs)
     *
     * @param s The string to be printed.
     */
    public void print(String s) {
        System.out.println(Messages.HLINE);
        System.out.println("\t" + s);
        System.out.println(Messages.HLINE);
    }

    /**
     * The output when a task is added.
     * @param t The task to be added.
     * @param i The number of tasks in the list after the task has been added.
     * @return
     */
    public String getOutputFromTaskAdded(Task t, int i) {
        return String.format(Messages.TASK_ADDED, t.toString(), i);
    }

    /**
     * The output when a task is removed.
     *
     * @param t The task to be removed.
     * @param i The number of tasks in the list after removal.
     * @return
     */
    public String getOutputFromTaskDeleted(Task t, int i) {
        return String.format(Messages.TASK_DELETED, t.toString(), i);
    }

    /**
     * Method to print the Logo for Duke.
     */
    public void print_logo() {
        System.out.println("Hello from\n" + Messages.LOGO);

    }

    /**
     * Prints all the strings added.
     */
    public String list(ArrayList<? extends Task> ls) {
        String output = "";
        if (ls.size() == 0) {
            return output;
        }
        output += "1." + ls.get(0);
        for (int i = 1; i < ls.size(); i++) {
            int indi = i + 1;
            output += "\n" + indi + "." + ls.get(i);
        }
        return output;
    }

    /**
     * Method for printing an exception.
     *
     * @param e The exception to handle.
     */
    public void printErrorMessage(Exception e) {
        this.print(e.getMessage());
    }


    /**
     * Obtains the input from the user.
     *
     */
    public String getInput() {
        return this.sc.nextLine();
    }


}
