import java.util.Scanner;

public class Ui {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_RED = "\u001B[31m";

    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public static void showGreeting() {
        String logo =
                " _   _ _ _\n" +
                        "| | | (_) | _____\n" +
                        "| |_| | | |/ / _ \\\n" +
                        "|  _  | |   < (_) |\n" +
                        "|_| |_|_|_|\\_\\___/\n";

        showMessage("Hello from\n" + logo + "What can I do for you?\n");
    }

    public static void showMessage(String str) {
        System.out.println(ANSI_PURPLE + str + ANSI_RESET);
    }

    public static void showError(String err) {
        System.out.println(ANSI_RED + err + ANSI_RESET);
    }

    public static void showCaret() {
        System.out.print("> ");
    }

    public String readInput() {
        return sc.nextLine();
    }
}
