package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * Class that deals with interactions with the user
 * such as reading user commands and displaying messages
 * to the user.
 */
public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Method that prints the welcome message
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke\n What can I do for you?");
    }

    /**
     * Method that reads in the user's next inputs
     * @return the command entered by the user
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Method that prints out the list of tasks
     * @param tasks the list of tasks
     */
    public void listTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            Task currTask = tasks.get(i);
            System.out.println((i+1) + "." + currTask.toString());
        }
    }

    /**
     * Method that lists the tasks that contains the word entered by user
     * @param tasks the list of tasks that contains the word entered by user
     */
    public void listFoundTasks(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            Task currTask = tasks.get(i);
            System.out.println((i+1) + "." + currTask.toString());
        }
    }

    /**
     * Method that prints out the goodbye message
     */
    public void stopMethod() {
        System.out.print("Bye. Hope to see you again soon!");
    }

    /**
     * Method that prints out the task marked as done and
     * total number of tasks when user marks it as done.
     * @param task the task to be marked as done
     */
    public void doneTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    /**
     * Method that prints out the task that aas added
     * @param task the task that is added
     */
    public void addTask(Task task, TaskList tasks) {
        System.out.println("Got it. I have added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
    }

    /**
     * Method that prints out the task that the user deleted.
     * @param task the task that is deleted
     */
    public void deleteTask(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
