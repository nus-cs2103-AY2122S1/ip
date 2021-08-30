package duke;

import duke.task.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The class to handle interactions with the user.
 */
public class Ui {

    /** the reader to get the user input */
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * The method to show the welcome message
     */
    public void showWelcome() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke :)\nWhat can I do for you?");
        showLine();
    }

    /**
     * The method to show a horizontal divider line
     */
    public void showLine() {
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    /**
     * The method to read the user input
     */
    public String readCommand() throws IOException {
        return reader.readLine();
    }

    /**
     * The method to show the error message
     */
    public void showError(String msg) {
        System.out.println(msg);
    }

    /**
     * The method to show the exit message
     */
    public void showExit() {
        System.out.println("Bye. See you again soon! :)");
    }

    /**
     * The method to show all tasks to user.
     *
     * @param listOfTasks the tasks to be printed
     */
    public void showAllTasks(TaskList listOfTasks) {
        if (listOfTasks.isEmpty()) {
            System.out.println("You currently have no tasks! Add one now ☻");
        } else {
            System.out.println("Here are the tasks in your list:");
            showTasks(listOfTasks);
        }
    }

    /**
     * The method to show all matching tasks to user.
     *
     * @param listOfTasks the tasks to be printed
     */
    public void showMatchingTasks(TaskList listOfTasks) {
        if (listOfTasks.isEmpty()) {
            System.out.println("No matching tasks found! ☹");
        } else {
            System.out.println("Here are the tasks that match this keyword:");
            showTasks(listOfTasks);
        }
    }

    /**
     * The method to print all tasks in the listOfTasks to user.
     *
     * @param listOfTasks the tasks to be printed
     */
    public void showTasks(TaskList listOfTasks) {
        int size = listOfTasks.size();
        for (int i = 0; i < size; i++) {
            Task t = listOfTasks.get(i);
            System.out.println((i + 1) + "." + t.toString());
        }
    }
}
