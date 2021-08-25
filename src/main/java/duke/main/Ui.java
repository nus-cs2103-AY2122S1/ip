package duke.main;

import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private final Scanner inputScanner;

    /**
     * Constructor for Duke UI
     */
    public Ui() {
        this.inputScanner = new Scanner(System.in);
        greetOnStart();
    }

    /**
     * Greets user upon starting the assistant.
     */
    public void greetOnStart() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Gets the next input from the user.
     *
     * @return String input.
     */
    public String getNextInput() {
        return inputScanner.nextLine();
    }

    /**
     * Stops taking inputs from the user.
     */
    public void closeInput() {
        inputScanner.close();
    }

    /**
     * Displays error when user makes an invalid Task selection.
     */
    public void showInvalidSelectionError() {
        System.out.println("\tSorry, I can't seem to find that task\n");
    }

    /**
     * Displays error when user inputs characters where a number is expected.
     */
    public void showNumberFormatException() {
        System.out.println("\tI'm Sorry, WHAT?!?!\n");
    }

    /**
     * Displays error when user input is empty.
     */
    public void showEmptyInputException() {
        System.out.println("\tTake your time :)\n");
    }

    /**
     * Displays error when user command is not found.
     */
    public void showUnknownCommandException() {
        System.out.println("\tI don't understand that command (yet...)\n");
    }

    /**
     * Displays the message in the DukeException.
     *
     * @param message to display.
     */
    public void showDukeException(String message) {
        System.out.println(message);
    }

    /**
     * Displays the TaskList.
     *
     * @param taskList to display.
     */
    public void displayTaskList(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("\tYou haven't added any tasks yet\n");
        } else {
            System.out.println(taskList);
        }
    }

    /**
     * Greets an existing user.
     *
     * @param tasks User's existing tasks to be displayed with the greeting.
     */
    public void greetWithFamiliarity(TaskList tasks) {
        System.out.println("\tNice to see you again.");
        System.out.println(tasks.taskSummary());
        if (!tasks.isEmpty()) {
            System.out.println(tasks);
        }
    }
}
