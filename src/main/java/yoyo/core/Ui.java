package yoyo.core;

import yoyo.task.Task;
import yoyo.task.TaskList;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    };

    /**
     * Prints a decoration line for output.
     */
    private void outputWrapper() {
        System.out.println("======================"
                + "======================================");
    }

    /**
     * Greets the user.
     */
    public void greetUser() {
        String greetings = "Hello! I'm Yoyo.\n"
                + "What can I do for you?";
        outputWrapper();
        System.out.println(greetings);
        outputWrapper();
    }

    /**
     * Say goodbye to users.
     */
    public void sayGoodbye() {
        outputWrapper();
        System.out.println("Bye. Hope to see you again soon!");
        outputWrapper();
    }


    /**
     * Prints all tasks in the current TaskList.
     *
     * @param tasks TaskList of the program.
     */
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

    /**
     * Prints a message indicating selected task has been marked as done.
     *
     * @param tasks TaskList of the program.
     * @param taskIndex Index of the Task to be marked as done.
     */
    public void printMarkTaskMessage(TaskList tasks, int taskIndex) {
        outputWrapper();
        System.out.println("Nice! I've marked this task as done:\n"
                + tasks.get(taskIndex).showStatus());
        outputWrapper();
    }

    /**
     * Prints a message indicating selected task has been removed.
     *
     * @param toRemove The Task that has been removed.
     * @param tasks TaskList of the program.
     */
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
     * @param tasks Task list.
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

    /**
     * Prints the Exception's error message.
     *
     * @param e Error whose message is to be printed.
     */
    public void printErrorMessage(Exception e) {
        outputWrapper();
        System.out.println(e.getMessage());
        outputWrapper();
    }

    /**
     * Reads user's next command and returns it as a string.
     *
     * @return A string containing user's full command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }


}
