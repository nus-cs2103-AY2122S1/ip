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
    private static final String GREETING = "Hello! I'm Duke!\n"
            + "What can I do for you?";

    public void printGreeting() {
        System.out.print(getGreeting());
    }

    private String getGreeting() {
        return indent(surroundWithDividerLines(LOGO + GREETING));
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
