package skeltal;

/**
 * A class that handles interaction with the user, Eg printing responses.
 */
public class Ui {

    private static String line = "---------------------------------------------";

    /**
     * Prints an introduction statement.
     */
    public static void introduction() {
        System.out.println(line);
        System.out.println("Hello! I am Skeltal! \n"
                + "What can I do for you?");
        System.out.println(line);
    }

    /**
     * Returns a boolean to the skeltal system to signal a shutdown.
     * Prints a goodbye statement to the user.
     * @return A boolean with a true value.
     */
    public static boolean bye() {
        System.out.println(Ui.line);
        System.out.println("Thanks for chatting! Hope to see you again soon! ");
        System.out.println(Ui.line);
        return true;
    }

    /**
     * Returns a line that is used to enclose Skeltal's responses.
     * @return A String of a line.
     */
    public static String line() {
        return Ui.line;
    }
}
