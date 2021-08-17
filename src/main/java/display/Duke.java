package display;

import error.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Simulates the Duke chatbot.
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

    /**
     * Primary response function of the chatbot
     */
    private static void analyzeLog() {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        String[] words;
        StringBuilder log;

        logging:
        while (true) {
            log = new StringBuilder();
            words = sc.nextLine().trim().split(" ", 2);
            try {
                checkInput(words);
                switch (words[0]) {
                case "bye":
                    System.out.println(line + "\n\t Peace out!" + line);
                    break logging;
                case "list":
                    log.append("\n\t Here are the tasks in your list:");
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
                case "todo":
                    ToDo newTask = new ToDo(words[1]);
                    tasks.add(newTask);
                    log.append("\n\t Got it. I've added this task:\n\t\t").append(newTask).append("\n\t Now you have ")
                            .append(tasks.size()).append(" tasks in the list.");
                    break;
                case "deadline":
                    String[] details = words[1].split(" /by ", 2);
                    Deadline deadline = new Deadline(details[0], details[1]);
                    tasks.add(deadline);
                    log.append("\n\t Got it. I've added this task:\n\t\t").append(deadline)
                            .append("\n\t Now you have ").append(tasks.size()).append(" tasks in the list.");
                    break;
                case "event":
                    details = words[1].split(" /at ", 2);
                    Event event = new Event(details[0], details[1]);
                    tasks.add(event);
                    log.append("\n\t Got it. I've added this task:\n\t\t").append(event)
                            .append("\n\t Now you have ").append(tasks.size()).append(" tasks in the list.");
                    break;
                case "delete":
                    idx = Integer.parseInt(words[1]);
                    task = tasks.get(idx - 1);
                    tasks.remove(idx - 1);
                    log.append("\n\t Noted. I've removed this task:\n\t\t").append(task)
                            .append("\n\t Now you have ").append(tasks.size()).append(" tasks in the list.");
                    break;
                default:
                    throw new DukeException(
                            "\n\t ☹ My dictionary does not contain this sophisticated language.\n\t Maybe someday :)");
                }
            } catch (DukeException e) {
                log.append(e.getMessage());
            }
            System.out.println(line + log + line);
        }

        sc.close();
    }

    /**
     * Handles DukeExceptions for the chatbot
     * @param log The separate words in a given line
     */
    private static void checkInput(String[] log) throws DukeException{
        if (log.length == 1) {
            switch (log[0]) {
            case "todo":
                throw new DukeException("\n\t ☹ Oh dearie!. The description of a todo cannot be empty!");
            case "deadline":
                throw new DukeException("\n\t ☹ Oh lord!. I need some description and a time limit!");
            case "event":
                throw new DukeException("\n\t ☹ By the heavens!. I need some description and a timing!");
            }
        } else if (log.length == 2) {
            switch (log[0]) {
            case "deadline":
                if (log[1].split(" /by ", 2).length != 2) {
                    throw new DukeException("\n\t ☹ Blimey! Did you forget to type \"/by\" or a time limit?");
                }
                break;
            case "event":
                if (log[1].split(" /at ", 2).length != 2) {
                    throw new DukeException("\n\t ☹ Wait! Did you forget to type \"/at\" or a timing?");
                }
            }
        }
    }
}
