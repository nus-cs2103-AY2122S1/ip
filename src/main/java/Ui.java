import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String SEPARATOR =
            "-------------------------------------------------------";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserInput() {
        out.print(">>> ");
        String input = in.nextLine();

        input = input.trim();
        input = input.replaceAll("~", "");

        return input;
    }

    public void showWelcomeMessage() {
        printMsg("Hello! I'm Duke\n    I am your personal to-do list!");
    }

    public void showGoodbyeMessage() {
        printMsg("Bye! Hope to see you again soon!");
    }

    public void showErrorMessage(Exception e) {
        printMsg(e.getMessage());
    }

    public void printMsg(String... msgs) {
        out.println(SEPARATOR);
        for (String msg:msgs) {
            out.println(msg);
        }
        out.println(SEPARATOR);
    }
}
