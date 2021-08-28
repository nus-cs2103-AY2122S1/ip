package aisu;

import java.io.PrintStream;

import java.util.Scanner;

/**
 * A User Interface for Aisu.
 *
 * @author Liaw Xin Yan
 */
public class Ui {
    private static final String DIVIDER = "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=\n";
    private static final String LOGO = " (`-')  _   _               (`-').->           \n" +
            "(OO ).-/  (_)              ( OO)_       .->\n" +
            "/ ,---.   ,-(`-') (`-')   (_)--\\_) ,--.(,--.\n" +
            "| \\ /`.\\  | ( OO) ( OO).->/    _ / |  | |(`-')\n" +
            "'-'|_.' | |  |  )(,------.\\_..`--. |  | |(OO )\n" +
            "(|  .-. |(|  |_/  `------'.-._)   \\|  | | |  \\ \n" +
            "|  | |  | |  |'->         \\      / \\  '-'(_ .'\n" +
            "`--' `--' `--'             `-----'  `-----'   \n";
    private static final int PADDING_LENGTH = 48;
    private static String formattedInstructions;
    private static final String GOODBYE_MESSAGE = LOGO + DIVIDER + " See you next time! :D\n" + DIVIDER;

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
        initInstructions("todo (task)", "Add tasks without any date/time attached to it",
                "list", "Show list",
                "done (taskNumber)", "Mark task as done",
                "deadline (task) /by (yyyy-mm-dd)", "Add tasks that need to be done before a specific date/time",
                "event (task) /at (date)", "Add tasks that start at a specific time and ends at a specific time",
                "find (keyword)", "Look for tasks with that keyword",
                "delete (taskNumber)", "Delete task",
                "bye", "Exit program");

    }

    /**
     * Initialises the instructions a user can do.
     * @param instructions string of instructions. each pair of strings refer to one instruction.
     */
    public static void initInstructions(String... instructions) {
        StringBuilder result = new StringBuilder();
        int index = 0;
        while (index < instructions.length) {
            result.append(padRightSpaces(String.format(" * %d) Type \"%s\"", (index + 2) / 2, instructions[index]),
                            PADDING_LENGTH))
                    .append(" - ")
                    .append(instructions[index + 1])
                    .append("\n");
            index += 2;
        }
        formattedInstructions = result.toString();
    }

    /**
     * Gets the input from the user.
     * @return input from the user in string format.
     */
    public String getInput() {
        return this.in.nextLine();
    }

    public String getGoodbyeMessage() {
        return GOODBYE_MESSAGE;
    }

    /**
     * Displays the ASCII divider.
     */
    public void showDivider() {
        this.out.println(DIVIDER);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcomeMessage() {
        this.out.println("\n" + LOGO + DIVIDER + " Hello, I'm Ai-su! How may I help you today?\n" + DIVIDER);
        this.out.println("You can:");
        this.out.println(formattedInstructions + "\n" + DIVIDER + "\n");
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbyeMessage() {
        this.out.println(GOODBYE_MESSAGE);
    }

    /**
     * Displays the error message.
     * @param message AisuException
     */
    public void showError(String message) {
        this.out.println(message);
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
     * Shows message(s) to the user.
     *
     * @param message Message to be shown.
     */
    public void showToUser(String... message) {
        for (String m : message) {
            this.out.println(m);
        }
    }

    /**
     * Formats messages neatly to be displayed in GUI.
     * @param message Message to be shown.
     * @return String format of the messages.
     */
    public String formatText(String... message) {
        StringBuilder result = new StringBuilder();
        for (String m: message) {
            result.append(m).append("\n");
        }
        return result.toString();
    }
}
