package duke;

import java.util.Scanner;

/**
 * Class that handles the interaction with the user
 */
public class Ui {
    Scanner scan;
    private String str;
    private static TaskList listOfTasks;
    private static Storage storage;

    /**
     * Constructor for the Ui class.
     *
     * @param t The task list that gets written to
     * @param s The Storage class that gets written to
     */
    public Ui(TaskList t, Storage s) {
        scan = new Scanner(System.in);
        this.listOfTasks = t;
        this.storage = s;
    }

    public Ui() {
        str = "There has been a loading error";
    }

    /**
     * Returns the user input
     *
     * @return String The string that displays the input of the user
     */
    public String input() {
        if (scan.hasNextLine()) {
            String str = scan.nextLine();
            return str;
        }
        return "";
    }


    /**
     * Shows the loading error if there is an error with input
     *
     * @return void
     */
    public String showLoadingError() {
        return this.str;
    }
}
