import java.util.Scanner;

public class Virushade {
    /**
     * Prints the greeting.
     */
    private static void greet() {
        System.out.println( "Hello from\n"
                + "__      __ ( )                ____\n"
                + "\\ \\    / /  _   ____  _   _  / __/\n"
                + " \\ \\  / /  | | | ,__|| | | | \\ \\\n"
                + "  \\ \\/ /   | | | |   | |_| | _) \\\n"
                + "   \\__/    |_| |_|    \\__,_||___/\n"
                + "What can <<Virushade>> do for you?");
    }

    /**
     * The echo function that Virushade uses. Echoes whatever words that Virushade is given.
     * @param wordToEcho The word provided by the user for Duke to echo.
     */
    private static void echo(String wordToEcho) {
        System.out.println(wordToEcho);
    }

    /**
     * Virushade first says goodbye before exiting.
     */
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    /**
     * The running sequence for Virushade.
     */
    public static void run() {
        greet();

        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                break;
            } else if (str.equals("list")) {
                Memory.list();
            } else {
                Memory.add(str);
            }
        }

        sc.close();

        exit();
    }

    public static void main(String[] args) {
        run();
    }
}
