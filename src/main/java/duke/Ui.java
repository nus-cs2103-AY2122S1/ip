package duke;

import java.util.Scanner;

/**
 * Class that handles the interaction with the user
 */
public class Ui {
    Scanner scanner;
    private String errorString;
    private static TaskList listOfTasks;
    private static Storage storage;

    /**
     * Constructor for the Ui class.
     *
     * @param t The task list that gets written to
     * @param s The Storage class that gets written to
     */
    public Ui(TaskList t, Storage s) {
        scanner = new Scanner(System.in);
        this.listOfTasks = t;
        this.storage = s;
    }

    public Ui() {
        errorString = "There has been a loading error";
    }

    /**
     * Returns the user input
     *
     * @return String The string that displays the input of the user
     */
    public String input() {
        //below code adapted from https://www.javatpoint.com/how-to-get-input-from-user-in-java
        if (scanner.hasNextLine()) {
            String inputString = scanner.nextLine();
            return inputString;
        }
        return "";
    }


    /**
     * Shows the loading error if there is an error with input
     *
     * @return void
     */
    public String showLoadingError() {
        return this.errorString;
    }
}
