import java.util.Scanner;

public class Ui {
    private static final String WELCOME_MESSAGE = "Hello, I'm Duke!\nWhat can I do for you?";
    private static final String LINE_DIVIDER = "______________________________________";

    public Ui() {

    }

    public static void showWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }

    public String receiveUserInput() {
        Scanner sc = new Scanner(System.in);
        String userResponse = sc.nextLine();
        return userResponse;

    }

    public void printout(String s) {
        System.out.println(s);
    }

    public static void showLine() {
        System.out.println(LINE_DIVIDER);
    }

    public void showError(String s) {
        System.out.println(s);
    }

    public void showLoadingError() {
        System.out.println("Save file could not be loaded!");
    }
}
