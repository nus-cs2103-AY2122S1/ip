import java.util.Scanner;

/**
 * This is a Ui class, which encapsulates all interactions with the user.
 */
public class Ui {
    private static final String LINE = "\n------------------------------------------\n";
    private static final String EXIT_KEYWORD = "bye";

    /**
     * Prints string between 2 lines.
     * @param s The string to be printed.
     */
    public void print(String s) {
        System.out.print(LINE + s + LINE);
    }

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
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "\n------------------------------------------\n";
        String startGreeting = LINE + "Hello! I'm Duke\n"
                + "What can I do for you?" + LINE;
        System.out.print("Hello from\n" + logo + startGreeting);
    }

    /**
     * Prints the ending display to the user.
     */
    public void printEndDisplay() {
        String endGreeting = "Bye. Hope to see you again soon!";
        print(endGreeting);
    }
}
