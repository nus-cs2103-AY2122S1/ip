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
     * @return The first word of a given string.
     */
    private static String firstWord(String str) {
        int index = str.indexOf(' ');

        if (index > -1) {
            // If there exists ' ' in the string, take out the first word only.
            return str.substring(0, index).trim();
        } else {
            // If there exists no ' ' in the string, return the entire string.
            return str;
        }
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
            } else if (firstWord(str).equals("done")) {
                TaskList.completeTask(str);
            } else {
                TaskList.add(str);
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
