import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    protected static final String FILE_PATH = "data/duke.txt";
    protected static final String DELIMITER = " | ";
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
    private TaskList tasks;
    
    private Duke() {
        File file = new File(FILE_PATH);
        printLogo();
        print("Initializing Duke...");
        try {
            if (file.exists()) {
                // if file exists, we initialize the task list with the existing saved tasks
                this.tasks = Parser.parseTxtFile(file);
            } else {
                // if file does not exist, we initialize an empty task list, and create a new text file.
                this.tasks = new TaskList();
                file.getParentFile().mkdir(); // creates /data/ directory if it does not exist.
                file.createNewFile();
            }
            
            print("Initialized!");
            this.tasks.printAllTasks();
            print("Hi, im Duke!");
            print("What can i do for you?");
        } catch (IOException | UnableToParseException e) {
            printErr("Unable to initialize duke:");
            System.out.println("\t" + COLOR_RED + e.getMessage() + COLOR_RESET);
            printErr("exiting...");
            System.exit(1);
        }
        
    }
    
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    private void run() {
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
                    | IOException e) {
                printErr(e.getMessage());
            }
        }
    }
    
    protected static void printErr(String string) {
        print(COLOR_RED + string + COLOR_RESET);
    }

    protected static void print(String string) {
        System.out.println(ROBOT_EMOJI + ": " + string);
    }
    
    private static void printLogo() {
        System.out.println(COLOR_CYAN + LOGO + COLOR_RESET);
    }
}
