package duke;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    static String dash = "__________________________________";

    static String logo = " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";

    private final Scanner in;
    private final PrintStream out;

    /**
     * Initialises a Ui object.
     */
    public Ui() {
        this.in = new Scanner(System.in);
        this.out = new PrintStream(System.out);
    }

    /**
     * Presents "Welcome" Message to user when app is loaded.
     */
    public void showWelcomeMessage() {
        out.println("Hello from\n" + logo);
        out.println(dash);
        out.println("Howdy! I'm Duke" + '\n' + "How may I assist you?");
        out.println(dash);
    }

    /**
     * Presents "Farewell" Message to user when command "bye" is given.
     */
    public void showFarewellMessage() {
        System.out.println(dash);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(dash);
        this.in.close();
        System.exit(0);
    }

    /**
     * Prompts user for command and reads text input.
     *
     * @return command (full line) for Parser to interpret.
     */
    public String getUserCommand() {
        out.print("Enter command: ");
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }
}
