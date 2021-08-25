package duke.main;

import duke.task.Task;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void greet() {
        System.out.println("\t Hello! I'm Duke");
    }

    public void displayError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void displayAddedMessage(Task t, TaskList tasks) {
        System.out.println("\t I have added to the list: \n\t \t" + t.toString());
        System.out.println("\t There are " + tasks.getSize() + " items in the list");
    }

    public void displayCompletedMessage(Task taskDone) {
        System.out.println("\t Good job! This task has been completed:");
        System.out.println("\t \t" + taskDone.toString());
    }

    public void displayListOfTasks(TaskList tasks) {
        System.out.println(tasks.toString());
    }

    public void displayExitMessage() {
        System.out.println("\t Bye. Hope to see you again soon");
    }

    public void displaySaveMessage() {
        System.out.println("\t List successfully saved");
    }

    public void displayDeleteMessage(TaskList taskList, int index) {
        System.out.println("\t Noted. The task has been removed!");
    }

}
