import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);  // Create a Scanner object
    }

    public void showWelcome() {
        String botName = "HuAI";
        System.out.printf("Hello! I'm %s\n", botName);
        System.out.println("What can I do for you?");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showln(String s) {
        System.out.println(s);
    }

    public void showTask(Task task) {
        System.out.println(task);
    }

    public void showTaskCount(int taskCount) {
        System.out.printf("Now you have %d tasks in the list.\n", taskCount);
    }

    public void showDivider() {
        String bar = "";
        for (int i = 0; i < 50; i++) {
            bar += "_";
        }
        System.out.println(bar);
    }

    public void showDukeException(DukeException e) {
        System.out.printf("HuAI Liddat!!! %s\n", e);
    }

    public void showException(Exception e) {
        e.printStackTrace();
    }
}
