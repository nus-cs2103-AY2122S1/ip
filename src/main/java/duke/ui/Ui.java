package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    /** Horizontal line for formatting */
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    /** Greeting message to be displayed when bot starts running */
    private static final String GREETING_MESSAGE = "Hello! I'm JWBot\nWhat can I do for you?";

    /** Goodbye message to be displayed when bot stops running */
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    /** A Scanner instance to obtain user input */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Displays the formatted greeting message to the user.
     */
    public void greetUser() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(GREETING_MESSAGE);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the formatted goodbye message to the user.
     */
    public void exit() {
        scanner.close();
        System.out.println(HORIZONTAL_LINE);
        System.out.println(GOODBYE_MESSAGE);
        System.out.println(HORIZONTAL_LINE);
    }

    public void showError(String errorMessage) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(errorMessage);
        System.out.println(HORIZONTAL_LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void listTasks(TaskList taskList) {
        ArrayList<Task> tasksList = taskList.getTaskList();
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksList.size(); i++) {
            System.out.println((i + 1) + "." + tasksList.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void showCommandDone(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }
}
