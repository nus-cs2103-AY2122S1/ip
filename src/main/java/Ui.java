import java.util.Scanner;

/**
 * This is a Ui class, which encapsulates all interactions with the user.
 */
public class Ui {
    private static final String LINE = "\n" +
            "————————————————————————————————————————————————————————————\n";

    /**
     * Retrieves input with a Scanner object.
     * @return The input string read by a Scanner object.
     */
    public String getInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints the starting display to the user.
     */
    public void printStartDisplay() {
        print(logo());
        print(startGreeting());
    }

    /**
     * Prints the ending display to the user.
     */
    public void printEndDisplay() {
        print(endGreeting());
    }

    /**
     * Prints string between 2 lines.
     * @param s The string to be printed.
     */
    public void print(String s) {
        System.out.print(LINE + s + LINE);
    }

    private String logo() {
        String logo = "\t\t\t\t \t—————      —————\n"
                + "\t\t\t\tM\t|      \\/      |\tM\n"
                + "\t\t\t\to\t|              |\tO\n"
                + "\t\t\t\tR\t|    |\\  /|    |\tR\n"
                + "\t\t\t\tG\t|    | \\/ |    |\tG\n"
                + "\t\t\t\tA\t|    |    |    |\tA\n"
                + "\t\t\t\tN\t|    |    |    |\tN\n"
                + "\t\t\t\t \t ————      ————";
        return logo;
    }

    private String startGreeting() {
        String startGreeting = "Hello, my name is Morgan. I'm your personal task manager.\n"
                + "What can I do for you today?";
        return startGreeting;
    }

    private String endGreeting() {
        String endGreeting = "Bye. Hope to see you again soon.";
        return endGreeting;
    }
}
