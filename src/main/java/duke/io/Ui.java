package duke.io;

/**
 * Encapsulates the methods used to print to output.
 */
public class Ui {

    /**
     * Prints a formatted message with line separator on top and bottom.
     *
     * @param message String to be printed.
     * @return string that is formatted.
     */
    public static String print(String message) {
        System.out.println(message);
        return message;
    }

    /**
     * Prints a loading error message.
     *
     * @return loading error message.
     */
    public static String showLoadingError() {
        return print("LOADING ERROR! ;_;");
    }

    /**
     * Prints a hi message.
     *
     * @return hi message.
     */
    public static String sayHi() {
        return print("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Prints a bye message.
     *
     * @return goodbye message.
     */
    public static String sayBye() {
        return print("Bye. Hope to see you again soon!");
    }
}
