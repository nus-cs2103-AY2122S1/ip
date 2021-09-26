package Wonderland;

public class Ui {
    private static final String GREET_MESSAGE = "Welcome to Wonderland! What do you want?\n\t" +
            "I'm running late so make it quick.\n\t";
    private static final String BYE_MESSAGE = "No time to say 'hello, goodbye,'\n\t" +
            "I'm late, I'm late, I'm late!\n\t";
    /**
     * Returns formatted output string for bot message.
     *
     * @param input An input string.
     * @return a formatted string for bot message.
     */
    private static String format(String input) {
        return String.format("\t--------------------- " +
                "\n\t%1$s--------------------- ", input);
    }

    /**
     * Prints greeting message for bot.
     */
    public static void greet() {
        System.out.println(format(GREET_MESSAGE));
    }

    /**
     * Prints ending message for bot.
     */
    public static void end() {
        System.out.println(format(BYE_MESSAGE));
    }

    /**
     * Prints messages in proper format.
     *
     * @param message String to be printed properly.
     */
    public static void display(String message) {
        System.out.println(format(message));
    }

    /**
     * Prints message for missing date in proper format.
     *
     * @param keyword Keyword to show which message to print.
     */
    public static void missingDate(Keyword keyword) {
        if (keyword.equals(Keyword.EVENT)) {
            System.out.println(format("OOPS!!! No date for event! " +
                    "Use format of event description /at YYYY-MM-DD \n\t"));
        } else if (keyword.equals(Keyword.DEADLINE)){
            System.out.println(format("OOPS!!! No date for deadline! " +
                    "Use format of deadline description /by YYYY-MM-DD \n\t"));
        }
    }
}
