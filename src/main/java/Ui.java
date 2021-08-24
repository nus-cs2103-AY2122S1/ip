import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void greet() {
        System.out.println("Hello from Duke! What can I do for you?\n");
    }

    public String readCommand() {
        System.out.print("You: ");
        return sc.nextLine();
    }

    public void reply(String reply) {
        System.out.println("Duke: " + reply);
    }
}
