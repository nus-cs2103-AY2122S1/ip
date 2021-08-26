import java.util.Scanner;

public class Ui {

    private Scanner input = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public String readCommand() {
        String userInput = input.nextLine();
        return userInput;
    }

    public void showLoadingError() {
        System.out.println("No task list file found! Creating a new file for you (:");
    }

    public void showAddTask(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showDeleteTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showDoneTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println(tasks);
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
