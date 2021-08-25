import java.util.Scanner;

public class Ui {
    private static String WELCOME_TEXT = "Hey there I'm Duke!\n" + "How can I help you today?";
    private static String BYE_TEXT = "Bye! Hope to see you again!";

    private Scanner in;

    public Ui(Scanner in) {
        this.in = in;
    }

    public String[] readCommand() {
        System.out.print("> ");
        String commandString = in.next();
        String arguments = in.nextLine().trim();

        return new String[] { commandString, arguments };
    }

    public void printGreeting() {
        printMessage(WELCOME_TEXT);
    }

    public void printGoodbye() {
        printMessage(BYE_TEXT);
    }

    public void printMessage(String string) {
        System.out.print("------------------------------------------------\n" + string + "\n"
                + "------------------------------------------------\n\n");
    }
}
