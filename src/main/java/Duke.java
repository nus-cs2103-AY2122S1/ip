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
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    // Commands
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";

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

        TodoList todoList = new TodoList();

        Scanner sc = new Scanner(System.in);
        for (String input = sc.nextLine(); canContinue(input); input = sc.nextLine()) {
            switch (input) {
                case LIST_COMMAND:
                    display_message(todoList.toString());
                    break;
                default:
                    display_message(todoList.addTodo(new Todo(input)));
            }
        }
        sc.close();

        display_message(EXIT_MESSAGE);
    }
}
