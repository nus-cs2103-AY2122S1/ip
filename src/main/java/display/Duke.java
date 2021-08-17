package display;

import tasks.Task;

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
        List<Task> tasks = new ArrayList<>();
        String input;
        String[] words;
        StringBuilder log;

        logging:
        while (true) {
            log = new StringBuilder();
            input = sc.nextLine().trim();
            words = input.split(" ", 2);
            switch (words[0]) {
            case "bye":
                System.out.println(line + "\n\t Peace out!" + line);
                break logging;
            case "list":
                for (int i = 0; i < tasks.size(); i++) {
                    log.append("\n\t ").append(i + 1).append(". ").append(tasks.get(i));
                }
                break;
            case "done":
                int idx = Integer.parseInt(words[1]);
                Task task = tasks.get(idx - 1);
                if (task.getStatusIcon().equals("X")) {
                    log.append("\t This task has been marked as done");
                } else {
                    task.markAsDone();
                    log.append("\n\t Nice! I've marked this task as done:\n\t\t").append(task);
                }
                break;
            default:
                tasks.add(new Task(input));
                log.append("\n\t added: ").append(input);
                break;
            }
            System.out.println(line + log + line);
        }

        sc.close();
    }
}
