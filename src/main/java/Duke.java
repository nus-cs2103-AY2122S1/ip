import java.util.Scanner;

public class Duke {
    // User commands
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
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals(EXIT_COMMAND)) {
                prettifier.print(EXIT_MESSAGE);
                break;
            }
            // Echo user command
            prettifier.print(command);
        }

        scanner.close();
    }

}
