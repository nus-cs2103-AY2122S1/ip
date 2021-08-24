package duke;

import duke.command.Command;
import duke.command.CommandKeyword;
import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

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

    public void greetUser() {
        String greetMessage = "Hello! I'm Saitama";
        String detailsMessage = "I do 100 sit-ups, 100 push-ups, 100 squats and a 10 kilometer run every day! No cap";
        this.printMessage(greetMessage);
        this.printMessage(detailsMessage);
    }

    public void showError(DukeException e) {
        this.printMessage(e.getMessage());
    }

    public void showFarewell() {
        this.sc.close();
        this.printMessage("Hope to see you again!! ^_^");
    }

    public void showLoadingError() {
        this.printMessage("There is an error while loading tasks.");
    }

    public Command readCommand() throws IllegalArgumentException {
        String commandName = sc.next();
        CommandKeyword keyword = CommandKeyword.valueOf(commandName.toUpperCase());
        String restOfCommand = sc.nextLine().trim();
        return new Command(keyword, restOfCommand);
    }

    public void clearInput() {
        sc.nextLine();
    }

    public void showAddTask(Task task, int totalTasks) {
        this.printMessage(String.format("Got it. I've added this task:"
                + "\n\t%s"
                + "\nNow you have %d tasks in the list.", task, totalTasks));
    }

    public void showTasks(ArrayList<Task> tasks) {
        int len = tasks.size();
        if (len == 0) {
            this.printMessage("No task is found!");
        } else {
            for (int i = 0; i < len; i++) {
                int num = i + 1;
                Task task = tasks.get(i);
                this.printMessage(String.format("%d.%s", num, task));
            }
        }
    }

    public void showMarkedTask(Task task) {
        if (task != null) {
            this.printMessage(String.format("Nice! I've marked this task as done: \n\t%s", task));
        } else {
            this.printMessage("There is no such task to mark!");
        }
    }

    public void showDeletedTask(Task task, int totalTasks) {
        if (task != null) {
            this.printMessage(String.format("Noted. I've removed this task: \n\t%s\n" +
                    "Now you have %d tasks in the list.", task, totalTasks));
        } else {
            this.printMessage("There is no such task to delete!");
        }
    }

    public void showNoKeyword() {
        this.printMessage("There is no keyword to search for!");
    }

    public void showFilteredTasks(ArrayList<Task> tasks) {
        this.printMessage("Here are the matching tasks in your list:");
        this.showTasks(tasks);
    }
}
