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
        System.out.println(WELCOMEMESSAGE);

        // Logic of program based on user input
        String input = sc.nextLine();
        Command command = Command.valueOfLabel(input.split(" ")[0]);
        while (command != Command.BYE) {
            try {
                switch (command) {
                    case LIST:
                        taskList.printTaskList();
                        break;
                    case DONE:
                        int index = extractIndex(input, Command.DONE);
                        formatPrint(taskList.markTaskDone(index));
                        break;
                    case HELP:
                        printHelp();
                        break;
                    case TODO:
                        String toDoInfo = extractInfo(input, Command.TODO);
                        formatPrint(taskList.addTask(toDoInfo, Command.TODO));
                        break;
                    case DEADLINE:
                        String deadlineInfo = extractInfo(input, Command.DEADLINE);
                        formatPrint(taskList.addTask(deadlineInfo, Command.DEADLINE));
                        break;
                    case EVENT:
                        String eventInfo = extractInfo(input, Command.EVENT);
                        formatPrint(taskList.addTask(eventInfo, Command.EVENT));
                        break;
                    default:
                        throw new DukeException("Invalid command @_@ Try typing 'help' to see my list of commands!");
                }
            } catch (DukeException e) {
                formatPrint(e.getMessage());
            } finally {
                System.out.println(PROMPT);
                input = sc.nextLine();
                command = Command.valueOfLabel(input.split(" ")[0]);
            }
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

    public static String extractInfo(String input, Command command) throws DukeException{
        String[] info = input.split(" ", 2);
        if (info.length < 2) {
            throw new DukeException(String.format("Error: OOPS!!! The description of a %s cannot be empty.",
                    command.label));
        }
        return info[1];
    }

    public static int extractIndex(String input, Command command) throws DukeException{
        String[] info = input.split(" ", 2);
        if (info.length < 2 || info[1].equals("")) {
            throw new DukeException(String.format("Error: OOPS!!! The index argument for %s cannot be empty.",
                    command.label));
        }
        return Integer.parseInt(info[1]) - 1;
    }
}
