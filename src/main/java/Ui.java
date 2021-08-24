import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage + "\n");
    }

    public void greet() {
        System.out.println("Hello from Duke! What can I do for you?\n");
    }

    public String readCommand() {
        System.out.print("You: ");
        return sc.nextLine();
    }

    private void reply(String reply) {
        System.out.println("Duke: " + reply);
    }

    public void goodbye() {
        reply("Nice talking to you, goodbye!");
    }

    public void showList(TaskList taskList) {
        if (taskList.toString().equals("")) {
            reply("Your todo list is empty!\n");
        } else {
            reply("Checking your todo list...");
            System.out.println(taskList + "\n");
        }
    }
}
