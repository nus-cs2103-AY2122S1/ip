import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    // User commands
    private static final String LIST_COMMAND = "list";
    private static final String EXIT_COMMAND = "bye";

    // User-facing messages
    private static final String GREETING_MESSAGE = "Hello! I'm Duke. What can I do for you?";
    private static final String EXIT_MESSAGE = "Goodbye. Hope to see you again soon!";

    // ResponsePrettifier settings
    private static final int INDENTATION_LEVEL = 4;
    private static final int SEPARATOR_LENGTH = 60;
    private static final String SEPARATOR = "_".repeat(SEPARATOR_LENGTH);

    public static void main(String[] args) {
        DukeResponsePrettifier prettifier = new DukeResponsePrettifier(INDENTATION_LEVEL, SEPARATOR);
        prettifier.print(GREETING_MESSAGE);

        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            switch (command) {
            case LIST_COMMAND:
                prettifier.print(taskManager.toString());
                break;
            case EXIT_COMMAND:
                prettifier.print(EXIT_MESSAGE);
                scanner.close();
                return;
            default:
                // Add task to the task manager
                String result = taskManager.addTask(new Task(command));
                prettifier.print(result);
                break;
            }
        }
    }

}
