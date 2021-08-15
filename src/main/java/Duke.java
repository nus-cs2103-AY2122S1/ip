import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    // User commands
    private static final String LIST_COMMAND = "list";
    private static final String MARK_TASK_AS_DONE_COMMAND = "done";
    private static final String EXIT_COMMAND = "bye";

    // User-facing messages
    private static final String GREETING_MESSAGE = "Hello! I'm Duke. What can I do for you?";
    private static final String MARKED_TASK_AS_DONE_MESSAGE = "Nice! I've marked this task as done:\n  %s";
    private static final String EXIT_MESSAGE = "Goodbye. Hope to see you again soon!";

    // User-facing error messages
    private static final String MISSING_TASK_NUMBER_MESSAGE =
            "You have to let me know which task to be marked as completed.";
    private static final String INVALID_TASK_NUMBER_MESSAGE =
            "You need to give me a valid number to mark as completed.";
    private static final String TASK_NOT_FOUND_MESSAGE =
            "You don't have a task with that number.";

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
            String inputLine = scanner.nextLine();
            String[] inputArguments = inputLine.split(" ");
            String command = inputArguments[0];
            switch (command) {
            case LIST_COMMAND:
                prettifier.print(taskManager.toString());
                break;
            case MARK_TASK_AS_DONE_COMMAND:
                if (inputArguments.length < 2) {
                    // Index of task to be marked as done is not provided
                    prettifier.print(MISSING_TASK_NUMBER_MESSAGE);
                    break;
                }
                try {
                    int taskNumber = Integer.parseInt(inputArguments[1]);
                    Task task = taskManager.markTaskAsDone(taskNumber);
                    prettifier.print(String.format(MARKED_TASK_AS_DONE_MESSAGE, task.toString()));
                } catch (NumberFormatException e) {
                    // User provided an argument that is not parsable.
                    prettifier.print(INVALID_TASK_NUMBER_MESSAGE);
                } catch (IndexOutOfBoundsException e) {
                    // User provided a task number that is not in the task list.
                    prettifier.print(TASK_NOT_FOUND_MESSAGE);
                }
                break;
            case EXIT_COMMAND:
                prettifier.print(EXIT_MESSAGE);
                scanner.close();
                return;
            default:
                // Add task to the task manager
                String result = taskManager.addTask(new Task(inputLine));
                prettifier.print(result);
                break;
            }
        }
    }

}
