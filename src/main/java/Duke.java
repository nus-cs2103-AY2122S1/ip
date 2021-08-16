import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String ROBOT_EMOJI = "\uD83E\uDD16";
    private static final String COLOR_CYAN = "\u001B[36m";
    private static final String COLOR_RED = "\u001B[91m";
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
            try {
                String input = sc.nextLine();
                // index 0 has command, index 1 has command arguments
                String[] splitInput = input.split(" ", 2);
                String cmd = splitInput[0];
                switch (cmd) {
                case "todo":
                    if (splitInput.length <= 1) {
                        throw new InvalidArgumentsException("todo [task]");
                    }
                    addTodo(splitInput[1]);
                    break;
                case "deadline":
                    if (splitInput.length <= 1) {
                        throw new InvalidArgumentsException("deadline [task] /by [time]");
                    }
                    addDeadline(splitInput[1]);
                    break;
                case "event":
                    if (splitInput.length <= 1) {
                        throw new InvalidArgumentsException("event [task] /at [time period]");
                    }
                    addEvent(splitInput[1]);
                    break;
                case "done":
                    if (splitInput.length != 2) {
                        throw new InvalidArgumentsException("done [task id]");
                    }
                    
                    markTaskDone(splitInput[1]);
                    break;
                case "list":
                    if (splitInput.length != 1) {
                        throw new InvalidArgumentsException("list");
                    } else if (tasks.size() == 0) {
                        throw new NoTasksException();
                    }
                    
                    printAllTasks();
                    break;
                case "bye":
                    if (splitInput.length != 1) {
                        throw new InvalidArgumentsException("bye");
                    }
                    sc.close();
                    robotPrint("Bye. Hope to see you again soon!");
                    return;
                default:
                    robotPrintErr("Unknown Command!");
                }
            } catch (InvalidTaskSelectedException
                    | InvalidArgumentsException
                    | UnableToParseException
                    | NoTasksException e) {
                robotPrintErr(e.getMessage());
            }
        }
    }

    private static void markTaskDone(String index) throws UnableToParseException, InvalidTaskSelectedException {
        // try to parse int
        int i;
        try {
            i = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new UnableToParseException("task id");
        }

        if (i < 0 || i > tasks.size()) {
            throw new InvalidTaskSelectedException();
        }

        Task task = tasks.get(i - 1);
        task.markAsDone();
        robotPrint("Nice! I've marked this task as done:");
        System.out.println("\t" + task);
    }

    private static void addTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        printTaskAdded(todo);
    }

    private static void addDeadline(String args) throws UnableToParseException {
        String[] splitArgs = args.split(" /by ");
        if (splitArgs.length != 2) {
            throw new UnableToParseException("deadline");
        }
        Deadline deadline = new Deadline(splitArgs[0], splitArgs[1]);
        tasks.add(deadline);
        printTaskAdded(deadline);
    }

    private static void addEvent(String args) throws UnableToParseException {
        String[] splitArgs = args.split(" /at ");
        if (splitArgs.length != 2) {
            throw new UnableToParseException("event");
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
    
    private static void robotPrintErr(String string) {
        robotPrint(COLOR_RED + string + COLOR_RESET);
    }

    private static void robotPrint(String string) {
        System.out.println(ROBOT_EMOJI + ": " + string);
    }
}
