package yoyo.core;

import yoyo.task.Task;
import yoyo.task.TaskList;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a decoration line for output.
     */
    private void outputWrapper() {
        System.out.println("======================"
                + "======================================");
    }

    public void greetUser() {
        String greetings = "Hello! I'm Yoyo.\n"
                + "What can I do for you?";
        outputWrapper();
        System.out.println(greetings);
        outputWrapper();
    }

    public void sayGoodbye() {
        outputWrapper();
        System.out.println("Bye. Hope to see you again soon!");
        outputWrapper();
    }


    public void printTaskList(TaskList tasks) {
        int currListLength = tasks.size();
        outputWrapper();
        if (currListLength == 0) {
            System.out.println("You have no task at the moment.");
        } else {
            for (int i = 0; i < currListLength; i++) {
                System.out.println(i + 1 + "." + tasks.get(i).showStatus());
            }
        }
        outputWrapper();
    }

    public void printMarkTaskMessage(TaskList tasks, int taskIndex) {
        outputWrapper();
        System.out.println("Nice! I've marked this task as done:\n"
                + tasks.get(taskIndex).showStatus());
        outputWrapper();
    }

    public void printRemoveTaskMessage(Task toRemove, TaskList tasks) {
        outputWrapper();
        System.out.println("Noted. I've removed this task:\n"
                + toRemove.showStatus()
                + "\nNow you have "
                + tasks.size()
                + " tasks in the list.");
        outputWrapper();
    }

    /**
     * prints success message for adding task.
     *
     * @param newTask The task that has been created.
     * @param tasks   Task list.
     */
    public void printAddMessage(Task newTask, TaskList tasks) {
        outputWrapper();
        System.out.print("Got it. I've added this task:\n   "
                + newTask.showStatus()
                + "\nNow you have "
                + tasks.size()
                + " tasks in the list.\n");
        outputWrapper();
    }

    public void printErrorMessage(Exception exception) {
        outputWrapper();
        System.out.println(exception.getMessage());
        outputWrapper();
    }

    public String readCommand() {
        return scanner.nextLine();
    }


}
