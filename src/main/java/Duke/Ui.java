package Duke;

import Duke.Keyword;

public class Ui {
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
    public static void greeting() {
        String greet = "Hihi! This is halp! \n\tHow can I help?\n\t";
        System.out.println(format(greet));
    }

    /**
     * Prints ending message for bot.
     */
    public static void end() {
        System.out.println(format("Bye bye!\n\t"));
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
     * @param keyword Duke.Keyword to show which message to print.
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
