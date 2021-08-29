package duke;
import java.util.Scanner;

public class Ui {
    private static final Scanner sc = new Scanner(System.in);
    private static final String GREETING_MESSAGE = 
    "Hello! I'm Duke" + "\n" + "What can I do for you?";

    public Ui() {}

    protected void showWelcome() {
        System.out.println(GREETING_MESSAGE);
    }

    protected void showLoadingError() {
        System.out.println("Error in loading task in local disk!");
    }

    protected void showLine() {
        System.out.println("________________________________");
    }


    protected String readCommand() {
        String userInput = sc.nextLine();
        return userInput;
    }


    public void displayByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    public void displayCompletedMessage(String completedMessage) {
        System.out.println("Nice! I've marked this task as done:" + 
        "\n" + completedMessage);
    }

    public void displayDeleteMessage(String deleteMessage, int taskLength) {
        System.out.println("Noted. I've removed this task" +
        "\n" + "Now you have " + taskLength + " tasks in the list.");
    }

    public void displayTaskInstructions(String displayMessage, int taskLength) {
        System.out.println("Got it. I've added this task:" + 
        "\n" + displayMessage + "\n" + "Now you have " + taskLength + 
        " tasks in the list.");
    }

    public void displayTaskList(String listMessage) {
        System.out.println("Here are the tasks in your list:" + 
        "\n" + listMessage);
    }
}
