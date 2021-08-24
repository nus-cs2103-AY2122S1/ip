package duke;

import duke.command.Command;
import duke.command.CommandKeyword;
import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs an Ui instance. Instantiate a scanner object to take in user's input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    private void printMessage(String message) {
        String formatDisplay = String.format("\t%s", message.replaceAll("\n", "\n\t"));
        System.out.println(formatDisplay);
    }

    /**
     * A welcome message to display to user when Duke starts.
     */
    public void greetUser() {
        String greetMessage = "Hello! I'm Saitama";
        String detailsMessage = "I do 100 sit-ups, 100 push-ups, 100 squats and a 10 kilometer run every day! No cap";
        this.printMessage(greetMessage);
        this.printMessage(detailsMessage);
    }

    /**
     * An error message to display to user according to the exception.
     *
     * @param e An exception thrown due to various reasons such as incorrect user command.
     */
    public void showError(DukeException e) {
        this.printMessage(e.getMessage());
    }

    /**
     * A farewell message to display to user before ending the program.
     */
    public void showFarewell() {
        this.sc.close();
        this.printMessage("Hope to see you again!! ^_^");
    }

    /**
     * A message that will display to user if there is an error loading tasks from the file.
     */
    public void showLoadingError() {
        this.printMessage("There is an error while loading tasks.");
    }

    /**
     * Read user command and create a command object to represent it.
     *
     * @return A command object that consists of keyword and rest of the command.
     * @throws IllegalArgumentException If the command keyword is invalid.
     */
    public Command readCommand() throws IllegalArgumentException {
        String commandName = sc.next();
        CommandKeyword keyword = CommandKeyword.valueOf(commandName.toUpperCase());
        String restOfCommand = sc.nextLine().trim();
        return new Command(keyword, restOfCommand);
    }

    /**
     * Clear any remaining user input.
     */
    public void clearInput() {
        sc.nextLine();
    }

    /**
     * A message to display to user after user successfully added a task.
     *
     * @param task Task that is added.
     * @param totalTasks Total number of tasks in the list after the task is added.
     */
    public void showAddTask(Task task, int totalTasks) {
        this.printMessage(String.format("Got it. I've added this task:"
                + "\n\t%s"
                + "\nNow you have %d tasks in the list.", task, totalTasks));
    }

    /**
     * Display to user the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public void showTasks(ArrayList<Task> tasks) {
        int len = tasks.size();
        if (len == 0) {
            this.printMessage("You have no task!");
        } else {
            for (int i = 0; i < len; i++) {
                int num = i + 1;
                Task task = tasks.get(i);
                this.printMessage(String.format("%d.%s", num, task));
            }
        }
    }

    /**
     * A message to display to user after user try to mark a task.
     * Message displayed depends on the successfulness of the mark operation.
     *
     * @param task The task to mark.
     */
    public void showMarkedTask(Task task) {
        if (task != null) {
            this.printMessage(String.format("Nice! I've marked this task as done: \n\t%s", task));
        } else {
            this.printMessage("There is no such task to mark!");
        }
    }

    /**
     * A message to display to user after user try to delete a task.
     * Message displayed depends on the successfulness of the delete operation.
     *
     * @param task The task to delete.
     * @param totalTasks The total number of tasks remaining.
     */
    public void showDeletedTask(Task task, int totalTasks) {
        if (task != null) {
            this.printMessage(String.format("Noted. I've removed this task: \n\t%s\n" +
                    "Now you have %d tasks in the list.", task, totalTasks));
        } else {
            this.printMessage("There is no such task to delete!");
        }

    }
}
