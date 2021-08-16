import java.util.Scanner;

public class Duke {

    /* Default line separator design. */
    private static String sepLine = "===========================================";
    private static String sepLineOpen = "///<<<============ Duke Says: ===========>>>\\\\\\";
    private static String sepLineClose = "\\\\\\<<<===================================>>>///";


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Give me something to do!");

        System.out.println(sepLine);

        runInputLoopMain();
    }

    /**
     * The main loop used when detecting keyboard input.
     * Stops when "bye" is detected.
     */
    private static void runInputLoopMain() {
        /* Create scanner for detecting input. */
        Scanner currScanner = new Scanner(System.in);

        /* Stores last input by user. */
        String lastInput = null;

        while (true) {
            System.out.println("");
            lastInput = currScanner.nextLine();

            if (lastInput.equals("bye")) {
                break;
            }

            dukeSays(lastInput);
        }

        dukeExiter();
    }

    /**
     * Runs when program is going to exit.
     */
    private static void dukeExiter() {
        dukeSays("Bye. Hope to see you soon!");
    }

    /**
     * Used when Duke is supposed to say something.
     * This would print the message between 2 line separators.
     */
    private static void dukeSays(String printThis) {
        System.out.println("");
        System.out.println(sepLineOpen);
        System.out.println(printThis);
        System.out.println(sepLineClose);
    }

}
