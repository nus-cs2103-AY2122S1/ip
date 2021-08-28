package duke.ui;

import java.util.Scanner;
import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    private final String WELCOME_MESSAGE = "Hello! I'm Duke. What can I do for you?";
    private final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private final int INDENT = 4;
    private final String BORDER = "-".repeat(150);

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getUserInput() {
        return sc.nextLine();
    }

    public void showWelcomeMessage() {
        display(WELCOME_MESSAGE);
    }

    public void showExitMessage() {
        display(EXIT_MESSAGE);
    }

    public void showTaskList(TaskList tasks) {
        display("Here are the tasks in your list:\n" + tasks);
    }

    public void showTaskAddedMessage(Task task, int numRemainingTasks) {
        display("Got it. I've added this task:\n  " + task + '\n' +
            "You have " + numRemainingTasks + " tasks in the list\n");
    }

    public void showTaskDeletedMessage(Task task, int numRemainingTasks) {
        display("Noted. I've removed this task:\n " + task + '\n' +
            "You have " + numRemainingTasks + " tasks in the list\n");
    }

    public void showTaskDoneMessage(Task task) {
        display("Nice! this task has been marked done:\n  " + task + "\n");
    }

    /**
     * Prints list of filtered tasks
     *
     * @param tasks List of filtered tasks
     */
    public void showFilteredTaskList(TaskList tasks) {
        display("Here are the matching tasks in your list:\n" + tasks);
    }

    public void showErrorMessage(Exception e) {
        display(e.getMessage());
    }

    private void display(String... messages) {
        printWithIndent(BORDER);
        for(String message : messages){
            String[] newLineSeparated = message.split("\n");
            for(String line: newLineSeparated) {
                printWithIndent(line);
            }
        }
        printWithIndent(BORDER);
    }

    private void printWithIndent(String string) {
        System.out.println(" ".repeat(INDENT) + string);
    }
}
