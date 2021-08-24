package duke.ui;

import duke.data.TaskList;
import duke.data.task.Task;

import java.util.Scanner;

/**
 * This class abstracts the user interface of Duke.
 *
 * @author Chesterwongz
 */
public class Ui {
    private static final String line = "__________________________________________________________";

    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Reads the next line of the user's input.
     *
     * @return The String that the user inputs.
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Frames the message with underscore lines and prints it.
     *
     * @param msg The String we want to frame.
     */
    public void showFramedMsg(String msg) {
        System.out.println(line);
        System.out.println(msg);
        System.out.println(line);
        System.out.println();
    }

    /**
     * Prints the Duke logo.
     */
    public void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        showFramedMsg("Hello from\n" + logo);
    }

    /**
     * Prints our greeting.
     */
    public void showGreeting() {
        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?";
        showFramedMsg(greeting);
    }

    /**
     * Prints our welcome message.
     */
    public void showWelcome() {
        showLogo();
        showGreeting();
    }

    /**
     * Prints our goodbye.
     */
    public void showGoodBye() {
        String bye = "Bye! Hope to see you again soon!";
        showFramedMsg(bye);
    }

    /**
     * Prints out the taskList.
     */
    public void showList(TaskList taskList) {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : taskList.getAllTasks()) {
            System.out.println((index++) + "." + task);
        }
        System.out.println(line);
        System.out.println();
    }

    /**
     * Prints out filtered taskList.
     */
    public void showFilteredList(TaskList taskList, String keyword) {
        System.out.println(line);
        System.out.println("Here are the matching tasks in your list:");
        int index = 1;
        for (Task task : taskList.getAllTasks()) {
            if (task.toString().contains(keyword)) {
                System.out.println((index++) + "." + task);
            }
        }
        System.out.println(line);
        System.out.println();
    }

    /**
     * Prints the error message when the saved tasks could not be loaded.
     */
    public void showLoadingError() {
        String loadingError = "OOPS!!! We couldn't load your saved tasks!";
        showFramedMsg(loadingError);
    }

    /**
     * Prints the Error message.
     *
     * @param msg The Error message to be printed.
     */
    public void showError(String msg) {
        showFramedMsg(msg);
    }
}
