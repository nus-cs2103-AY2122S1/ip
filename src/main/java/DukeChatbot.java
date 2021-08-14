import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public void printGreeting() {
        printFormattedMessage(LOGO + GREETING_MESSAGE);
    }

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
            switch (command) {
            case EXIT_COMMAND:
                printExitMessage();
                break;
            default:
                printFormattedMessage(command);
                break;
            }
        } while (!command.equals(EXIT_COMMAND));
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
