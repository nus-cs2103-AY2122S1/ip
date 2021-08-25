package duke.ui;

import duke.task.Task;

import java.util.Scanner;

public class Ui { //IU Class used to handle the interactions with the user.
    private final Scanner sc;

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
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
    }

    public void showGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println("_______________");
    }

    public void showAddedMessage(TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + (taskList.get(taskList.getCount() - 1)));
        System.out.println("Now you have " + taskList.getCount() + " in the list.");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public static void printList(TaskList taskList) {
        taskList.printList();
    }

    public static void doneMessage(String msg) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + msg);
    }

    public static void deleteMessage(Task task, int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + index + " in the list.");
    }

    public static void showLoadingError() {
        System.out.println("☹ OOPS!!! It seems like your file format is incorrect!");
    }
}
