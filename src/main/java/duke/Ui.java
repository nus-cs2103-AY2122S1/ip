package duke;

import duke.tasks.Task;

import java.util.Scanner;

public class Ui {
    private final String DIVIDER = "───────────────────────────────────────────────────────────────────────\n";
    private final String DOUBLE_DIVIDER = "═══════════════════════════════════════" +
            "════════════════════════════════\n";

    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println(DOUBLE_DIVIDER + "Welcome to Duke!\n" + DOUBLE_DIVIDER);
        System.out.println("Please enter the tasks (todo/event/deadline) to be added to the list.\n" +
                "(Enter 'list' to view the list, or 'bye' to exit.)\n" + DIVIDER);
    }

    public void showGoodbye() {
        System.out.println("Exiting the program, goodbye!");
    }

    public void showLoadingError() {
        System.out.println(DIVIDER + "No taskfile found, creating a new taskfile...\n" + DIVIDER);
    }

    public void showTaskAdded(Task task, TaskList taskList) {
        System.out.println("Got it. I have added this task:\n  " + task + "\n Now you have " +
                taskList.size() + " tasks in the list.\n");
    }

    public void showTaskDeleted(Task task, TaskList taskList) {
        System.out.println("Noted. I have removed this task:\n  " + task + "\n Now you have " +
                taskList.size() + " tasks in the list.\n");
    }

    public void showTaskDone(Task task, TaskList taskList) {
        System.out.println("Great! I've marked this task as done:\n" + task + "\n");
    }

    public void showError(String error) {
        System.out.println("ERROR: " + error);
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showList(TaskList taskList) {
        System.out.println("Here are the tasks in your list: " + taskList);
    }

}
