package duke.io;

/**
 * Encapsulates the methods used to print to output.
 */
public class Ui {

    private static final String LINE_SEPARATOR = "\t____________________________________________________________\n";

    /**
     * Prints a formatted message with line separator on top and bottom.
     *
     * @param message String to be printed.
     */
    public void print(String message) {
        String indentedMessage = "\t " + message.replaceAll("\n", "\n\t ") + "\n";
        System.out.println(LINE_SEPARATOR + indentedMessage + LINE_SEPARATOR);
    }

    /**
     * Prints a loading error message.
     */
    public void showLoadingError() {
        print("LOADING ERROR! ;_;");
    }

    /**
     * Prints a hi message.
     */
    public void sayHi() {
        print("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Prints a bye message.
     */
    public void sayBye() {
        print("Bye. Hope to see you again soon!");
    }
}
