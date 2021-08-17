package display;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Simulates the Duke Chatbot.
 */
public class Duke {
    private static final String line = "\n\t_______________________________________________________________";

    public static void main(String[] args) {
        initBot();
    }

    private static void initBot() {
        greet();
        analyzeLog();
    }

    private static void greet() {
        Date localDate = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String localTime = timeFormat.format(localDate);
        String msg = "\t I'm IntelliBot. What can I do for you today?";

        if ("05:00:00".compareTo(localTime) <= 0 && localTime.compareTo("12:00:00") < 0) {
            System.out.println(line + "\n\t Good morning!\n" + msg + line);
        } else if ("12:00:00".compareTo(localTime) <= 0 && localTime.compareTo("18:00:00") < 0) {
            System.out.println(line + "\n\t Good afternoon!\n" + msg + line);
        } else {
            System.out.println(line + "\n\t Good evening!\n" + msg + line);
        }
    }

    private static void analyzeLog() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String log = sc.nextLine();
            if (log.equals("bye")) {
                System.out.println(line + "\n\t Peace out!" + line);
                break;
            }
            System.out.println(line + "\n\t " + log + line);
        }

        sc.close();
    }
}
