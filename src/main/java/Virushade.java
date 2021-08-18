import java.util.Scanner;

public class Virushade {

    /**
     * Prints a line break. Used after every interaction with Virushade.
     */
    private static void lineBreak() {
        System.out.println("_____________________________________________________\n");
    }

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
                + "What can <<Virushade>> do for you?\n");
        lineBreak();
    }

    /**
     * The echo function that Virushade uses. Echoes whatever words that Virushade is given.
     * @param wordToEcho The word provided by the user for Virushade to echo.
     */
    private static void echo(String wordToEcho) {
        System.out.println(wordToEcho);
    }

    /**
     * Virushade first says goodbye before exiting.
     */
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        lineBreak();
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
                TaskList.list();
            } else if (str.startsWith("done ")) {
                TaskList.completeTask(str.substring(5));
            } else if (str.startsWith("todo ")) {
                TaskList.add(str.substring(5), "TODO");
            } else if (str.startsWith("deadline ")) {
                TaskList.add(str.substring(9), "DEADLINE");
            } else if (str.startsWith("event ")) {
                TaskList.add(str.substring(6), "EVENT");
            } else {
                TaskList.add(str, "");
            }

            lineBreak();
        }

        sc.close();
        exit();
    }

    public static void main(String[] args) {
        run();
    }
}
