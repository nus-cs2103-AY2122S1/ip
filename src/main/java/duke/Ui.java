package duke;

/**
 * UI related methods
 */
public class Ui {


    /**
     * Greets user with a simple message
     */
    public static String greet() {
        return ("Hello! I'm Magnolia\n" + "What can I do for you?");
    }
    /**
     * Shows user an error message
     *
     * @param message Error message to be shown
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
