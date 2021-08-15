package duke.ui;

import duke.task.Task;

import java.util.List;

public class MessageFormatter {

    private static final String INDENTATION_UNIT_STRING = " ";
    private static final int INDENTATION_UNIT_COUNT = 4;
    private static final String DIVIDER_LINE_UNIT_STRING = "_";
    private static final int DIVIDER_LINE_UNIT_COUNT = 60;
    private static final int DIVIDER_INDENTATION_UNIT_COUNT = 1;

    /**
     * Formats the message passed for output.
     *
     * @param message The message to be formatted.
     * @return The formatted message.
     */
    public String getFormattedMessage(String message) {
        return indent(surroundWithDividerLines(message));
    }

    public String formatTasksList(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        int n = tasks.size();
        for (int i = 0; i < n; i++) {
            sb.append(String.format("%d.%s\n", i + 1, tasks.get(i)));
        }
        return sb.toString().stripTrailing();
    }

    public String formatTask(Task task) {
        return indent(task.toString(), 2);
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

    /**
     * Indents the passed string by the passed number of unit indentations.
     * Every line, unless it is empty, is indented. The unit indentation is a single blank space character.
     *
     * @param string The string to be indented.
     * @param count The number of unit indentations.
     * @return The indented string.
     */
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
