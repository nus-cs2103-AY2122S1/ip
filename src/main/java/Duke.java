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

    /**
     * Starts the bot
     *
     * @param args CLI commands (not used for now)
     */
    public static void main(String[] args) {
        printIntro();
        handleCommands();
    }

    private static void handleCommands() {
        TaskList tasks = new TaskList();
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            try {
                String input = sc.nextLine();
                // index 0 has command, index 1 has command arguments (if applicable)
                String[] splitInput = input.split(" ", 2);
                String cmd = splitInput[0];
                
                switch (cmd) {
                case "todo":
                    if (splitInput.length != 2) {
                        throw new InvalidArgumentsException("todo [task]");
                    }
                    
                    tasks.addTodo(splitInput[1]);
                    break;
                case "deadline":
                    if (splitInput.length != 2) {
                        throw new InvalidArgumentsException("deadline [task] /by [time]");
                    }
                    
                    tasks.addDeadline(splitInput[1]);
                    break;
                case "event":
                    if (splitInput.length != 2) {
                        throw new InvalidArgumentsException("event [task] /at [time period]");
                    }
                    
                    tasks.addEvent(splitInput[1]);
                    break;
                case "done":
                    if (splitInput.length != 2) {
                        throw new InvalidArgumentsException("done [task id]");
                    }
                    
                    tasks.markTaskDone(splitInput[1]);
                    break;
                case "list":
                    tasks.printAllTasks();
                    break;
                case "delete":
                    if (splitInput.length != 2) {
                        throw new InvalidArgumentsException("delete [task id]");
                    }
                    
                    tasks.delete(splitInput[1]);
                    break;
                case "bye":
                    sc.close();
                    print("Bye. Hope to see you again soon!");
                    return;
                default:
                    printErr("Unknown Command!");
                }
            } catch (InvalidTaskSelectedException
                    | InvalidArgumentsException
                    | UnableToParseException
                    | NoTasksException e) {
                printErr(e.getMessage());
            }
        }
    }
    
    private static void printIntro() {
        // print logo
        System.out.println(COLOR_CYAN + LOGO + COLOR_RESET);
        
        print("Hello! I'm Duke");
        print("What can I do for you?");
    }
    
    protected static void printErr(String string) {
        print(COLOR_RED + string + COLOR_RESET);
    }

    protected static void print(String string) {
        System.out.println(ROBOT_EMOJI + ": " + string);
    }
}
