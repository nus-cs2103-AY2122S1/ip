import java.util.Scanner;

/**
 * Represents the UI of the Duke program.
 */
public class Ui {
    /** Greeting message to be printed when the program starts */
    private static final String GREETING_MESSAGE =
            "____________________________________________________________\n" +
                    "Hello! I'm Duke\n" +
                    "What can I do for you?\n" +
                    "____________________________________________________________\n";
    /** Scanner used to read commands */
    private Scanner input;

    /**
     * Constructor for the class `Ui`.
     */
    public Ui() {
        System.out.println(Ui.GREETING_MESSAGE);
        this.input = new Scanner(System.in);
    }

    /**
     * Reads and returns command from the keyboard.
     *
     * @return The command received.
     */
    public String getCommand() {
        return this.input.nextLine().trim();
    }
}
