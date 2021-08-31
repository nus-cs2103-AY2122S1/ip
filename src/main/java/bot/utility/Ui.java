package bot.utility;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Represents a UI that the user interacts with.
 */
public class Ui {
    private static final String LINE = "\n\t_______________________________________________________________";
    private final Scanner in;

    /**
     * Creates a UI object for the user to interact with.
     */
    public Ui() {
        PrintStream out = System.out;
        this.in = new Scanner(System.in);
    }

    /**
     * Returns the user input.
     *
     * @return A String entered by the user.
     */
    public String getUserInput() {
        return in.nextLine();
    }

    /**
     * Greets the user according to the specific timezone.
     */
    public void greet() {
        Date localDate = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String localTime = timeFormat.format(localDate);
        String msg = "\t I'm IntelliBot. What can I do for you today?";

        if ("05:00:00".compareTo(localTime) <= 0 && localTime.compareTo("12:00:00") < 0) {
            System.out.println(LINE + "\n\t Good morning!\n" + msg + LINE);
        } else if ("12:00:00".compareTo(localTime) <= 0 && localTime.compareTo("18:00:00") < 0) {
            System.out.println(LINE + "\n\t Good afternoon!\n" + msg + LINE);
        } else {
            System.out.println(LINE + "\n\t Good evening!\n" + msg + LINE);
        }
    }

    /**
     * Shows the user a message indicating what happened with their input.
     *
     * @param message The main message to the user.
     * @return A response to user.
     */
    public String showToUser(String message) {
        return LINE + message + LINE;
    }

    protected void close() {
        in.close();
    }
}
