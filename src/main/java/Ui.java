import java.util.Scanner;

public class Ui {
    protected Scanner sc;

    public Ui() {
    }

    public void showWelcome() {
        System.out.println("Hello from Neko!\nWhat can I do for you?\n");
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        String userInput = sc.nextLine();
        return userInput;
    }

    public void showLine() {
        System.out.println("--------------------------");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showLoadingError() {
        System.out.println("Oops! Error in loading the document...");
    }
}
