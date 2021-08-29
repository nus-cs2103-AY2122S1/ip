package duke;

import java.util.Scanner;

/**
 * The type Ui that handles string formatting and basic interactions with the user.
 */
public class Ui {

    /** line separator */
    private static final String lineBreak = "\t____________________________________________________________\n";

    /** scanner for reading input */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Formats string argument(s) sequentially with tab before and linebreak after each string.
     * Output is sandwiched between two lineBreaks.
     *
     * @param one     one string.
     * @param strings any subsequent strings to be separated by linebreak.
     * @return the formatted string.
     */
    public static String formatString(String one, String... strings) {
        String result = lineBreak + "\t" + one + "\n";
        for (String s : strings) {
            result += ("\t" + s + "\n");
        }
        result += lineBreak;
        return result;
    }

    /**
     * Formats an array of string inputs sequentially with tab and linebreak after each string.
     * Output is sandwiched between two lineBreaks.
     *
     * @param strings array of strings.
     * @return the formatted string.
     */
    public static String formatString(String[] strings) {
        String result = lineBreak;
        for (String s : strings) {
            if (s == null) {
                break;
            }
            result += ("\t" + s + "\n");
        }
        result += lineBreak;
        return result;
    }

    /**
     * Add a tab after every newline.
     *
     * @param str string input.
     * @return the formatted string.
     */
    public static String tabAllNewline(String str) {
        return str.replace("\n", "\n\t");
    }

    /**
     * Tab and format string.
     *
     * @param str string input.
     * @return the formatted string.
     */
    public static String tabAndFormat(String str) {
        return formatString(tabAllNewline(str));
    }

    /**
     * Show welcome message.
     */
    public void showWelcome() {
        // Starting Message
        String[] startMessage = {" ____        _        ",
            "|  _ \\ _   _| | _____ ",
            "| | | | | | | |/ / _ \\",
            "| |_| | |_| |   <  __/",
            "|____/ \\__,_|_|\\_\\___|",
            "Hello! I'm Duke",
            "What can I do for you?"};
        System.out.println(Ui.formatString(startMessage));
    }

    /**
     * Reads a command string from user and trims it.
     *
     * @return user-inputted string.
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }
}
