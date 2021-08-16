import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String ROBOT_EMOJI = "\uD83E\uDD16";
    private static final String COLOR_CYAN = "\u001B[36m";
    private static final String COLOR_RESET = "\u001B[0m";
    private static final String LOGO =
        " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Starts the bot
     *
     * @param args CLI commands (not used for now)
     */
    public static void main(String[] args) {
        printLogo();
        greet();
        handleCommands();
    }

    private static void handleCommands() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            // index 0 has command, index 1 has command arguments
            String[] splitInput = input.split(" ", 2);
            String cmd = splitInput[0];
            switch (cmd) {
            case "todo":
                if (splitInput.length <= 1) {
                    robotPrint("Invalid command! Please use as \"todo [task]\"");
                    break;
                }
                addTodo(splitInput[1]);
                break;
            case "deadline":
                if (splitInput.length <= 1) {
                    robotPrint("Invalid command! Please use as \"deadline [task] /by [time]\"");
                    break;
                }
                addDeadline(splitInput[1]);
                break;
            case "event":
                if (splitInput.length <= 1) {
                    robotPrint("Invalid command! Please use as \"event [task] /at [time period]\"");
                    break;
                }
                addEvent(splitInput[1]);
                break;
            case "done":
                if (splitInput.length != 2) {
                    robotPrint("Invalid command! Please use as \"done [task id]\"");
                    break;
                }
                markTaskDone(splitInput[1]);
                break;
            case "list":
                if (splitInput.length != 1) {
                    robotPrint("Invalid command! Please use as \"list\"");
                    break;
                }
                printAllTasks();
                break;
            case "bye":
                if (splitInput.length != 1) {
                    robotPrint("Invalid command! Please use as \"bye\"");
                    break;
                }
                sc.close();
                robotPrint("Bye. Hope to see you again soon!");
                return;
            default:
                robotPrint("Unknown command!");
            }
        }
    }

    private static void markTaskDone(String index) {
        // try to parse int
        int i;
        try {
            i = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            robotPrint("Unable to parse task id!");
            return;
        }

        if (i < 0 || i > tasks.size()) {
            robotPrint("Invalid task id!");
            return;
        }

        Task task = tasks.get(i - 1);
        task.markAsDone();
        robotPrint("Nice! I've marked this task as done:");
        System.out.println("\t " + task);
    }

    private static void addTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        printTaskAdded(todo);
    }

    private static void addDeadline(String args) {
        String[] splitArgs = args.split(" /by ");
        if (splitArgs.length != 2) {
            robotPrint("Unable to parse deadline!");
            return;
        }
        Deadline deadline = new Deadline(splitArgs[0], splitArgs[1]);
        tasks.add(deadline);
        printTaskAdded(deadline);
    }

    private static void addEvent(String args) {
        String[] splitArgs = args.split(" /at ");
        if (splitArgs.length != 2) {
            robotPrint("Unable to parse event!");
            return;
        }
        Event event = new Event(splitArgs[0], splitArgs[1]);
        tasks.add(event);
        printTaskAdded(event);
    }

    private static void printTaskAdded(Task task) {
        robotPrint("Got it. I've added this task:");
        System.out.println("\t" + task);
        String numOfTasks = tasks.size() + " task" + (tasks.size() > 1 ? "s" : "");
        robotPrint("Now you have " + numOfTasks + " in the list.");
    }

    private static void printAllTasks() {
        robotPrint("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + ((i + 1) + ". " + tasks.get(i)));
        }
    }

    private static void printLogo() {
        System.out.println(COLOR_CYAN + LOGO + COLOR_RESET);
    }

    private static void greet() {
        robotPrint("Hello! I'm Duke");
        robotPrint("What can I do for you?");
    }

    private static void robotPrint(String string) {
        System.out.println(ROBOT_EMOJI + ": " + string);
    }
}
