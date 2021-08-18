import java.util.Scanner;

public class Duke {

    // Constants
    private static final String LOGO =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:\n%s";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:\n %s";
    private static final String MISSING_DONE_NUMBER_MESSAGE = "Please input a number after the done command";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    // Commands
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";

    // Message methods
    public static void horizontal_line() {
        System.out.print("____________________________________________________________\n");
    }

    public static void display_message(String message) {
        horizontal_line();
        System.out.println(message);
        horizontal_line();
    }

    // Continue loop conditions
    public static boolean canContinue(String input) {
        return !input.equalsIgnoreCase(EXIT_COMMAND);
    }

    public static void main(String[] args) {
        System.out.println(LOGO);
        display_message(WELCOME_MESSAGE);

        TaskList taskList = new TaskList();

        Scanner sc = new Scanner(System.in);
        for (String input = sc.nextLine(); canContinue(input); input = sc.nextLine()) {
            String[] inputArr = input.split(" ");
            String firstCommand = inputArr[0];
            switch (firstCommand) {
                case LIST_COMMAND:
                    display_message(String.format(LIST_MESSAGE, taskList.toString()));
                    break;
                case DONE_COMMAND:
                    if (inputArr.length < 2) {
                        display_message(MISSING_DONE_NUMBER_MESSAGE);
                    } else {
                        String secondCommand = inputArr[1];
                        int taskIndex = Integer.parseInt(secondCommand);
                        display_message(String.format(DONE_MESSAGE, taskList.markTaskAsDone(taskIndex)));
                    }
                    break;
                default:
                    display_message(taskList.addTask(input));
            }
        }
        sc.close();

        display_message(EXIT_MESSAGE);
    }
}
