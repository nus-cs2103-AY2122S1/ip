import java.util.Scanner;
/**
 * This class encapsulates a CLI bot named Duke.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class Duke {
    // Set up constant
    static final String divider = "\t--------------------------------------------------------";

    static final String banner =
            "____    __    ____  _______  __        ______   ______   .___  ___.  _______    .___________.  ______       _______   __    __   __  ___  _______  __  \n"
                    + "\\   \\  /  \\  /   / |   ____||  |      /      | /  __  \\  |   \\/   | |   ____|   |           | /  __  \\     |       \\ |  |  |  | |  |/  / |   ____||  | \n"
                    + " \\   \\/    \\/   /  |  |__   |  |     |  ,----'|  |  |  | |  \\  /  | |  |__      `---|  |----`|  |  |  |    |  .--.  ||  |  |  | |  '  /  |  |__   |  | \n"
                    + "  \\            /   |   __|  |  |     |  |     |  |  |  | |  |\\/|  | |   __|         |  |     |  |  |  |    |  |  |  ||  |  |  | |    <   |   __|  |  | \n"
                    + "   \\    /\\    /    |  |____ |  `----.|  `----.|  `--'  | |  |  |  | |  |____        |  |     |  `--'  |    |  '--'  ||  `--'  | |  .  \\  |  |____ |__| \n"
                    + "    \\__/  \\__/     |_______||_______| \\______| \\______/  |__|  |__| |_______|       |__|      \\______/     |_______/  \\______/  |__|\\__\\ |_______|(__) \n"
                    + "                                                                                                                                                       ";

    static final ToDoList list = new ToDoList();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        printWelcomeMessage();
        String input = sc.nextLine();
        Command command = detectCommand(input);
        while (command != Command.EXIT) {
            switch (command) {
                case TODO:
                    handleTodo(input);
                    break;
                case DEADLINE:
                    handleDeadline(input);
                    break;
                case EVENT:
                    handleEvent(input);
                    break;
                case LIST:
                    list.printList();
                    break;
                case DONE:
                    int index = extractIndex(input);
                    list.markTaskAsDone(index);
                    break;
                case UNRECOGNISED:
                    Duke.prettyPrint("Unrecognised command. Perhaps you made a typo?");
                    break;
            }
            input = sc.nextLine();
            command = detectCommand(input);
        }
        printExitMessage();
    }

    /**
     * Formats message passed in and prints it out to the screen.
     *
     * @param message Message to be pretty printed.
     */
    public static void prettyPrint(String message) {
        System.out.printf("%s\n\t%s\n%s%n", divider, message, divider);
    }

    /** Prints the welcome message when a user uses the bot for the first time. */
    private static void printWelcomeMessage() {
        System.out.println(banner);
        prettyPrint(
                "Hello, I'm Duke, your personal CLI bot!\n\t"
                        + "I now function as a simple ToDoList.\n\t"
                        + "Type in tasks that you want me to keep track of and type 'list' to see the tasks you have added.\n\t"
                        + "You can also mark tasks as done by typing 'done' followed by the index of the task you completed (e.g done 2).\n\t"
                        + "Once you are done, just type 'exit' to quit the program.");
    }

    /**
     * Classifies the user's input into one of the Command.
     *
     * @param input Raw user's input.
     * @return The corresponding Command.
     */
    private static Command detectCommand(String input) {
        String[] inputs = input.split(" ");
        return Command.convertInput(inputs[0]);
    }

    /**
     * Extracts out index for commands that deals with modifying specific tasks.
     *
     * @param input Raw user's input.
     * @return Desired index specified by user.
     */
    private static int extractIndex(String input) {
        String[] inputs = input.split(" ");
        return Integer.parseInt(inputs[1]);
    }

    /**
     * Handler for ToDos task creation.
     *
     * @param input Raw user's input.
     */
    private static void handleTodo(String input) {
        String[] extracted = input.split(" ", 2);
        ToDo task = new ToDo(extracted[1]);
        list.addToList(task);
    }

    /**
     * Handler for Deadline task creation.
     *
     * @param input Raw user's input.
     */
    private static void handleDeadline(String input) {
        String[] extracted = input.split(" ", 2)[1].split(" /by ");
        String description = extracted[0];
        String deadline = extracted[1];
        Deadline task = new Deadline(description, deadline);
        list.addToList(task);
    }

    /**
     * Handler for Event task creation.
     *
     * @param input Raw user's input.
     */
    private static void handleEvent(String input) {
        String[] extracted = input.split(" ", 2)[1].split(" /at ");
        String description = extracted[0];
        String dateTime = extracted[1];
        Event task = new Event(description, dateTime);
        list.addToList(task);
    }

    /** Prints the exit message when user types in the exit command. */
    private static void printExitMessage() {
        prettyPrint("Bye bye! See you again soon!");
    }
}
