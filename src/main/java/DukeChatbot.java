import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Represents the Duke chatbot.
 *
 * @author Jay Aljelo Saez Ting
 */
public class DukeChatbot {

    private static final String INDENTATION_UNIT_STRING = " ";
    private static final int INDENTATION_UNIT_COUNT = 4;
    private static final String DIVIDER_LINE_UNIT_STRING = "_";
    private static final int DIVIDER_LINE_UNIT_COUNT = 60;
    private static final int DIVIDER_INDENTATION_UNIT_COUNT = 1;
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING_MESSAGE = "Hello! I'm Duke!\n"
            + "What can I do for you?";
    private static final String EXIT_COMMAND = "bye";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String READ_COMMAND_ERROR_MESSAGE = "An unexpected error has occurred.";
    private static final String LIST_TASKS_COMMAND = "list";
    private static final String MARK_TASK_DONE_COMMAND = "done";

    private List<Task> tasks;

    /**
     * Creates a Duke chatbot with an initially empty list of tasks.
     */
    public DukeChatbot() {
        tasks = new ArrayList<>();
    }

    /**
     * Prints the greeting message.
     */
    public void printGreeting() {
        printFormattedMessage(LOGO + GREETING_MESSAGE);
    }

    /**
     * Listens for inputs for commands and responds accordingly.
     */
    public void listenForInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command;
        do {
            try {
                command = br.readLine();
            } catch (IOException e) {
                printFormattedMessage(READ_COMMAND_ERROR_MESSAGE);
                break;
            }
            StringTokenizer st = new StringTokenizer(command);
            String commandName = st.nextToken();
            switch (commandName) {
            case EXIT_COMMAND:
                printExitMessage();
                break;
            case LIST_TASKS_COMMAND:
                printTasks();
                break;
            case MARK_TASK_DONE_COMMAND:
                int taskIndex = Integer.parseInt(st.nextToken()) - 1;
                markTaskDone(taskIndex);
                break;
            default:
                addTask(command);
                break;
            }
        } while (!command.equals(EXIT_COMMAND));
    }

    private void addTask(String taskDescription) {
        Task task = new Task(taskDescription);
        tasks.add(task);
        printFormattedMessage(String.format("added: %s", task));
    }

    private void printTasks() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        int n = tasks.size();
        for (int i = 0; i < n; i++) {
            sb.append(String.format("%d.%s\n", i + 1, tasks.get(i)));
        }
        printFormattedMessage(sb.toString());
    }

    private void markTaskDone(int taskIndex) {
        Task task = tasks.get(taskIndex);
        task.complete();
        StringBuilder sb = new StringBuilder("Nice! I've marked this task as done:\n");
        sb.append(indent(task.toString(), 2));
        printFormattedMessage(sb.toString());
    }

    private void printExitMessage() {
        printFormattedMessage(EXIT_MESSAGE);
    }

    private void printFormattedMessage(String message) {
        System.out.println(getFormattedMessage(message));
    }

    private String getFormattedMessage(String message) {
        return indent(surroundWithDividerLines(message));
    }

    private String surroundWithDividerLines(String message) {
        String dividerLine = getDividerLine();
        return String.format("%s%s\n%s", dividerLine,
                indent(message, DIVIDER_INDENTATION_UNIT_COUNT).stripTrailing(), dividerLine);
    }

    private String getDividerLine() {
        return String.format("%s\n", DIVIDER_LINE_UNIT_STRING.repeat(DIVIDER_LINE_UNIT_COUNT));
    }

    private String indent(String string) {
        return indent(string, INDENTATION_UNIT_STRING, INDENTATION_UNIT_COUNT);
    }

    private String indent(String string, int count) {
        return indent(string, INDENTATION_UNIT_STRING, count);
    }

    private String indent(String string, String unit, int count) {
        String indentation = unit.repeat(count);
        StringBuilder sb = new StringBuilder();
        for (String line : string.split("\n")) {
            if (line.length() > 0) {
                sb.append(indentation).append(line);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
