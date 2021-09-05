import java.util.Scanner;

public class Ui {

    private Scanner scanner;
    private static final String WELCOME = " Hello! I'm Duke\n" + " What can I do for you?";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printTemplate(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(msg);
        System.out.println("____________________________________________________________");
    }

    public void showWelcome() {
        printTemplate(WELCOME);
    }

    public void showError(String msg) {
        printTemplate(" ☹ OOPS!!! " + msg);
    }

    public void showLoadingError() {
        showError("There was an error loading the file. Please try again.");
    }
}
