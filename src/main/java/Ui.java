import java.util.Scanner;

public class Ui {
    protected Scanner sc;

    public Ui() {
    }

    public void showWelcome() {
        System.out.println("Hello from Neko!\nWhat can I do for you?\n");
        sc = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("--------------------------");
    }
}
