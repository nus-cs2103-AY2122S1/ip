package chad.ui;

import java.util.List;
import java.util.Map;

import chad.command.Command;
import chad.task.Task;

/**
 * Helper class for formatting the chatbot's messages.
 *
 * @author Jay Aljelo Saez Ting
 */
public class MessageFormatter {

    private static final String INDENTATION_UNIT_STRING = " ";
    private static final String TASKS_LIST_ENTRY_FORMAT = "%d.%s\n";

    private static MessageFormatter instance;

    private MessageFormatter() {}

    /**
     * Gets the sole MessageFormatter instance.
     *
     * @return The sole MessageFormatter instance.
     */
    static MessageFormatter getInstance() {
        if (instance == null) {
            instance = new MessageFormatter();
        }
        return instance;
    }

    /**
     * Formats the message passed for output.
     *
     * @param message The message to be formatted.
     * @return The formatted message.
     */
    String getFormattedMessage(String message) {
        return message;
    }

    /**
     * Creates a formatted String representing the list of tasks passed.
     *
     * @param tasks The list of tasks to be formatted.
     * @return The formatted String representing the list of tasks.
     */
    String formatTasksList(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        int n = tasks.size();
        for (int i = 0; i < n; i++) {
            sb.append(String.format(TASKS_LIST_ENTRY_FORMAT, i + 1, tasks.get(i)));
        }
        return sb.toString().stripTrailing();
    }

    /**
     * Creates a formatted String representing a single task.
     *
     * @param task The task to be formatted.
     * @return The formatted String representing the task.
     */
    String formatTask(Task task) {
        return indent(task.toString(), 2);
    }

    /**
     * Creates a formatted String representing the results from a "Find Task" command.
     *
     * @param queryResults The list of results to be formatted.
     * @return The formatted String representing the list of results.
     */
    String formatFindTasksResultsList(List<Map.Entry<Integer, Task>> queryResults) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Task> findResult : queryResults) {
            sb.append(String.format(TASKS_LIST_ENTRY_FORMAT, findResult.getKey() + 1, findResult.getValue()));
        }
        return sb.toString().stripTrailing();
    }

    String formatCommand(Command command) {
        return indent(command.toString(), 2);
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
        return sb.toString().stripTrailing();
    }
}
