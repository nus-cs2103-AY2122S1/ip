package duke.main;

import duke.task.Task;
import duke.task.TaskList;

import java.util.List;
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
     * Displays error when user input is empty.
     */
    public void showEmptyInputException() {
        System.out.println("\tTake your time :)\n");
    }

    /**
     * Displays error when user command is not found.
     */
    public void showUnknownCommandException(String command) {
        System.out.println("\tI don't understand " + command + " (yet...)\n");
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

    /**
     * Displays matching tasks.
     *
     * @param matchingTasks to be displayed.
     */
    public void showMatchingTasks(List<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("\tNo matching tasks found!\n");
        } else {
            System.out.println("\tHere are the matching tasks from your list:\n");
            System.out.println(TaskList.enumerateTasks(matchingTasks));
        }
    }

}
