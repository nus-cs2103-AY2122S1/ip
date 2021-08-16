import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    /* Default line separator design. */
    private static String sepLine = "===========================================";
    private static String sepLineOpen = "///<<<============ Duke Says: ===========>>>\\\\\\";
    private static String sepLineClose = "\\\\\\<<<===================================>>>///";

    private static ArrayList<String> toDoList = new ArrayList<>();

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
     *
     * @param printThis The message to print inside Duke's text bubble
     */
    private static void dukeSays(String printThis) {
        System.out.println("");
        System.out.println(sepLineOpen);
        System.out.println("    " + printThis);
        System.out.println(sepLineClose);
    }

    /**
     * Used for adding things to the to do list.
     *
     * @param str   Thing to add to the list.
     */
    private static void addToTDL(String str) {
        toDoList.add(str);
        dukeSays("added: " + str);
    }


}
