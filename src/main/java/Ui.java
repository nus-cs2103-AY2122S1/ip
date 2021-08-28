import java.util.Scanner;

public class Ui {

    private final Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("\tHello! I'm Duke. \n\tWhat can I do for you?");
    }

    public String readCommand() {
        String input = sc.nextLine();
        return input;
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showLine() {
        System.out.println("\t____________________________________________________________");
    }
}
