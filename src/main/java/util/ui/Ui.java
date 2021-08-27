package util.ui;

import util.commons.Messages;
import util.tasks.DukeException;
import util.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with the interactions of the user.
 *
 */

public class Ui {
    private final Scanner sc;

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

    public void printTaskAdded(Task t, int i) {
        this.print(String.format(Messages.TASK_ADDED, t.toString(), i));
    }

    public void printTaskDel(Task t, int i) {
        this.print(String.format(Messages.TASK_DELETED, t.toString(), i));
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
    public void list(ArrayList<? extends Task> ls) throws DukeException {
        String output = "";
        if (ls.size() == 0) return;
        output += "1." + ls.get(0);
        for (int i = 1; i < ls.size(); i++) {
            int indi = i + 1;
            output += "\n\t" + indi + "." + ls.get(i);
        }
        this.print(output);
    }

    /**
     * Method for printing an exception.
     *
     * @param e The exception to handle.
     */
    public void print_error_message(Exception e) {
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
