import java.util.Scanner;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author Chng Zi Hao
 */
public class Duke {
    // Constants for the program
    static final String DIVIDER = "--------------------------------------------------------------------------------";
    static final String PROMPT = "Enter Command: ";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String WELCOMEMESSAGE = String.format("%s\n%s\n%s\n%s\n%s", DIVIDER, LOGO,
            "Hello! I'm Duke :)\nWhat can I do for you? (Type 'help' to see what I can do!)", DIVIDER, PROMPT);
    private static final String GOODBYEMESSAGE = String.format("%s\n%s\n%s", DIVIDER,
            "Bye :< Hope to see you again soon!", DIVIDER);

    private static TaskList taskList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        taskList = new TaskList();

        // Welcome message
        System.out.print(WELCOMEMESSAGE);

        // Logic of program based on user input
        String input = sc.nextLine();
        Command command = Command.valueOfLabel(input.split(" ")[0]);
        while (command != Command.BYE) {
            switch (command) {
                case LIST:
                    taskList.printTaskList();
                    break;
                case DONE:
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    formatPrint(taskList.markTaskDone(index));
                    break;
                case HELP:
                    printHelp();
                    break;
                case TODO:
                    formatPrint(taskList.addTask(input, Command.TODO));
                    break;
                case DEADLINE:
                    formatPrint(taskList.addTask(input, Command.DEADLINE));
                    break;
                case EVENT:
                    formatPrint(taskList.addTask(input, Command.EVENT));
                    break;
                default:
                    formatPrint("Invalid command @_@ Try typing 'help' to see my list of commands!");
            }
            System.out.print(PROMPT);
            input = sc.nextLine();
            command = Command.valueOfLabel(input.split(" ")[0]);
        }

        // Goodbye message for user and program stops
        System.out.println(GOODBYEMESSAGE);
    }

    /**
     * Formats the input and prints it in a formatted version.
     *
     * @param input Input to be printed.
     */
    public static void formatPrint(String input) {
        System.out.println(DIVIDER);
        System.out.println(input);
        System.out.println(DIVIDER);
    }

    /**
     * Prints the help page for users.
     */
    public static void printHelp() {
        System.out.println(DIVIDER);
        System.out.println("Here is the list of my available commands!\n"
            + "1. todo [description] - Adds a ToDo task to task list\n"
            + "2. deadline [description] /by [deadline] - Adds a Deadline to task list\n"
            + "3. event [description] /at [event duration] - Adds a Event to task list\n"
            + "4. list - Display list of items you have added\n"
            + "5. done [index of completed task] - Marks specified tasks as completed\n"
            + "6. bye - End the program");
        System.out.println(DIVIDER);
    }
}
