package mango;

/**
 * Represents a user interface that controls the interactions with the user, such as
 * greeting, showing errors, and exiting.
 */
public class Ui {

    /**
     * Constructor for a Ui.
     */
    public Ui() {
    }

    /**
     * Greets the user with the chat-bot logo and an introduction.
     *
     * @return A greeting message with an introduction.
     */
    public String greet() {
        String logo =
                "   +--+--+--+--+\n" +
                "   | m | a | n | g | o |\n" +
                "   +--+--+--+--+\n\n";

        return "Hello from\n" + logo + "Hello! I'm Mango\nWhat can I do for you?";
    }

    /**
     * Prints a farewell message to the user.
     *
     * @return A farewell message to the user.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a loading error message.
     *
     * @param e The exception that was thrown during loading.
     */
    public void showLoadingError(Exception e) {
        System.out.println("Error encountered when loading data: " + e.getMessage());
    }
}
