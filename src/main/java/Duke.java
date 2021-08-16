import java.util.Scanner;

public class Duke {

    /* Default line separator design. */
    private static String sepLine = "///\\\\\\///\\\\\\<<<===================================>>>///\\\\\\///\\\\\\";


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

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

        while (lastInput != "bye") {
            lastInput = currScanner.nextLine();

        }

    }

}
