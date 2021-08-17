package display;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        List<String> texts = new ArrayList<>();
        String input;
        StringBuilder log;

        while (true) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(line + "\n\t Peace out!" + line);
                break;
            } else if (input.equals("list")){
                log = new StringBuilder();
                for (int i = 0; i < texts.size(); i++) {
                    log.append("\n\t ").append(i + 1).append(". ").append(texts.get(i));
                }
            } else {
                texts.add(input);
                log = new StringBuilder();
                log.append("\n\t added: ").append(input);
            }
            System.out.println(line + log + line);
        }

        sc.close();
    }
}
