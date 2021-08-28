package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 *
 * @author Adam Ho
 */
public class Ui {
    private final String DIVIDER = "\t____________________________________________________________";
    private Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showWelcome() {
        System.out.println("\tHello! I'm Adam, your personal chat bot.\n\tHow may I assist you today?");
    }

    public void showExit() {
        System.out.println("\tGoodbye! Please visit me again soon :(");
    }

    public void showAddTask(TaskList tasks, Task task) {
        System.out.println("    Got it. I've added this task: \n      " + task);
        System.out.println("    Now you have " + tasks.getListSize() + " in the list.");
    }

    public void showDeleteTask(TaskList tasks, Task task) {
        System.out.println("    Noted. I've removed this task: \n      " + task);
        System.out.println("    Now you have " + tasks.getListSize() + " in the list.");
    }

    public void showLoadingError() {
        System.out.println("TEST LOADING ERROR");
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("\tHere are the tasks in your list:");
        int id = 1;
        for (Task task : tasks.getTaskList()) {
            System.out.println("\t" + id++ + "." + task);
        }
    }

    public static void done(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done: \n      " + task);
        System.out.println("    ____________________________________________________________");
    }

    public void showError(String errorMessage) {
        System.out.println("\t" + errorMessage);

    }
}
