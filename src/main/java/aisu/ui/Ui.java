package aisu.ui;

/**
 * A User Interface for Aisu.
 *
 * @author Liaw Xin Yan
 */
public class Ui {
    private static final int PADDING_LENGTH = 20;
    private static final String GOODBYE_MESSAGE = "See you next time! :D";
    private static final String LOGO = "   _____  .__              \n"
            + "  /  _  \\ |__| ________ __ \n"
            + " /  /_\\  \\|  |/  ___/  |  \\\n"
            + "/    |    \\  |\\___ \\|  |  /\n"
            + "\\____|__  /__/____  >____/ \n"
            + "        \\/        \\/       ";
    private static String formattedInstructions;

    /**
     * Constructor to initialise the User Interface.
     */
    public Ui() {
        initInstructions("todo (task)", "Add tasks without any date/time attached to it",
                "list", "Show list",
                "done (taskNumber)", "Mark task as done",
                "deadline (task) /by (yyyy-mm-dd)", "Add tasks that need to be done before a specific date/time",
                "event (task) /at (date)", "Add tasks that start at a specific time and ends at a specific time",
                "find (keyword)", "Look for tasks with that keyword",
                "delete (taskNumber)", "Delete task",
                "tag (taskNumber) /with (tagname)", "Add tag to task",
                "bye", "Exit program",
                "help", "Show help page");
    }

    /**
     * Initialises the instructions a user can do.
     * @param instructions string of instructions. each pair of strings refer to one instruction.
     */
    public static void initInstructions(String... instructions) {
        StringBuilder result = new StringBuilder();
        int index = 0;
        while (index < instructions.length) {
            int instructionNumber = (index + 2) / 2;
            int descriptionIndex = index + 1;

            result.append(padRightSpaces(String.format(" * %d) Type \"%s\"", instructionNumber, instructions[index]),
                            PADDING_LENGTH))
                    .append(" - ")
                    .append(instructions[descriptionIndex])
                    .append("\n");

            index += 2;
        }
        formattedInstructions = result.toString();
    }

    /**
     * Displays the welcome message from the bot.
     * @return Welcome message.
     */
    public static String getWelcomeMessage() {
        return formatText("\n" + LOGO + "\nHello, I'm Ai-su! How may I help you today?\n",
                "You can:",
                formattedInstructions);
    }

    /**
     * Retrieves the goodbye message from the bot.
     * @return Goodbye message.
     */
    public static String getGoodbyeMessage() {
        return GOODBYE_MESSAGE;
    }

    /**
     * Retrieves the help message from the bot.
     * @return Help message.
     */
    public static String getHelpMessage() {
        return formattedInstructions;
    }

    /**
     * Pads spaces to the right of a string until its length reaches the desired length.
     * @param inputString String to be padded.
     * @param length Desired length of string.
     * @return Result string with padding.
     */
    // Solution below adapted from https://www.baeldung.com/java-pad-string
    public static String padRightSpaces(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(inputString);
        while (sb.length() < length) {
            sb.append(' ');
        }

        return sb.toString();
    }

    /**
     * Formats messages neatly to be displayed in GUI.
     * @param message Message to be shown.
     * @return String format of the messages.
     */
    public static String formatText(String... message) {
        StringBuilder result = new StringBuilder();
        for (String m: message) {
            result.append(m).append("\n");
        }
        return result.append("\n").toString();
    }
}
