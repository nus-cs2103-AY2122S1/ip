import java.util.Scanner;

public class Ui {
    //change this
    Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }
    public void showLoadingError() {
        System.out.println("hi");
    }

    public void showWelcome() {
        System.out.println("Hello, I am Duke. What can I do for you?");
    }

    public String[] readCommand() {
        return new String[] {scanner.next(), scanner.nextLine().trim()};
    }

    public void showError(String errMessage) {
        System.out.println("Ooops! " + errMessage);
        System.out.println("Please try again.");
    }
    public void showTaskAdded(Task task, TaskList tasks) {
        System.out.printf(
                "Got it. I've added this task: \n   %s \nNow you have %d task in the list.%n",
                task, tasks.size());
    }

    public void showTaskRemoved(Task task, TaskList tasks) {
        System.out.printf("Noted. I've removed this task:\n  %s\nNow you have %d task in the list.%n",
                task, tasks.size());
    }

    public void showTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
        }
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again!");
    }
}
