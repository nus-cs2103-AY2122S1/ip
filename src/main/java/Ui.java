import java.util.Scanner;

public class Ui {

    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui() {

    }

    public void showWelcome() {
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void showLoopWelcome() {
        System.out.println("What else can I do for you?");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        try {
            input = scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return input;
    }

    public void printErrorMessage(String msg) {
        System.out.println(msg);
    }

    public void showLoadingError() {
        System.out.println("Initialisation Error! We will override with a fresh state.");
    }

    public void goodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void taskRemovedMessage(Task taskToBeDeleted, int totalTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + taskToBeDeleted.toString());
        System.out.println("Now you have " + totalTask + " task in the list.");
    }

    public void taskAddedMessage(Task task, int totalTask) {
        System.out.println("Got it. I've added this task.");
        System.out.println(task);
        System.out.println("Now you have " + totalTask + " tasks in the list.");
    }
}
