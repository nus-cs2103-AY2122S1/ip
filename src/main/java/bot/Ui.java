package bot;

/**
 * The OutputManager handles bot output messages to console
 */
public class Ui {

    public static final String RESPONSE_BLOCK_MARGIN = "    ";
    public static final String TEXT_BLOCK_MARGIN = " ";
    public static final String DIVIDER = "_________________________________________________";
    public static final String[] START_MESSAGES = {
        "hOI!!! i'm tEMMIE!!",
    };
    public static final String[] END_MESSAGES = {
        "baiii thenn"
    };
    public static final String ERROR_SIGNATURE = "owoWOWOWOW! ";

    /**
     * Prints welcome message dialogue
     */
    public static void printWelcome() {
        print(START_MESSAGES);
    }

    /**
     * Prints goodbye message dialogue
     */
    public static void printGoodbye() {
        print(END_MESSAGES);
    }

    /**
     * Prints given input string in the bot dialogue
     *
     * @param input string to print in dialogue
     */
    public static void printEcho(String input) {
        print(new String[]{input});
    }

    /**
     * Prints given messages in sequence in the bot
     * dialogue
     *
     * @param messages string messages to print in
     *                 dialogue
     */
    public static void print(String[] messages) {
        System.out.println(RESPONSE_BLOCK_MARGIN + DIVIDER);
        for (String message : messages) {
            System.out.println(RESPONSE_BLOCK_MARGIN + TEXT_BLOCK_MARGIN + message);
        }
        System.out.println(RESPONSE_BLOCK_MARGIN + DIVIDER);
    }

    /**
     * Prints given message without dividers
     *
     * @param message message to print
     */
    public static void printNoDivider(String message) {
        System.out.println(RESPONSE_BLOCK_MARGIN + TEXT_BLOCK_MARGIN + message);
    }

}
