package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private String input = "";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.Duke\n" + "What can I do for you?");
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void addTaskToList(Task task, int size) {
        String taskToString = task.toString();
        System.out.println("Got it. I've added this task: \n" + taskToString
                + "\nNow you have " + size + " tasks in the list.");
    }

    public void removeTaskFromList(Task task, int size) {
        String taskToString = task.toString();
        System.out.println("Noted. I've removed this task: \n" +
                taskToString
                + "\nNow you have " + size + " tasks in the list.");
    }

    public void printTaskList(TaskList taskList) {
        if (taskList.getSize() == 0) {
            System.out.println("There are currently no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.getSize(); i++) {
                System.out.println((i + 1) + "." + taskList.getTask(i).toString());
            }
        }
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String e) {
        System.out.println(e);
    }

    public void showLine() {
        System.out.println("___________________");
    }

}
