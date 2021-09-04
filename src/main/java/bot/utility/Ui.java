package bot.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a UI that the user interacts with.
 */
public class Ui {

    /**
     * Greets the user according to the specific timezone.
     */
    public void greetConsole() {
        Date localDate = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String localTime = timeFormat.format(localDate);
        String msg = "\t I'm IntelliBot. What can I do for you today?";

        if ("05:00:00".compareTo(localTime) <= 0 && localTime.compareTo("12:00:00") < 0) {
            System.out.println("\n\t Good morning!\n" + msg);
        } else if ("12:00:00".compareTo(localTime) <= 0 && localTime.compareTo("18:00:00") < 0) {
            System.out.println("\n\t Good afternoon!\n" + msg);
        } else {
            System.out.println("\n\t Good evening!\n" + msg);
        }
    }

    protected String greet() {
        Date localDate = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String localTime = timeFormat.format(localDate);
        String msg = "\t I'm IntelliBot. What can I do for you today?";

        if ("05:00:00".compareTo(localTime) <= 0 && localTime.compareTo("12:00:00") < 0) {
            return "\n\t Good morning!\n" + msg;
        } else if ("12:00:00".compareTo(localTime) <= 0 && localTime.compareTo("18:00:00") < 0) {
            return "\n\t Good afternoon!\n" + msg;
        } else {
            return "\n\t Good evening!\n" + msg;
        }
    }
}
