package duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Ui {
    Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void displayWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void displayFarewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printList(TaskList taskList) {
        IntStream.range(0, taskList.size()).forEach(i -> {
            System.out.println((i + 1) + "." + taskList.get(i));
        });
    }

    public void showLoadingError() {
        System.out.println("Unable to load file!");
    }

    public void showLine() {
        System.out.println("--------------------------------------------------");
    }

    public void showError(String errorMsg) {
        System.out.println("OOPS!! " + errorMsg);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void announceNewTask(Task newTask) {
        System.out.println("added: " + newTask);
    }

    public void announceTaskCompletion(Task completedTask) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(completedTask);
    }

    public void announceTaskDeletion(Task deletedTask, int numTasksLeft) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println("Now you have " + numTasksLeft + " tasks in the list.");
    }

    public void closeScanner() {
        this.scanner.close();
    }

    public void displayMatches(ArrayList<Task> taskList) {
        System.out.println("Here are the matching tasks in your list:");
        IntStream.range(0, taskList.size()).forEach(i -> {
            System.out.println((i + 1) + "." + taskList.get(i));
        });
    }
}
