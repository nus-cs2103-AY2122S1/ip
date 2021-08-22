package util.ui;

import util.tasks.DukeException;
import util.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Scanner;


/**
 * Deals with the interactions of the user.
 *
 */

public class Ui {
    private static final String taskRemoved = "Noted, I've removed this task:";
    private static final String taskComplete = "Nice, I've marked this task as done";
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String greetings = "Greetings! I'm Duke\n\tWhat can I do for you?";
    private static final String bye = "Godspeed young padawan!";
    private static final String hline = "\t----------------------------";
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
        System.out.println(hline);
        System.out.println("\t" + s);
        System.out.println("\n" + hline);
    }

    public void printTaskAdded(Task t, int i) {
        System.out.println(hline);
        System.out.println("\tRoger! I have added this task.");
        System.out.println("\t" + t);
        System.out.println(String.format("\tNow you have %d tasks in the list.", i));
        System.out.println("\n" + hline);
    }

    public void printTaskDel(Task t, int i) {
        this.print("The following task has been removed\n\t" + t + "\n\tNow you have "+ i + " tasks remaining");
    }






    /**
     * Method to print the Logo for Duke.
     */
    public void print_logo() {
        System.out.println("Hello from\n" + Ui.logo);

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
