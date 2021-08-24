import java.util.Scanner;

public class Ui {
    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    void displayStart() {
        String logo = " __      _\n"
                + "|   \\   | |__    __ __     __\n"
                + "| |\\ \\  | |\\ \\  / / \\ \\   / /\n"
                + "| | \\ \\ | | \\ \\/ /   \\ \\ / / \n"
                + "| |  \\ \\| |  \\  /    /    /\n"
                + "| |   \\   |  / /    / / \\ \\\n"
                + "|_|    \\__| /_/    /_/   \\_\\\n";
        displayOutput("Hello! This is\n" + logo + "\nWhat can I do for you?");

    }

    void displayOutput(String message) {
        displayDivider();
        System.out.println("\t" + message.replace("\n", LS + "\t"));
        displayDivider();
    }

    String readInput() {
        return sc.nextLine();
    }

    private void displayDivider() {
        System.out.println("\t____________________________________________________________" + LS);
    }
}
